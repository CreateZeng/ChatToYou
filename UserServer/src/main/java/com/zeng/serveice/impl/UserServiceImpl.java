package com.zeng.serveice.impl;

import com.zeng.configuration.JWTProperties;
import com.zeng.mapper.UserMapper;
import com.zeng.entry.vo.PayLoad;
import com.zeng.entry.dto.UserDTO;
import com.zeng.entry.po.User;
import com.zeng.serveice.UserService;
import com.zeng.utils.CookieUtil;
import com.zeng.utils.JWTUtil;
import com.zeng.utils.MsgUtil;
import com.zeng.utils.RSAUtil;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-08-17
 **/
@SuppressWarnings("all")
@Service
public class UserServiceImpl implements UserService{

    private static Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;
    @Resource(name = "RedisString")
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private MsgUtil msgUtil;
    @Autowired
    private JWTProperties jwtProperties;

    /**
     * @根据用户账号密码进行登录
     * @Params:
     * @Return:
     *
    */
    @Override
    public String userLoginByPassword(String username, String password) {
        logger.info("使用账号密码执行登录逻辑.......");
        User user = userMapper.selectByUsername(username);
        UserDTO userDTO = new UserDTO();
        if (user!=null){
            logger.info("查询到用信息、用户名为---"+user.getUsername());
            if (user.getPassword().equals(password)){
                userDTO.setUsername(user.getUsername());
                userDTO.setPhone(user.getPhone());
                //生成token
                String token =null;
                try {
                    token=JWTUtil.generateTokenExpireMinutes(userDTO,jwtProperties.getExpire(), RSAUtil.acquirePrivate(RSAUtil.path + "\\private.txt"));
                    logger.info("根据用户信息生成Token---"+token);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                logger.info("登录逻辑执行完毕......");
                return token;
            }
        }
        logger.info("登录逻辑执行完毕......");
        return null;
    }

    /**
     * @使用手机号进行登录
     * @Params:
     * @Return:
     *
    */
    @Override
    public String userLoginByPhone(String phone,String code) {
        logger.info("使用手机验证码执行登录逻辑.......");
        User user = userMapper.selectByPhone(phone);
        UserDTO userDTO = new UserDTO();
        if (user!=null){
            logger.info("查询到用信息、用户名为---"+user.getUsername());
            String codeRedis = redisTemplate.opsForValue().get("MsgCode:" + user.getPhone());
            if (code!=null&&code.equals(codeRedis)){
                userDTO.setUsername(user.getUsername());
                userDTO.setPhone(user.getPhone());
                //生成token
                String token =null;
                try {
                    token=JWTUtil.generateTokenExpireMinutes(userDTO, jwtProperties.getExpire(), RSAUtil.acquirePrivate(RSAUtil.path + "\\private.txt"));
                    logger.info("根据用户信息生成Token---"+token);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                logger.info("登录逻辑执行完毕......");
                return token;
            }
        }
        logger.info("登录逻辑执行完毕......");
        return null;
    }

    /**
     * @根据用户信息进行用户注册
     * @Params:
     * @Return:
     *
    */
    @Override
    public boolean userRegister(User user) {
        //判断用户名是否使用过
        logger.info("用户进行注册.......");
        User selectUser = userMapper.selectByUsername(user.getUsername());
        if (selectUser!=null){
            logger.info("该用户已经注册过.......");
            return false;
        }
        //短信验证手机号的正确性
        String code = redisTemplate.opsForValue().get("MsgCode:" + user.getPhone());
        if (code!=null&&code.equals(user.getCode())){
            logger.info("验证码校验成功.......");
            int count = userMapper.insertUser(user);
            logger.info("用户结束注册.......");
            return count == 1;
        }
        logger.info("用户结束注册.......");
        return false;
    }

    /**
     * @发送验证码服务
     * @Params:
     * @Return:
     *
    */
    @Override
    public boolean sendVailCode(String phone) {
        logger.info("向手机号为"+phone+"的用户、发送短信验证码.......");
        String code = msgUtil.sendCode(phone);
        if (code==null){
            logger.info("发送短信验证码失败......");
            return false;
        }
        //将验证码存入redis缓存、5分钟失效
        String str= redisTemplate.opsForValue().get("MsgCode:" + phone);
        if (str==null){
            redisTemplate.opsForValue().set("MsgCode:"+phone,code ,5 ,TimeUnit.MINUTES );
            logger.info("验证码存入redis成功.......");
        }
        logger.info("发送短信验证码结束.......");
        return true;
    }

    /**
     * @对用户信息进行认证
     * @Params:
     * @Return:
     *
    */
    @Override
    public UserDTO userVerufy(HttpServletRequest request, HttpServletResponse response) {
        logger.info("进行用户身份认证.......");
        Cookie[] requestCookies = request.getCookies();
        UserDTO userDto=null;
        if (requestCookies!=null){
            for (Cookie requestCookie : requestCookies) {
                String token = requestCookie.getValue();
                logger.info("从请求的Coolie中获取到token---"+token);
                try {
                    logger.info("开始解析token.....");
                    //获取JWT载荷
                    PayLoad payLoad = JWTUtil.analysisJWTToken(token, RSAUtil.acquirePublic(RSAUtil.path + "\\public.txt"));
                    //获取载荷信息、Id、userInfo、有效时间
                    String payLoadId = payLoad.getId();
                    userDto= payLoad.getUserDto();
                    Date expiration = payLoad.getExpiration();
                    if (userDto!=null){
                        logger.info("解析后用户信息如下: username="+userDto.getUsername()+" phone="+userDto.getPhone());
                    }
                    //判断该token是否过期、存入了redis缓存
                    Boolean flag = redisTemplate.hasKey(payLoadId);
                    if (flag){
                        return null;
                    }
                    //判断载荷信息的剩余有效时间  10分钟之内、将其过期
                    Date surplus = DateTime.now().minusMillis(jwtProperties.getSurplus()).toDate();
                    if (surplus.getTime()>expiration.getTime()){
                        logger.info("该token即将过期、正在重新生成.....");
                        //将要过期、放入redis缓存
                        redisTemplate.opsForValue().set(payLoadId, token, surplus.getTime()-expiration.getTime(),TimeUnit.MILLISECONDS);
                        //重新生成token
                        token=JWTUtil.generateTokenExpireMinutes(userDto, jwtProperties.getExpire(), RSAUtil.acquirePrivate(RSAUtil.path + "\\private.txt"));
                        //将token放入响应的cookie中
                        CookieUtil.CookieBuilder cookieBuilder = CookieUtil.createCookieBuilder("WS-TOKEN", token);
                        cookieBuilder.domian("api.websocket.com").httpOnly(true).path("/").response(response).build();
                        logger.info("token重新生成成功........");
                    }
                } catch (Exception e) {
                    logger.info("解析token异常.....");
                }
            }
        }
        logger.info("用户信息认证结束.......");
        return userDto;
    }
}

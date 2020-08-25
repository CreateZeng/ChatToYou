package com.zeng.serveice.impl;

import com.zeng.mapper.UserMapper;
import com.zeng.pojo.dto.UserDTO;
import com.zeng.pojo.po.User;
import com.zeng.serveice.UserService;
import com.zeng.utils.CookieUtils;
import com.zeng.utils.JWTUtil;
import com.zeng.utils.MsgUtil;
import com.zeng.utils.RSAUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-08-17
 **/
@SuppressWarnings("all")
@Service
public class UserServiceImpl implements UserService {

    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserMapper userMapper;
    @Resource(name = "RedisString")
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private MsgUtil msgUtil;

    /**
     * @根据用户账号密码查询用户信息
     * @Params:
     * @Return:
     *
    */
    @Override
    public String userLogin(String username, String password,HttpServletResponse response) {
        User user = userMapper.selectByUsername(username);
        UserDTO userDTO = new UserDTO();
        if (user!=null){
            if (user.getPassword().equals(password)){
                userDTO.setUsername(user.getUsername());
                userDTO.setPhone(user.getPhone());
                //生成token
                String token =null;
                try {
                    token=JWTUtil.generateTokenExpireMinutes(userDTO, 15, RSAUtil.acquirePrivate(RSAUtil.path + "\\private.txt"));
                    System.out.println(token);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return token;
            }
        }
        return null;
    }

    /**
     * @手机号登陆
     * @Params:
     * @Return:
     *
    */
    @Override
    public String userLoginByPhone(String phone,String code,HttpServletResponse response) {
        User user = userMapper.selectByPhone(phone);
        UserDTO userDTO = new UserDTO();
        if (user!=null){
            String codeRedis = redisTemplate.opsForValue().get("MsgCode:" + user.getPhone());
            if (code!=null&&code.equals(codeRedis)){
                userDTO.setUsername(user.getUsername());
                userDTO.setPhone(user.getPhone());
                //生成token
                String token =null;
                try {
                    token=JWTUtil.generateTokenExpireMinutes(userDTO, 15, RSAUtil.acquirePrivate(RSAUtil.path + "\\private.txt"));
                    System.out.println(token);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return token;
            }
        }
        return null;
    }

    /**
     * @根据用户信息进行用户注册
     * @Params:
     * @Return:
     *
    */
    @Override
    public boolean userRegister(User user,String validCode) {
        //判断用户名是否使用过
        User selectUser = userMapper.selectByUsername(user.getUsername());
        if (selectUser!=null){
            return false;
        }
        //短信验证手机号的正确性
        String code = redisTemplate.opsForValue().get("MsgCode:" + user.getPhone());
        if (code!=null&&code.equals(validCode)){
            int count = userMapper.insertUser(user);
            return count == 1;
        }
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
        String code = msgUtil.sendCode(phone);
        if (code==null){
            return false;
        }
        String str= redisTemplate.opsForValue().get("MsgCode:" + phone);
        if (str==null){
            redisTemplate.opsForValue().set("MsgCode:"+phone,code ,5 ,TimeUnit.MINUTES );
        }
        return true;
    }
}

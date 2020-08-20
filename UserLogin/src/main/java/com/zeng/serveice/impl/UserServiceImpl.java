package com.zeng.serveice.impl;

import com.zeng.mapper.UserMapper;
import com.zeng.pojo.po.User;
import com.zeng.serveice.UserService;
import com.zeng.utils.MsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-08-17
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
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
    public User userLogin(String username, String password) {

        User user = userMapper.selectUser(username);
        if (user!=null&&user.getPassword().equals(password)){

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
        String code = redisTemplate.opsForValue().get("MsgCode:" + user.getPhone());
        if (code!=null&&code.equals(validCode)){
            User selectUser = userMapper.selectUser(user.getUsername());
            if (selectUser!=null){
                return false;
            }
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
            redisTemplate.opsForValue().set("MsgCode:"+phone,code ,2 ,TimeUnit.MINUTES );
        }
        return true;
    }
}

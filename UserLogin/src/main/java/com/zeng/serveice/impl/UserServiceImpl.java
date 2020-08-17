package com.zeng.serveice.impl;

import com.zeng.mapper.UserMapper;
import com.zeng.pojo.po.User;
import com.zeng.serveice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

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
    private RedisTemplate redisTemplate;

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
    public boolean userRegister(String username, String password) {
        return false;
    }
}

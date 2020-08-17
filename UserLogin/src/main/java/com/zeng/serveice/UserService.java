package com.zeng.serveice;

import com.zeng.pojo.po.User;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-08-17
 **/
public interface UserService {

    User userLogin(String username,String password);

    boolean userRegister(String username,String password);
}

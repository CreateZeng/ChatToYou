package com.zeng.serveice;

import com.zeng.entry.dto.UserDTO;
import com.zeng.entry.po.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-08-17
 **/
public interface UserService {

    String userLoginByPassword(String username, String password);

    String userLoginByPhone(String phone,String code);

    boolean userRegister(User user);

    boolean sendVailCode(String phone);

    UserDTO userVerufy(HttpServletRequest request, HttpServletResponse response);
}

package com.zeng.serveice;

import com.zeng.pojo.dto.UserDTO;
import com.zeng.pojo.po.User;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-08-17
 **/
public interface UserService {

    String userLogin(String username, String password,HttpServletResponse response);

    String userLoginByPhone(String phone,String code,HttpServletResponse response);

    boolean userRegister(User user,String code);

    boolean sendVailCode(String phone);
}

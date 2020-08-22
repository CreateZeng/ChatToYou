package com.zeng.serveice;

import com.zeng.pojo.dto.UserDTO;
import com.zeng.pojo.po.User;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-08-17
 **/
public interface UserService {

    UserDTO userLogin(String username, String password);

    UserDTO userLoginByPhone(String phone,String code);

    boolean userRegister(User user,String code);

    boolean sendVailCode(String phone);
}

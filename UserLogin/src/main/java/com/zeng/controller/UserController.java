package com.zeng.controller;

import com.zeng.serveice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-08-17
 **/
@RestController
public class UserController {


    @Autowired
    private UserService userService;

    /**
     * @用户登录
     * @Params:
     * @Return:
     *
    */
    public void login(String username, @RequestParam(value = "password",required = false) String password,
                      @RequestParam(value = "phone",required = false)String phone,
                      @RequestParam(value = "code",required = false)String code, HttpServletResponse response){
        if (password==null){

        }else {

        }
    }

    public void register(String username, String password){

    }

    public void sendCode(String phone){

    }


}

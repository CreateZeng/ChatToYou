package com.zeng.controller;

import com.zeng.pojo.po.User;
import com.zeng.serveice.UserService;
import com.zeng.utils.ThreadMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
    @PostMapping("user/login")
    public void login(String username, @RequestParam(value = "password",required = false) String password,
                      @RequestParam(value = "phone",required = false)String phone,
                      @RequestParam(value = "code",required = false)String code,
                      HttpServletResponse response){
        if (password==null){

        }else {

        }
    }

    /**
     * @用户注册
     * @Params:
     * @Return:
     *
    */
    @PostMapping("user/register")
    public void register(@Valid User user,BindingResult result,@RequestParam("code")String validCode){
        if (result.hasErrors()){
            for (FieldError fieldError : result.getFieldErrors()) {
                System.out.println(fieldError);
            }
        }

    }

    /**
     * @短信验证
     * @Params:
     * @Return:
     *
    */
    @PostMapping("user/code")
    public void sendCode(@RequestParam("phone") String phone){

    }

    @GetMapping("user/verify")
    public void userVerify(){

    }

}

package com.zeng.controller;

import com.zeng.pojo.ReturnResult;
import com.zeng.pojo.dto.UserDTO;
import com.zeng.pojo.po.User;
import com.zeng.serveice.UserService;
import com.zeng.utils.RegexConstants;
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
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public ReturnResult login(String username, @RequestParam(value = "password",required = false) String password,
                      @RequestParam(value = "phone",required = false)String phone,
                      @RequestParam(value = "code",required = false)String code,
                      HttpServletResponse response){
        if (username!=null){
            UserDTO userDTO = userService.userLogin(username, password,code);
            if (userDTO==null){
                return ReturnResult.getFail("登陆失败");
            }
            return ReturnResult.getSuccess(userDTO);
        }else {
            return ReturnResult.getFail("登陆失败");
        }
    }

    /**
     * @用户注册
     * @Params:
     * @Return:
     *
    */
    @PostMapping("user/register")
    public ReturnResult register(@Valid User user,BindingResult result,@RequestParam(value = "code",required = true)String validCode){
        List<String> errorStr=null;
        if (result.hasErrors()){
            errorStr=new ArrayList<>();
            for (FieldError fieldError : result.getFieldErrors()) {
                errorStr.add(fieldError.getDefaultMessage());
            }
        }else if (user!=null){
            boolean flag = userService.userRegister(user,validCode);
            if (flag){
                return ReturnResult.getSuccess(null);
            }
        }
        return ReturnResult.getFail(errorStr);
    }

    /**
     * @短信验证
     * @Params:
     * @Return:
     *
    */
    @PostMapping("user/code")
    public ReturnResult sendCode(@RequestParam("phone") String phone){
        Matcher matcher = Pattern.compile(RegexConstants.PHONE_REGEX).matcher(phone);
        if (matcher.find()) {
            boolean flag = userService.sendVailCode(phone);
            if (flag) {
                return ReturnResult.getSuccess("发送成功");
            }
            return ReturnResult.getFail("发送失败");
        }else {
            return ReturnResult.getFail("手机号格式不正确");
        }
    }

    @GetMapping("user/verify")
    public void userVerify(){
        System.out.println("用户验证通过......");
    }

}

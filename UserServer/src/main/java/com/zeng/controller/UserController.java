package com.zeng.controller;

import com.zeng.entry.vo.ReturnResult;
import com.zeng.entry.dto.UserDTO;
import com.zeng.entry.po.User;
import com.zeng.serveice.UserService;
import com.zeng.utils.CookieUtil;
import com.zeng.utils.RegexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
    */
    @PostMapping("user/login")
    public ResponseEntity<ReturnResult> login(@RequestParam(value = "username",required = false)String username,
                                @RequestParam(value = "password",required = false) String password,
                                @RequestParam(value = "phone",required = false)String phone,
                                @RequestParam(value = "code",required = false)String code,
                                HttpServletResponse response){
        String token=null;
        if (username!=null){
            token = userService.userLoginByPassword(username,password);
            if (token==null){
                return ResponseEntity.ok(ReturnResult.getFail("登陆失败"));
            }
        }else if (phone!=null){
            token = userService.userLoginByPhone(phone,code);
            if (token==null){
                return ResponseEntity.ok(ReturnResult.getFail("登陆失败"));
            }
        }else {
            return ResponseEntity.ok(ReturnResult.getFail("登陆失败"));
        }
        //把token写入respone的Cookie中
        CookieUtil.CookieBuilder cookieBuilder = CookieUtil.createCookieBuilder("WS-TOKEN", token);
        cookieBuilder.domian("api.websocket.com").httpOnly(true).path("/").response(response).build();
        return ResponseEntity.ok(ReturnResult.getSuccess(token));
    }

    /**
     * @用户注册
     * @Params:
     * @Return:
    */
    @PostMapping("user/register")
    public ResponseEntity<ReturnResult> register(@Valid User user,BindingResult result,@RequestParam(value = "code",required = true)String validCode){
        //后端检验格式
        List<String> errorStr=new ArrayList<>();
        if (result.hasErrors()){
            for (FieldError fieldError : result.getFieldErrors()) {
                errorStr.add(fieldError.getDefaultMessage());
            }
        }else if (user!=null){
            boolean flag = userService.userRegister(user,validCode);
            if (flag){
                return ResponseEntity.ok(ReturnResult.getSuccess("注册成功"));
            }
        }
        return ResponseEntity.ok(ReturnResult.getFail(errorStr));
    }

    /**
     * @短信验证
     * @Params:
     * @Return:
    */
    @PostMapping("user/code")
    public ResponseEntity<ReturnResult> sendCode(@RequestParam("phone") String phone){
        //正则表达式验证手机号格式
        Matcher matcher = Pattern.compile(RegexUtil.PHONE_REGEX).matcher(phone);
        if (matcher.find()) {
            boolean flag = userService.sendVailCode(phone);
            if (flag) {
                return ResponseEntity.ok(ReturnResult.getSuccess("发送成功"));
            }
            return ResponseEntity.ok(ReturnResult.getFail("发送失败"));
        }else {
            return ResponseEntity.ok(ReturnResult.getFail("手机号格式不正确"));
        }
    }

    @GetMapping("user/verify")
    public ResponseEntity<ReturnResult> userVerify(HttpServletRequest request,HttpServletResponse response){
        UserDTO userDTO = userService.userVerufy(request, response);
        if (userDTO!=null){
            return ResponseEntity.ok(ReturnResult.getSuccess(userDTO));
        }
        return ResponseEntity.ok(ReturnResult.getFail("认证失败"));
    }
}

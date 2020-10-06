package com.zeng.controller;

import com.zeng.entry.dto.LoginDto;
import com.zeng.entry.vo.ReturnResult;
import com.zeng.entry.dto.UserDTO;
import com.zeng.entry.po.User;
import com.zeng.serveice.UserService;
import com.zeng.utils.CookieUtil;
import com.zeng.utils.RegexUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

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
@Api(value = "UserController",tags = "用户Controller",description = "用户登录注册以及验证") //使用在类上、描述该类的作用
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
    @ApiOperation(value = "用户登录页面",notes = "使用密码进行登录")    //增加方法的说明
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名",required = false,dataType = "String")
    })//@ApiImplicitParams注解一组参数、@ApiImplicitParam注解一个参数
    public ResponseEntity<ReturnResult> login(@RequestParam(value = "username",required = false)String username,
                                @RequestParam(value = "password",required = false) String password,
                                @RequestParam(value = "phone",required = false)String phone,
                                @RequestParam(value = "code",required = false)String code,
                                @RequestBody(required = false) LoginDto loginDto,
                                HttpServletResponse response){
        System.out.println("收到请求");
        if (loginDto!=null){
            username=loginDto.getUsername();
            password=loginDto.getPassword();
        }
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
    @ApiOperation(value = "用户注册",notes = "用户验证码登录")
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
    @ApiOperation(value = "发送短信",notes = "用户使用手机号发送短信")
    public ResponseEntity<ReturnResult> sendCode(@RequestBody LoginDto userDTO){
        //正则表达式验证手机号格式
        Matcher matcher = Pattern.compile(RegexUtil.PHONE_REGEX).matcher(userDTO.getPhone());
        if (matcher.find()) {
            boolean flag = userService.sendVailCode(userDTO.getPhone());
            if (flag) {
                return ResponseEntity.ok(ReturnResult.getSuccess("发送成功"));
            }
            return ResponseEntity.ok(ReturnResult.getFail("发送失败"));
        }else {
            return ResponseEntity.ok(ReturnResult.getFail("手机号格式不正确"));
        }
    }

    @GetMapping("user/verify")
    @ApiOperation(value = "用户身份验证",notes = "用户进行身份验证接口")
    public ResponseEntity<ReturnResult> userVerify(HttpServletRequest request,HttpServletResponse response){
        UserDTO userDTO = userService.userVerufy(request, response);
        if (userDTO!=null){
            return ResponseEntity.ok(ReturnResult.getSuccess(userDTO));
        }
        return ResponseEntity.ok(ReturnResult.getFail("认证失败"));
    }

    @GetMapping("user/login2")
    public ResponseEntity<ReturnResult> userlogin2(){
        System.out.println("请求成功");
        return ResponseEntity.ok(ReturnResult.getSuccess("登录成功"));
    }
}

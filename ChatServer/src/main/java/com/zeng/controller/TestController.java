package com.zeng.controller;/*
 * Package: com.zeng
 * Author: Mr.Z.J---🐎
 * Date: 2020-08-23
 * Desc:
 */

import com.zeng.pojo.ReturnResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("test/test")
    public ReturnResult test(){
        return ReturnResult.getSuccess(null);
    }
}

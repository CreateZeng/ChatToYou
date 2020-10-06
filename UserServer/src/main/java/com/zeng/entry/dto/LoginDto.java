package com.zeng.entry.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-09-04
 **/
@Data
public class LoginDto implements Serializable{

    private String username;

    private String password;

    private String phone;

    private String code;
}

package com.zeng.entry.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-08-17
 **/
@Data
public class UserDTO implements Serializable{

    private String username;
    private String phone;
}

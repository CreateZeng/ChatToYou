package com.zeng.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-08-21
 * @JWT的载荷实体类、用于存放用户信息
 **/
@Data
public class PayLoad<T> {

    private String id;

    private T userDto;

    private Date expiration;
}

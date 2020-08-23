package com.zeng.pojo;

import com.zeng.pojo.dto.UserDTO;
import lombok.Data;

import java.util.Date;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-08-21
 * @JWT的载荷实体类、用于存放用户信息
 **/
@Data
public class PayLoad{

    private String id;

    private UserDTO userDto;

    private Date expiration;
}

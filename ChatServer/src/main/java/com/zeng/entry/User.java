package com.zeng.entry;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-09-16
 **/
@Data
@Document(indexName = "user.index",type = "user")
public class User {

    @Id
    private Integer id;

    private String username;

    private String password;

    private String phone;
}

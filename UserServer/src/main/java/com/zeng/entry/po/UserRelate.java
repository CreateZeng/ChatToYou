package com.zeng.entry.po;/*
 * Package: com.zeng.entry.po
 * Author: Mr.Z.J---🐎
 * Date: 2020-08-26
 * Desc:
 */

import lombok.Data;

import java.util.Date;
@Data
public class UserRelate {

    private Integer id;

    private String username;

    private String friendName;

    private Date entrDate;

}

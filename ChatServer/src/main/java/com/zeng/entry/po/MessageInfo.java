package com.zeng.entry.po;/*
 * Package: com.zeng.entry.po
 * Author: Mr.Z.J---🐎
 * Date: 2020-08-26
 * Desc:
 */

import lombok.Data;

import java.util.Date;

@Data
public class MessageInfo {

    private Integer messageId;

    private String context;

    private Integer status;

    private String sendUid;

    private String receiveUid;

    private Date entryDate;

}

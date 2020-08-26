package com.zeng.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-08-19
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnResult implements Serializable{

    private Integer state;

    private String msg;

    private Object data;

    public static ReturnResult getSuccess(Object data){
        return new ReturnResult(1,"操作成功",data);
    }

    public static ReturnResult getFail(Object data){
        return new ReturnResult(0,"操作失败",data);
    }
}

package com.zeng.entry;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-09-18
 **/
@Data
@Document(indexName = "good.index",type = "good")
public class Goods{

    @Id
    public String id;

    private String name;

    private String title;
}

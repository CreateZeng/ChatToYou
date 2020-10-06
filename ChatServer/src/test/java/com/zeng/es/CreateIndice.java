package com.zeng.es;

import com.zeng.entry.User;
import com.zeng.service.UserSearch;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-09-10
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateIndice {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    UserSearch userSearch;

    //向索引库中添加数据
    @Test
    public void createInices(){
        User user = new User();
        user.setId(12);
        user.setUsername("test");
        userSearch.createIndics(user);
    }
}

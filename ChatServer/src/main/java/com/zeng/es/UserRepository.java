package com.zeng.es;

import com.zeng.entry.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-09-16
 **/
@Component          //对索引库的操作增删改
public interface UserRepository extends ElasticsearchRepository<User,Integer>{

}

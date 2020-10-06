package com.zeng.es;

import com.zeng.entry.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-09-18
 **/
@Component
public interface GoodRepository extends ElasticsearchRepository<Goods,String>{
}

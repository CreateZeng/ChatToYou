package com.zeng.service;

import com.zeng.dao.SpuMapper;
import com.zeng.entry.Goods;
import com.zeng.entry.Spu;
import com.zeng.entry.SpuExample;
import com.zeng.es.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-09-18
 **/
@Service
public class GoodService {

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private GoodRepository goodRepository;


    public void CreateIndic(){
        List<Spu> spus = spuMapper.selectByExample(null);
        for (Spu spu : spus) {
            Goods goods = new Goods();
            goods.setId(String.valueOf(spu.getId()));
            goods.setName(spu.getName());
            goods.setTitle(spu.getSubTitle());
            goodRepository.save(goods);
        }
    }
}

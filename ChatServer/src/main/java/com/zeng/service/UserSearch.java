package com.zeng.service;

import com.zeng.entry.User;
import com.zeng.es.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-09-16
 **/
@Service
public class UserSearch {

    @Autowired
    private UserRepository repository;

    public void createIndics(User user){
        repository.save(user);
    }

    public void deleteIndics(Integer id){
        repository.deleteById(id);
    }



}

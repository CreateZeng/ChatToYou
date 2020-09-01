package com.zeng.mapper;

import com.zeng.entry.po.User;
import com.zeng.entry.po.UserRelate;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-08-27
 **/
@Repository
public interface UserRelateMapper {

    @Results({
            @Result(column = "user_name",property = "username"),
            @Result(column = "",property = "")
    })

    int addFriend(String username);

    int deleteFreind(String username);

    List<UserRelate> selectFrrineds(String username);
}

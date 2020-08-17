package com.zeng.mapper;

import com.zeng.pojo.po.User;
import org.apache.ibatis.annotations.*;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Repository;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-08-17
 **/

@Repository
public interface UserMapper{

    /**
     * @映射关系
    */
    @Results(id = "userMap",value = {
            @Result(property = "id",column = "id"),
            @Result(property = "username",column = "user_name"),
            @Result(property = "password",column = "password")
    })

    /**
     * @查询用户信息
     * @Params: [username]
     * @Return: com.zeng.pojo.po.User
     *
    */

    @ResultMap("userMap")
    @Select("select * from tb_user where username=#{username}")
    User selectUser(String username);

    @Insert("Insert into user('username','password') values(#{username},#{password})")
    @Options(useGeneratedKeys = true,keyColumn = "id")
    int insertUser(User user);

}

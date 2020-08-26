package com.zeng.mapper;

import com.zeng.entry.po.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-08-17
 **/

@Repository
public interface UserMapper{

    /**
     * @根据用户名查询用户信息
     * @Params:
     * @Return:
     */
    @Results(id = "userMap",value = {
            @Result(property = "id",column = "id"),
            @Result(property = "username",column = "user_name"),
            @Result(property = "password",column = "password")
    })
    @Select("select * from tb_user where user_name=#{username}")
    User selectByUsername(String username);

    /**
     * @根据手机号查询用户信息
     * @param
     * @return
     */
    @ResultMap("userMap")
    @Select("select * from tb_user where phone=#{phone}")
    User selectByPhone(String phone);

    /**
     * @添加用户
     * @Params:
     * @Return:
    */
    @ResultMap("userMap")
    @Insert("insert into tb_user(user_name,password,phone) values(#{username},#{password},#{phone})")
    @Options(useGeneratedKeys = true,keyColumn = "id")
    int insertUser(User user);
}

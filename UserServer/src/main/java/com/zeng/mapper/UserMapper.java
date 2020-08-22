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
     * @根据用户名查询用户信息
     * @Params:
     * @Return:
     *
    */
    @Select("select * from tb_user where user_name=#{username}")
    User selectByUsername(String username);

    /**
     * @根据手机号查询用户信息
     * @param phone
     * @return
     */
    @Select("select * from tb_user where user_name=#{phone}")
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

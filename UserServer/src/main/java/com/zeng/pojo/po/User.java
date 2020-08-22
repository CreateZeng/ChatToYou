package com.zeng.pojo.po;

import com.zeng.utils.RegexConstants;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-08-17
 **/
@Data
public class User {

    private Integer id;
    @Pattern(regexp = RegexConstants.USERNAME_REGEX,message = "用户名格式不正确")
    private String username;
    @Length(max = 8,min = 4,message = "用户密码格式不正确")
    private String password;
    @Pattern(regexp = RegexConstants.PHONE_REGEX, message = "手机号格式不正确")
    private String phone;

}

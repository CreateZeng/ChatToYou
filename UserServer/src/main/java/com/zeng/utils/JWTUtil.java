package com.zeng.utils;

import com.alibaba.fastjson.JSON;
import com.zeng.pojo.PayLoad;
import com.zeng.pojo.dto.UserDTO;
import io.jsonwebtoken.*;
import org.joda.time.DateTime;

import javax.validation.Payload;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.UUID;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-08-21
 **/
public class JWTUtil {

    private static final String JWT_PAYLOAD_USER_KEY = "user";   //载荷名称

    public String generateTokenExpireMinutes(UserDTO userDTO,int expire,PrivateKey privateKey){
        String userInfo = JSON.toJSONString(userDTO);
        //生成JWTToken、并返回
        return Jwts.builder()
                .claim(JWT_PAYLOAD_USER_KEY, userInfo)    //载荷
                .setId(CreateJwtId())       //tokenId
                .setExpiration(DateTime.now().plusMinutes(expire).toDate())   //有效时间
                .signWith(privateKey, SignatureAlgorithm.RS256)    //加密
                .compact();
    }

    private String CreateJwtId(){
        return new String(Base64.getEncoder().encode(UUID.randomUUID().toString().getBytes()));
    }

    public static <T> PayLoad<T> analysisJWTToken(String token, PublicKey publicKey, Class<T> userType){
        //获取载荷信息
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        //组装成Payload返回
        PayLoad<T> userDTOPayLoad= new PayLoad<T>();
        return userDTOPayLoad;
    }
}

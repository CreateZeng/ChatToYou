package com.zeng.utils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeng.entry.vo.PayLoad;
import com.zeng.entry.dto.UserDTO;
import io.jsonwebtoken.*;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
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

    private static Logger logger= LoggerFactory.getLogger(JWTUtil.class);

    private static final String JWT_PAYLOAD_USER_KEY = "JWT-PAYLOAD";   //载荷名称

    /**
     * @生成JWTToken字符串
     * @Params:
     * @Return:
     *
    */
    public static String generateTokenExpireMinutes(UserDTO userDTO,int expire,PrivateKey privateKey){
        String userInfo = JSON.toJSONString(userDTO);
        //生成JWTToken、并返回
        return Jwts.builder()
                .claim(JWT_PAYLOAD_USER_KEY, userInfo)    //载荷
                .setId(CreateJwtId())       //tokenId
                .setExpiration(DateTime.now().plusMinutes(expire).toDate())   //有效时间
                .signWith(privateKey, SignatureAlgorithm.RS256)    //加密
                .compact();
    }

    /**
     * @获取BASE64编码的ID字符串
     * @Params:
     * @Return:
     *
    */
    private static String CreateJwtId(){
        return new String(Base64.getEncoder().encode(UUID.randomUUID().toString().getBytes()));
    }

    /**
     * @解析Token、获取载荷信息
     * @Params:
     * @Return:
     *
    */
    public static PayLoad analysisJWTToken(String token, PublicKey publicKey){
        logger.info("开始解析JWTToken........");
        //获取载荷信息
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        //组装成Payload返回
        PayLoad userDTOPayLoad= new PayLoad();
        //获取Id
        userDTOPayLoad.setId(new String(Base64.getDecoder().decode(claims.getId())));
        //获取userDto
        String userInfo = (String)claims.get(JWT_PAYLOAD_USER_KEY);
        logger.info("获取用户信息字符串"+userInfo);
        try {
            //ObjectMapper将json字符串映射成相应的对象
            if (userInfo!=null){
                userDTOPayLoad.setUserDto(new ObjectMapper().readValue(userInfo,UserDTO.class));
            }else {
                userDTOPayLoad.setUserDto(null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("解析JWTToken完毕.........");
        return userDTOPayLoad;
    }

    public static void main(String[] args) {
        System.out.println(DateTime.now().plusMinutes(15).toDate());
    }
}

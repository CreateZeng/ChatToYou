package com.zeng.utils;


import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.zeng.config.MsgProperties;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.Random;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-08-20
 **/
@Component
public class MsgUtil {

    @Autowired
    private MsgProperties msgProperties;    //请求参数属性类


    public String sendCode(String phone){
        //配置IAcsClient的属性
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", msgProperties.getAccessKeyId(), msgProperties.getAccessKeySecret());
        IAcsClient client = new DefaultAcsClient(profile);
        //创建请求
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);            //请求方式
        request.setSysDomain("dysmsapi.aliyuncs.com");    //域名
        request.setSysVersion("2017-05-25");              //API版本
        request.setSysAction("SendSms");                  //服务名
        request.putQueryParameter("RegionId", "cn-hangzhou");   //地域ID
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", msgProperties.getSignName());
        request.putQueryParameter("TemplateCode", msgProperties.getTemplateCode());
        //添加验证码信息
        String value = RandomStringUtils.randomNumeric(6);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",value );
        String codeJson = jsonObject.toJSONString();
        request.putQueryParameter("TemplateParam", codeJson);
        //发送请求
        try {
            CommonResponse response = client.getCommonResponse(request);
            JSONObject responseJson = JSONObject.parseObject(response.getData());
            if ("OK".equals(responseJson.get("Code"))) {
                return value;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
}

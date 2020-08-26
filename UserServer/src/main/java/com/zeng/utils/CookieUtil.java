package com.zeng.utils;

import lombok.Data;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-08-26
 **/
@Data
public class CookieUtil {


    public static CookieBuilder createCookieBuilder(String name,String value){
        return new CookieBuilder(name,value);
    }
    /**
     * @静态内部类进行cookie的创建
     * @Params:
     * @Return:
    */
    public static class CookieBuilder{

        private String domain;    //域名 可实现跨域Cookie共享

        private boolean httpOnly=false;     //只读模式、

        private String path;      //设置 / 可实现同项目cookie共享

        private String name;      //cookie名字

        private String value;     //cookie值

        private int MaxTime;      //有效时间

        private HttpServletResponse response;      //响应

        private CookieBuilder(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public CookieBuilder domian(String domain){
            this.domain=domain;
            return this;
        }

        public CookieBuilder httpOnly(boolean httpOnly){
            this.httpOnly=httpOnly;
            return this;
        }

        public CookieBuilder path(String path){
            this.path=path;
            return this;
        }

        public CookieBuilder response(HttpServletResponse response){
            this.response=response;
            return this;
        }

        public CookieBuilder MaxTime(int MaxTime){
            this.MaxTime=MaxTime;
            return this;
        }

        public void build(){
            Cookie cookie = new Cookie(name, value);
            if (StringUtils.isNotBlank(domain)){
                cookie.setDomain(domain);
            }
            if (StringUtils.isNotBlank(path)){
                cookie.setPath(path);
            }
            if (MaxTime>0){
                cookie.setMaxAge(MaxTime);
            }
            cookie.setHttpOnly(httpOnly);
            if (response!=null){
                response.addCookie(cookie);
            }else{
                throw new RuntimeException("response is null.....");
            }
        }

        public Cookie getBaseCookie(){
            Cookie cookie = new Cookie(name, value);
            if (StringUtils.isNotBlank(domain)){
                cookie.setDomain(domain);
            }
            if (StringUtils.isNotBlank(path)){
                cookie.setPath(path);
            }
            cookie.setHttpOnly(httpOnly);
            return cookie;
        }
    }
}

package com.zeng.utils;

/**
 * @ThredLocal线程的局部变量、线程独有、内含一个静态内部类的Map用于存放线程的值
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-08-18
 **/
public class ThreadMap {

    private static final ThreadLocal<String> TL=new ThreadLocal<>();

    public static void setUserId(String userId){
        TL.set(userId);
    }

    public static String getUserId() {
        return TL.get();
    }

    public static void removeUserId() {
        TL.remove();
    }
}

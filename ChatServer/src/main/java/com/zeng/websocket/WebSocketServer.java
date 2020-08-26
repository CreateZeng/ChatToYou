package com.zeng.websocket;

import com.zeng.config.HttpSessionConfigurator;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-08-17
 **/
@Component
@ServerEndpoint(value = "/ws/server",configurator = HttpSessionConfigurator.class)
public class WebSocketServer {

    private Session session;

    private HttpSession httpSession;

    private static ConcurrentHashMap concurrentHashMap=new ConcurrentHashMap();

    /**
     * @建立连接
     * @Params:
     * @Return:
     *
    */
    @OnOpen
    public void onOpen(Session session,EndpointConfig config){
        this.session=session;
        this.httpSession=(HttpSession)config.getUserProperties().get(HttpSession.class.getName());
    }

    @OnMessage
    public void onMessage(String msg,Session session){

    }

    @OnClose
    public void onClose(Session session){

    }

}

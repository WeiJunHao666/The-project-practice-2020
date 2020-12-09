package com.erhuo.websocket;


import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/chat/{userId}/{toUserId}")
public class WebSocketService {

    private static int onlineCount = 0;  //记录连接人数
    //通过key值去找寻对应的session
    private static Map<Integer, WebSocketService> clients = new ConcurrentHashMap<>();
    private Session session;//记录登录人的session
    private int userId;

    /**
     * 打开连接，保存连接的用户
     * @param session
     * @throws IOException
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") int userId) throws IOException {
        this.session = session;
        this.userId = userId;
        addOnlineCount();
        System.out.println(userId + "连接成功");
        clients.put(userId, this);
    }

    /**
     * 关闭连接，删除用户
     * @throws IOException
     */
    @OnClose
    public void onClose() throws IOException {
        clients.remove(this.userId);
        subOnlineCount();
        System.out.println(this.userId + "离开");
    }

    /**
     * 发送消息
     * @param message
     * @throws IOException
     */
    @OnMessage
    public void onMessage(String message,@PathParam("toUserId") int toUserId) throws IOException {
        clients.get(toUserId).session.getAsyncRemote().sendText(message);
    }

    /**
     * 错误打印
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /**
     * 通过session群聊发送消息
     * @param message
     * @throws IOException
     */
//    public void sendMessageAll(String message,@PathParam("toUserId") int toUserId) throws IOException {
//        clients.get(toUserId).session.getAsyncRemote().sendText("message");
//        for (WebSocketService item : clients.values()) {
//            item.session.getAsyncRemote().sendText(message);
//        }
//    }

    /**
     * 获取当前在线人数，线程安全
     * @return
     */
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    /**
     * 添加当前在线人数
     */
    public static synchronized void addOnlineCount() {
        WebSocketService.onlineCount++;
    }

    /**
     * 减少当前在线人数
     */
    public static synchronized void subOnlineCount() {
        WebSocketService.onlineCount--;
    }

    /**
     *
     * @return
     */
    public static synchronized Map<Integer, WebSocketService> getClients() {
        return clients;
    }

}
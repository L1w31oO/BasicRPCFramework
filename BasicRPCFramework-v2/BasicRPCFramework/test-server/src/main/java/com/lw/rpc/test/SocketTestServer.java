package com.lw.rpc.test;

import com.lw.rpc.api.HelloService;
import com.lw.rpc.register.DefaultServiceRegistry;
import com.lw.rpc.register.ServiceRegistry;
import com.lw.rpc.transport.socket.server.SocketServer;

/**
 * Socket测试用服务端
 */
public class SocketTestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ServiceRegistry serviceRegistry = new DefaultServiceRegistry();
        serviceRegistry.register(helloService);
        SocketServer socketServer = new SocketServer(serviceRegistry);
        socketServer.start(9999);
    }
}

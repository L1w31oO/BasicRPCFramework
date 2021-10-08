package com.lw.rpc.test;

import com.lw.rpc.api.HelloService;
import com.lw.rpc.register.DefaultServiceRegistry;
import com.lw.rpc.register.ServiceRegistry;
import com.lw.rpc.transport.RpcServer;

/**
 * 测试用服务端
 */
public class TestServer {

    public static void main(String[] args) {
        //创建服务对象
        HelloService helloService = new HelloServiceImpl();
        //创建服务容器
        ServiceRegistry serviceRegistry = new DefaultServiceRegistry();
        //注册服务对象到服务容器中
        serviceRegistry.register(helloService);
        //将服务容器纳入到服务端
        RpcServer rpcServer = new RpcServer(serviceRegistry);
        //启动服务端
        rpcServer.start(9000);
    }
}

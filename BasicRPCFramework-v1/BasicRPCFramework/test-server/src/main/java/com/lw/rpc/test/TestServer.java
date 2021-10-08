package com.lw.rpc.test;

import com.lw.rpc.api.HelloService;
import com.lw.rpc.transport.RpcServer;

/**
 * 试用服务端
 */
public class TestServer {

    public static void main(String[] args) {
        // 引入接口实现类HelloServiceImpl
        HelloService helloService = new HelloServiceImpl();
        // 引入RpcServer服务端
        RpcServer rpcServer = new RpcServer();
        //注册HelloServiceImpl服务
        rpcServer.register(helloService, 9000);
    }
}

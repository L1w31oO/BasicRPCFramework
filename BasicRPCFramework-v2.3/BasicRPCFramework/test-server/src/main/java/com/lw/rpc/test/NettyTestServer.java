package com.lw.rpc.test;

import com.lw.rpc.api.HelloService;
import com.lw.rpc.serializer.ProtostuffSerializer;
import com.lw.rpc.transport.netty.server.NettyServer;
import com.lw.rpc.register.DefaultServiceRegistry;
import com.lw.rpc.register.ServiceRegistry;

/**
 * Netty测试用服务端
 */
public class NettyTestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ServiceRegistry registry = new DefaultServiceRegistry();
        registry.register(helloService);
        NettyServer server = new NettyServer();
//        server.setSerializer(new JsonSerializer());
//        server.setSerializer(new KryoSerializer());
//        server.setSerializer(new HessianSerializer());
        server.setSerializer(new ProtostuffSerializer());
        server.start(9999);
    }
}
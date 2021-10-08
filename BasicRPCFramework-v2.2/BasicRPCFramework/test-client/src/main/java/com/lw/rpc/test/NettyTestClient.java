package com.lw.rpc.test;

import com.lw.rpc.transport.RpcClient;
import com.lw.rpc.transport.RpcClientProxy;
import com.lw.rpc.api.HelloObject;
import com.lw.rpc.api.HelloService;
import com.lw.rpc.transport.netty.client.NettyClient;

/**
 * Netty测试用客户端
 */
public class NettyTestClient {
    public static void main(String[] args) {
        RpcClient client = new NettyClient("127.0.0.1", 9999);
        RpcClientProxy rpcClientProxy = new RpcClientProxy(client);
        HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(2, "this is a netty test message");
        String res = helloService.hello(object);
        System.out.println(res);
    }
}
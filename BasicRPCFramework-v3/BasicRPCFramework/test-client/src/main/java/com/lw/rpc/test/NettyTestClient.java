package com.lw.rpc.test;

import com.lw.rpc.api.ByeService;
import com.lw.rpc.serializer.CommonSerializer;
import com.lw.rpc.transport.RpcClient;
import com.lw.rpc.transport.RpcClientProxy;
import com.lw.rpc.api.HelloObject;
import com.lw.rpc.api.HelloService;
import com.lw.rpc.transport.netty.client.NettyClient;

/**
 * 测试用Netty客户端
 */
public class NettyTestClient {
    public static void main(String[] args) {
        RpcClient client = new NettyClient(CommonSerializer.PROTOBUF_SERIALIZER);
        RpcClientProxy rpcClientProxy = new RpcClientProxy(client);
        HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(2, "this is a netty test message");
        String res = helloService.hello(object);
        System.out.println(res);
        ByeService byeService = rpcClientProxy.getProxy(ByeService.class);
        System.out.println(byeService.bye("Netty"));
    }
}
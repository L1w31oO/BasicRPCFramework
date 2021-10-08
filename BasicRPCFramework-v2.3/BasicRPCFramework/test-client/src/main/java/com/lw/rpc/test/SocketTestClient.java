package com.lw.rpc.test;

import com.lw.rpc.serializer.HessianSerializer;
import com.lw.rpc.serializer.ProtostuffSerializer;
import com.lw.rpc.transport.RpcClientProxy;
import com.lw.rpc.api.HelloObject;
import com.lw.rpc.api.HelloService;
import com.lw.rpc.transport.socket.client.SocketClient;

/**
 * Socket测试用客户端
 */
public class SocketTestClient {

    public static void main(String[] args) {
        SocketClient client = new SocketClient("127.0.0.1", 9999);
//        client.setSerializer(new JsonSerializer());
//        client.setSerializer(new KryoSerializer());
//        client.setSerializer(new HessianSerializer());
        client.setSerializer(new ProtostuffSerializer());
        //接口与代理对象之间的中介对象
        RpcClientProxy proxy = new RpcClientProxy(client);
        //创建代理对象
        HelloService helloService = proxy.getProxy(HelloService.class);
        //接口方法的参数对象
        HelloObject object = new HelloObject(1, "This is a socket test message");
        //由动态代理可知，代理对象调用hello()实际会执行invoke()
        String res = helloService.hello(object);
        System.out.println(res);
    }
}

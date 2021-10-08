package com.lw.rpc.test;

import com.lw.rpc.api.ByeService;
import com.lw.rpc.loadbalancer.RoundRobinLoadBalancer;
import com.lw.rpc.serializer.CommonSerializer;
import com.lw.rpc.serializer.KryoSerializer;
import com.lw.rpc.transport.RpcClientProxy;
import com.lw.rpc.api.HelloObject;
import com.lw.rpc.api.HelloService;
import com.lw.rpc.transport.socket.client.SocketClient;

/**
 * 测试用Socket客户端
 */
public class SocketTestClient {

    public static void main(String[] args) {
        SocketClient client = new SocketClient(CommonSerializer.KRYO_SERIALIZER, new RoundRobinLoadBalancer());
        //接口与代理对象之间的中介对象
        RpcClientProxy proxy = new RpcClientProxy(client);
        //创建代理对象
        HelloService helloService = proxy.getProxy(HelloService.class);
        //接口方法的参数对象
        HelloObject object = new HelloObject(1, "this is a socket test message");
        //由动态代理可知，代理对象调用hello()实际会执行invoke()
        String res = helloService.hello(object);
        System.out.println(res);
        ByeService byeService = proxy.getProxy(ByeService.class);
        System.out.println(byeService.bye("Socket"));
    }
}

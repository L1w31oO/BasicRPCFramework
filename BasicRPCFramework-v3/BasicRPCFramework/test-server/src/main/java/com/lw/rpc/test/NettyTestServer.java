package com.lw.rpc.test;

import com.lw.rpc.annotation.ServiceScan;
import com.lw.rpc.serializer.CommonSerializer;
import com.lw.rpc.transport.RpcServer;
import com.lw.rpc.transport.netty.server.NettyServer;

/**
 * 测试用Netty服务端
 */
@ServiceScan
public class NettyTestServer {
    public static void main(String[] args) {
        RpcServer server = new NettyServer("127.0.0.1", 9999, CommonSerializer.PROTOBUF_SERIALIZER);
        server.start();
    }
}
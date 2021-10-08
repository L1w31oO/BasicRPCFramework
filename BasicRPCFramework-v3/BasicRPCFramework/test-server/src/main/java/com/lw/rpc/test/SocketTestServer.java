package com.lw.rpc.test;

import com.lw.rpc.annotation.ServiceScan;
import com.lw.rpc.serializer.CommonSerializer;
import com.lw.rpc.transport.RpcServer;
import com.lw.rpc.transport.socket.server.SocketServer;

/**
 * 测试用Socket服务端
 */
@ServiceScan
public class SocketTestServer {
    public static void main(String[] args) {
        RpcServer server = new SocketServer("127.0.0.1", 9998, CommonSerializer.KRYO_SERIALIZER);
        server.start();
    }
}

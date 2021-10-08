package com.lw.rpc.transport.socket.server;

import com.lw.rpc.entity.RpcRequest;
import com.lw.rpc.entity.RpcResponse;
import com.lw.rpc.handler.RequestHandler;
import com.lw.rpc.register.ServiceRegistry;
import com.lw.rpc.serializer.CommonSerializer;
import com.lw.rpc.transport.socket.util.ObjectReader;
import com.lw.rpc.transport.socket.util.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

/**
 * IO传输模式,处理客户端RpcRequest的工作线程
 */
public class RequestHandlerThread implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(RequestHandlerThread.class);
    private Socket socket;
    private RequestHandler requestHandler;
    private ServiceRegistry serviceRegistry;
    private CommonSerializer serializer;

    public RequestHandlerThread(Socket socket, RequestHandler requestHandler, ServiceRegistry serviceRegistry, CommonSerializer serializer) {
        this.socket = socket;
        this.requestHandler = requestHandler;
        this.serviceRegistry = serviceRegistry;
        this.serializer = serializer;
    }
    @Override
    public void run() {
        try(InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream()) {
            RpcRequest rpcRequest = (RpcRequest) ObjectReader.readObject(inputStream);
            String interfaceName = rpcRequest.getInterfaceName();
            Object service = serviceRegistry.getService(interfaceName);
            Object response = requestHandler.handle(rpcRequest, service);
            ObjectWriter.writeObject(outputStream, response, serializer);
        } catch (IOException e){
            logger.info("调用或发送时发生错误：" + e);
        }
    }
}

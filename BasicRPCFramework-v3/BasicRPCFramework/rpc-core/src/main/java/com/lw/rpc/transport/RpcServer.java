package com.lw.rpc.transport;

import com.lw.rpc.serializer.CommonSerializer;

/**
 * 服务端类通过接口
 */
public interface RpcServer {

    int DEFAULT_SERIALIZER = CommonSerializer.KRYO_SERIALIZER;

    void start();

    /**
     * 向Nacos注册服务
     */
    <T> void publishService(T service, String serviceName);
}
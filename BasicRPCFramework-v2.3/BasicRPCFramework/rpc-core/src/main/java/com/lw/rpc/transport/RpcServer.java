package com.lw.rpc.transport;

import com.lw.rpc.serializer.CommonSerializer;

/**
 * 服务端类通用接口
 */
public interface RpcServer {

    void start(int port);

    void setSerializer(CommonSerializer serializer);

}
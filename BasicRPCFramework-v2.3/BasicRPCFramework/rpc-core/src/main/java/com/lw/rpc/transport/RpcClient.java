package com.lw.rpc.transport;

import com.lw.rpc.entity.RpcRequest;
import com.lw.rpc.serializer.CommonSerializer;

/**
 * 客户端类通用接口
 */
public interface RpcClient {

    Object sendRequest(RpcRequest rpcRequest);

    void setSerializer(CommonSerializer serializer);

}

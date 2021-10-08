package com.lw.rpc.transport;

import com.lw.rpc.entity.RpcRequest;

/**
 * 客户端类通用接口
 */
public interface RpcClient {

    Object sendRequest(RpcRequest rpcRequest);

}

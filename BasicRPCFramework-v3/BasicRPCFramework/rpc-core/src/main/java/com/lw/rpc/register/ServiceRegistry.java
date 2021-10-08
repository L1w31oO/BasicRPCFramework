package com.lw.rpc.register;

import java.net.InetSocketAddress;

/**
 * 服务发现接口
 */
public interface ServiceRegistry {
    /**
     * 将一个服务注册到注册表
     */
    void register(String serviceName, InetSocketAddress inetSocketAddress);
}
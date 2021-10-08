package com.lw.rpc.register;

import java.net.InetSocketAddress;

/**
 * 服务发现接口
 */
public interface ServiceDiscovery {
    /**
     * 根据服务名称查找服务端地址
     */
    InetSocketAddress lookupService(String serviceName);
}

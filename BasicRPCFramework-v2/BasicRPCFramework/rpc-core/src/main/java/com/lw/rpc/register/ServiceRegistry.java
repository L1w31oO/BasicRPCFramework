package com.lw.rpc.register;

public interface ServiceRegistry {
    /**
     * 将一个服务注册进注册表
     */
    <T> void register(T service);

    /**
     * 根据服务名获取服务实体
     */
    Object getService(String serviceName);
}

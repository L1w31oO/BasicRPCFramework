package com.lw.rpc.loadbalancer;

import com.alibaba.nacos.api.naming.pojo.Instance;

import java.util.List;

/**
 * 负载均衡接口
 */
public interface LoadBalancer {

    /**
     * 从一系列Instance中选择一个
     */
    Instance select(List<Instance> instances);

}
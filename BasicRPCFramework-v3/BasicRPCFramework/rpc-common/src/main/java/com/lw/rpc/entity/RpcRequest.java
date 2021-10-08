package com.lw.rpc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * 传输格式（传输协议）：客户端向服务端传输的对象
 */
//自动加上所有属性的get set toString hashCode equals方法
@Data
// 无参构造
@NoArgsConstructor
//添加一个含有所有已声明字段属性参数的构造函数
@AllArgsConstructor
public class RpcRequest implements Serializable {

    // 请求号
     private String requestId;

    // 待调用接口名称
    private String interfaceName;

    // 待调用方法名称
    private String methodName;

    // 待调用方法的参数
    private Object[] parameters;

    // 待调用方法的参数类型
    private Class<?>[] paramTypes;

    // 是否是心跳包
    private Boolean heartBeat;
}

package com.lw.rpc.test;

import com.lw.rpc.annotation.Service;
import com.lw.rpc.api.HelloObject;
import com.lw.rpc.api.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务端api接口实现
 */
 @Service
public class HelloServiceImpl implements HelloService {

    // 使用HelloServiceImpl初始化日志对象，方便在日志输出的时候，可以打印出日志信息所属的类。
    private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public String hello(HelloObject object) {
        // 使用{}可以直接将getMessage()内容输出
        logger.info("接收到消息：{}", object.getMessage());
        return "成功调用hello()方法";
    }

}

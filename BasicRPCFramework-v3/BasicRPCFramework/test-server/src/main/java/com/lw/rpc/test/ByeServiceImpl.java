package com.lw.rpc.test;

import com.lw.rpc.annotation.Service;
import com.lw.rpc.api.ByeService;

/**
 * 服务端api接口实现
 */
@Service
public class ByeServiceImpl implements ByeService {

    @Override
    public String bye(String name) {
        return "bye," + name;
    }

}
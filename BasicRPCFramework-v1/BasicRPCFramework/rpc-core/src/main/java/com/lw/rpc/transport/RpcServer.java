package com.lw.rpc.transport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * 进行远程调用连接的服务端
 */
public class RpcServer {

    private static final Logger logger = LoggerFactory.getLogger(RpcServer.class);
    private final ExecutorService threadPool;

    public RpcServer(){
        // 线程池的基本大小，即在没有任务需要执行的时候线程池的大小，
        // 并且只有在工作队列满了的情况下才会创建超出这个数量的线程。
        int corePoolSize = 5;
        // 线程池中允许的最大线程数，线程池中的当前线程数目不会超过该值。
        // 如果队列中任务已满，并且当前线程个数小于maximumPoolSize，那么会创建新的线程来执行任务。
        int maximumPoolSize = 50;
        // 线程池中空闲线程等待工作的超时时间,单位：TimeUnit.SECONDS
        long keepAliveTime = 60;
        // 设置上限为100个线程的阻塞队列
        BlockingQueue<Runnable> workingQueue = new ArrayBlockingQueue<>(100);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        // 创建线程池实例
        threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workingQueue, threadFactory);
    }

    /**
     * 服务注册，由于是简化实现，服务端只提供一个接口的调用服务，即HelloServiceImpl
     * 注册完一个服务后立刻开始监听
     */
    public void register(Object service, int port){
        try(ServerSocket serverSocket = new ServerSocket(port)){
            logger.info("服务器正在启动……");
            Socket socket;
            // 当未接收到连接请求时，accept()会一直阻塞
            while ((socket = serverSocket.accept()) != null){
                logger.info("客户端连接！IP：" + socket.getInetAddress());
                threadPool.execute(new WorkerThread(socket, service));
            }
        } catch (IOException e){
            logger.info("连接时有错误发生：" + e);
        }
    }
}
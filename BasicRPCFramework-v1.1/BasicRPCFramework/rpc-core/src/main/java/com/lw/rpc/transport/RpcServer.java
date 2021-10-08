package com.lw.rpc.transport;

import com.lw.rpc.handler.RequestHandler;
import com.lw.rpc.handler.RequestHandlerThread;
import com.lw.rpc.register.ServiceRegistry;
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
    // 线程池的基本大小，即在没有任务需要执行的时候线程池的大小，
    // 并且只有在工作队列满了的情况下才会创建超出这个数量的线程。
    private static final int CORE_POOL_SIZE = 5;
    // 线程池中允许的最大线程数，线程池中的当前线程数目不会超过该值。
    // 如果队列中任务已满，并且当前线程个数小于maximumPoolSize，那么会创建新的线程来执行任务。
    private static final int MAXIMUM_POOL_SIZE = 50;
    // 线程池中空闲线程等待工作的超时时间,单位：TimeUnit.SECONDS
    private static final int KEEP_ALIVE_TIME = 60;
    // 阻塞队列的上限为100个线程
    private static final int BLOCKING_QUEUE_CAPACITY = 100;
    private final ExecutorService threadPool;
    private RequestHandler requestHandler = new RequestHandler();
    private final ServiceRegistry serviceRegistry;

    public RpcServer(ServiceRegistry serviceRegistry){
        this.serviceRegistry = serviceRegistry;
        // 设置上限为100个线程的阻塞队列
        BlockingQueue<Runnable> workingQueue = new ArrayBlockingQueue<>(BLOCKING_QUEUE_CAPACITY);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        // 创建线程池实例
        threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, workingQueue, threadFactory);
    }

    /**
     * 服务注册
     */
    public void start(int port){
        try(ServerSocket serverSocket = new ServerSocket(port)){
            logger.info("服务器启动……");
            Socket socket;
            //当未接收到连接请求时，accept()会一直阻塞
            while ((socket = serverSocket.accept()) != null){
                logger.info("客户端连接！{}:{}", socket.getInetAddress(), socket.getPort());
                threadPool.execute(new RequestHandlerThread(socket, requestHandler, serviceRegistry));
            }
            threadPool.shutdown();
        } catch (IOException e){
            logger.info("服务器启动时有错误发生：" + e);
        }
    }
}
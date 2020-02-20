package com.wangdong.multithreadprogram.shizhanzhinan.chapterthree;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author wangdong
 * @description: 3-21
 */
public class Indicator {
    /**
     * 保存当前类的唯一实例
     */
    private static final Indicator INSTANCE = new Indicator();
    /**
     * 记录请求总数
     */
    private final AtomicLong requestCount = new AtomicLong(0);
    /**
     * 记录处理成功总数
     */
    private final AtomicLong successCount = new AtomicLong(0);
    /**
     * 记录处理失败总数
     */
    private final AtomicLong failureCount = new AtomicLong(0);

    private Indicator() {

    }

    /**
     * 返回该类的唯一实例
     *
     * @return
     */
    private static Indicator getInstance() {
        return INSTANCE;
    }

    public void newRequestReceived() {
        //-----总请求数 +1
        requestCount.incrementAndGet();
    }

    public void newRequestProcessed() {
        //-----总成功数 +1
        successCount.incrementAndGet();
    }

    public void requestProcessedFailed() {
        //-----总失败数 +1
        failureCount.incrementAndGet();
    }

    public long getRequestCount() {
        return requestCount.get();
    }

    public long getSuccessCount() {
        return successCount.get();
    }

    public long getFailureCount() {
        return failureCount.get();
    }

    public void reset(){
        requestCount.set(0);
        successCount.set(0);
        failureCount.set(0);
    }







}

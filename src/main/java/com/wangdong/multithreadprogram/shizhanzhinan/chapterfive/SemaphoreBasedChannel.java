package com.wangdong.multithreadprogram.shizhanzhinan.chapterfive;

import com.wangdong.multithreadprogram.shizhanzhinan.service.Channel;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * @description: 5-11
 * @author: wangdong
 * @date: 2020/2/27 10:09
 */
public class SemaphoreBasedChannel<P> implements Channel<P> {
    private final BlockingQueue<P> queue;
    private final Semaphore semaphore;

    public SemaphoreBasedChannel(BlockingQueue<P> queue, int flowLimit) {
        this(queue, flowLimit, false);
    }

    public SemaphoreBasedChannel(BlockingQueue<P> queue, int flowLimit, boolean isFair) {
        this.queue = queue;
        this.semaphore = new Semaphore(flowLimit, isFair);
    }

    @Override
    public void put(P product) throws InterruptedException {
        semaphore.acquire();
        try{
            queue.put(product);
        }finally {
            semaphore.release();
        }
    }

    @Override
    public P take() throws InterruptedException {
        return queue.take();
    }
}

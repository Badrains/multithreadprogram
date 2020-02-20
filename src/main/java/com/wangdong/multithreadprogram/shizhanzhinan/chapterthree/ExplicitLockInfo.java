package com.wangdong.multithreadprogram.shizhanzhinan.chapterthree;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangdong
 */
@Slf4j
public class ExplicitLockInfo {
    private static final Lock lock = new ReentrantLock();
    private static int sharedData = 0;

    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    try {
                        Thread.sleep(220000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sharedData = 1;
                } finally {
                    lock.unlock();
                }

            }
        });
        t.start();
        lock.lock();
        try {
            log.info("sharedData:{}", sharedData);
        }finally {
            lock.unlock();
        }
    }
}

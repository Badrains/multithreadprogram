package com.wangdong.multithreadprogram.shizhanzhinan.chapterfive;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 5-3
 * @author: wangdong
 * @date: 2020/2/24 10:09
 */
@Slf4j
public class TimeoutWaitWithCondition {
    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();
    private static boolean ready = false;
    protected static final Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread() {
            @Override
            public void run() {
                for (; ; ) {
                    lock.lock();
                    try {
                        ready = random.nextInt(100) < 5 ? true : false;
                        if (ready) {
                            try {
                                condition.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    } finally {
                        lock.unlock();
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.setDaemon(true);
        t.start();
        waiter(1000);
    }

    private static void waiter(final int waitOut) throws InterruptedException {
        if (waitOut < 0) {
            throw new IllegalArgumentException();
        }
        //-----计算等待的最后期限
        final Date deadline = new Date(System.currentTimeMillis() + waitOut);
        //-----是否继续等待
        boolean continueToWait = true;
        lock.lock();
        try {
            while (!ready) {
                log.info("still not ready, continue to wait : {}", continueToWait);
                if(!continueToWait){
                    log.error("wait timed out, unable to execution target action!");
                    return;
                }
                continueToWait = condition.awaitUntil(deadline);
            }
            guarededAction();
        }finally {
            lock.unlock();
        }
    }

    private static void guarededAction() {

    }
}

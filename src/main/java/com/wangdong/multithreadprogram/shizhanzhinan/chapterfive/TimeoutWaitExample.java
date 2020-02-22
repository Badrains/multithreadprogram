package com.wangdong.multithreadprogram.shizhanzhinan.chapterfive;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @author wangdong
 * @description 5-2
 * @since 2020/2/22 17:34
 */
@Slf4j
public class TimeoutWaitExample {
    private static final Object lock = new Object();
    private static boolean ready = false;
    protected static final Random random = new Random();

    public static void main(String[] args) {
        Thread t = new Thread() {
            @Override
            public void run() {
                for (; ; ) {
                    synchronized (this) {
                        ready = random.nextInt(100) < 20 ? true : false;
                        if (ready) {
                            lock.notify();
                        }
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

    private static void waiter(final long timeout) {
        if (timeout < 0) {
            throw new IllegalArgumentException();
        }
        long start = System.currentTimeMillis();
        long waitTime;
        long now;
        synchronized (lock) {
            while (!ready) {
                now = System.currentTimeMillis();
                //-----计算剩余等待时间
                waitTime = timeout - (now - start);
                log.info("remaining time to wait :{}", waitTime);
                if(waitTime <= 0){
                    //-----超时等待退出
                    break;
                }
                try {
                    lock.wait(waitTime);
                } catch (InterruptedException e) {

                }
            }
            if(ready){
                guardedAction();
            }else {
                //-----等待超时退出,保护条件未成立
                log.info("Waiting timed out ,unable to execution target action!");
            }
        }
    }

    private static void guardedAction() {
        log.info("take some action");
    }
}

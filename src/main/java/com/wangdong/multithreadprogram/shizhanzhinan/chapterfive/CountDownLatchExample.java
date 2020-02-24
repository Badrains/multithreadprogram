package com.wangdong.multithreadprogram.shizhanzhinan.chapterfive;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * @description:
 * @author: wangdong
 * @date: 2020/2/24 11:43
 */
@Slf4j
public class CountDownLatchExample {
    private static final CountDownLatch latch = new CountDownLatch(4);
    private static int data;

    public static void main(String[] args) throws InterruptedException {
        Thread workerThread = new Thread() {
            @Override
            public void run() {
                for (int i = 1; i < 10; i++) {
                    data = i;
                    latch.countDown();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        workerThread.start();
        latch.await();
        log.info("It's done. data = {}", data);
    }
}

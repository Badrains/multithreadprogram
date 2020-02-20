package com.wangdong.multithreadprogram.shizhanzhinan.chaptertwo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wangdong
 * @description 2-8
 * @since 2020/2/12 17:31
 */
@Slf4j
public class ThreadStartVisibility {
    static int data = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                log.info("{}：{}", Thread.currentThread().getName(), data);
            }
        };
        data = 1;
        thread.start();
        Thread.sleep(1000);
        data = 2;
    }
}

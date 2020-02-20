package com.wangdong.multithreadprogram.shizhanzhinan.chaptertwo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wangdong
 * @description 2-9
 * @since 2020/2/12 17:45
 */
@Slf4j
public class ThreadJoinVisibility {
    static int data = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run(){
                data = 1;
            }
        };

        thread.start();
        thread.join();
        log.info("data:{}",data);
    }

}

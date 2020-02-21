package com.wangdong.multithreadprogram.shizhanzhinan.chapterthree;

import lombok.extern.slf4j.Slf4j;

/**
 * @description: 3-19
 * @author wangdong
 */
@Slf4j
public class EnumBasedSingletonExample {
    public static void main(String[] args) {
        Thread t = new Thread() {
            @Override
            public void run() {
                Singleton.INSTANCE.someService();
            }
        };
        t.start();
    }

    public static enum Singleton {
        INSTANCE;

        Singleton() {

        }

        public void someService(){
            //-----省略
            log.info("enum instance someService running");
        }
    }
}

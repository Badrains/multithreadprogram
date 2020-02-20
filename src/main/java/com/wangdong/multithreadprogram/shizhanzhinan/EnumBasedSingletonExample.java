package com.wangdong.multithreadprogram.shizhanzhinan;

import lombok.extern.slf4j.Slf4j;

/**
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

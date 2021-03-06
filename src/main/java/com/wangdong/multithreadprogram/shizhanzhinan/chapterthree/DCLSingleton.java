package com.wangdong.multithreadprogram.shizhanzhinan.chapterthree;

/**
 * @description: 3-17
 * @author wangdong
 */
public class DCLSingleton {
    private static volatile DCLSingleton instance = null;

    private DCLSingleton() {

    }

    public static DCLSingleton getInstance() {
        if(null == instance) {
            synchronized (DCLSingleton.class) {
                if (null == instance) {
                    instance = new DCLSingleton();
                }

            }
        }
        return instance;
    }

    public void someService() {

    }
}

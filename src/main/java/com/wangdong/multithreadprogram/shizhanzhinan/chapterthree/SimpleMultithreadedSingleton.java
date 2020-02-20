package com.wangdong.multithreadprogram.shizhanzhinan.chapterthree;

/**
 * @description: 3-15
 * @author wangdong
 */
public class SimpleMultithreadedSingleton {
    private static SimpleMultithreadedSingleton instance = null;

    private SimpleMultithreadedSingleton() {

    }

    public static SimpleMultithreadedSingleton getInstance(){
        synchronized (SimpleMultithreadedSingleton.class){
            if(null == instance){
                instance = new SimpleMultithreadedSingleton();
            }
            return instance;
        }
    }

    public void someService(){

    }
}

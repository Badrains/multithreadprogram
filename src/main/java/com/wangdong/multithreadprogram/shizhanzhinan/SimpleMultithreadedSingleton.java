package com.wangdong.multithreadprogram.shizhanzhinan;

/**
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

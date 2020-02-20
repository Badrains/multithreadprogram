package com.wangdong.multithreadprogram.shizhanzhinan.chapterthree;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author wangdong
 */
public enum AlarmMgr implements Runnable{
    INSTANCE;
    AlarmMgr(){

    }
    private final AtomicBoolean initializating = new AtomicBoolean(false);

    public void init(){
        if(initializating.compareAndSet(false,true)){
            new Thread(this).start();
        }
    }

    public int sendAlarm (String message){
        int result = 0;
        //...
        return result;
    }

    @Override
    public void run() {

    }
}

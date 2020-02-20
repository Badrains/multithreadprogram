package com.wangdong.multithreadprogram.shizhanzhinan;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @description:
 * @author: wangdong
 * @date: 2020/2/20 15:21
 */
@Slf4j
public class SafeObjPublishWhenStartingThread {
    private final Map<String,String> objectState;

    private SafeObjPublishWhenStartingThread(Map<String,String> objectState){
        this.objectState = objectState;
        //-----不在构造器中欧冠启动工作者线程，以免this溢出
    }

    private void init(){
        new Thread(){
          @Override
          public void run(){
              //-----访问外层实例的状态变量
              String value = objectState.get("someKey");
              log.info("value:{}",value);
          }
        }.start();
    }

    /**
     * 工厂方法
     * @param objectState
     * @return
     */
    public static SafeObjPublishWhenStartingThread newInstance(Map<String,String> objectState){
        SafeObjPublishWhenStartingThread instance = new SafeObjPublishWhenStartingThread(objectState);
        instance.init();
        return instance;
    }


}

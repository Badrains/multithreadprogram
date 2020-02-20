package com.wangdong.multithreadprogram.shizhanzhinan.chapterthree;

/**
 * @author wangdong
 * @description: 3-14
 */
public class SingleThreadedSingleton {
    /**
     * 保存该类唯一的实例
     */
    private static SingleThreadedSingleton instance = null;

    /**
     * 私有构造器使其他类无法直接通过 new 创建该类的实例
     */
    private SingleThreadedSingleton() {

    }

    /**
     * 创建并返回唯一实例
     *
     * @return
     */
    public static SingleThreadedSingleton getInstance() {
        if (instance == null) {
            instance = new SingleThreadedSingleton();
        }
        return instance;
    }

    public void someService() {

    }
}

package com.wangdong.multithreadprogram.shizhanzhinan.chapterthree;

/**
 * @description: 3-18
 * @author wangdong
 */
public class StaticHolderSingleton {
    /**
     * 私有构造器
     */
    private StaticHolderSingleton(){

    }

    private static class InstanceHolder {
        //-----保存外部类的唯一实例
        final static StaticHolderSingleton INSTANCE = new StaticHolderSingleton();
    }

    public static StaticHolderSingleton getInstance(){
        return InstanceHolder.INSTANCE;
    }

    /**
     * 其他方法
     */
    public void someService(){

    }

    public static void main(String[] args) {
        InstanceHolder.INSTANCE.someService();
    }
}

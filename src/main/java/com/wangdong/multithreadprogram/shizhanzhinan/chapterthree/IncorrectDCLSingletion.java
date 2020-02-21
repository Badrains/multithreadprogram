package com.wangdong.multithreadprogram.shizhanzhinan.chapterthree;

/**
 * @description: 3-16
 * @author wangdong
 */
public class IncorrectDCLSingletion {

    private static IncorrectDCLSingletion instance = null;

    private IncorrectDCLSingletion() {

    }

    public static IncorrectDCLSingletion getInstance() {
        if(null == instance) {
            synchronized (IncorrectDCLSingletion.class) {
                if (null == instance) {
                    instance = new IncorrectDCLSingletion();
                }

            }
        }
        return instance;
    }

    public void someService() {

    }
}



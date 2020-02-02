package com.wangdong.multithreadprogram.shizhanzhinan;

import java.util.Date;

/**
 * @author wangdong
 */
public class SimpleJavaApp {

    public static void main(String[] args) throws Exception {
        while (true) {
            System.out.println(new Date());
            Thread.sleep(1000);
        }
    }
}

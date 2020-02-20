package com.wangdong.multithreadprogram.shizhanzhinan.chapterone;

import java.util.Date;

/**
 * @author wangdong
 * @description: 1-1 1-2
 */
public class SimpleJavaApp {

    public static void main(String[] args) throws Exception {
        while (true) {
            System.out.println(new Date());
            Thread.sleep(1000);
        }
    }
}

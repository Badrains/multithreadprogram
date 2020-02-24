package com.wangdong.multithreadprogram.shizhanzhinan.service;

/**
 * @description:
 * @author: wangdong
 * @date: 2020/2/24 11:05
 */
public interface Service {
    boolean isStarted();
    void stop();
    void start();
}

package com.wangdong.multithreadprogram.shizhanzhinan.service;

/**
 * @description: 5-10
 * @author: wangdong
 * @date: 2020/2/24 17:45
 */
public interface Channel<P> {
    /**
     * 往传输通道中存入一个产品
     * @param product
     * @throws InterruptedException
     */
    void put(P product) throws InterruptedException;

    /**
     * 冲传输通道中取一个产品
     * @return
     * @throws InterruptedException
     */
    P take() throws InterruptedException;
}

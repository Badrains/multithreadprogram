package com.wangdong.multithreadprogram.shizhanzhinan;

/**
 * @author wangdong
 */
public class Counter {
    private volatile long count;

    public long value() {
        return count;
    }

    public void increment() {
        synchronized (this) {
            count++;
        }
    }
}
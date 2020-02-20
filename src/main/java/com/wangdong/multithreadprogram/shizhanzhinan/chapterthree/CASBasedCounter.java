package com.wangdong.multithreadprogram.shizhanzhinan.chapterthree;

/**
 * @author wangdong
 */
public class CASBasedCounter {
    private volatile long count;

    public long value() {
        return count;
    }

    public void increment() {
        long oldValue;
        long newValue;
        do {
            oldValue = count;
            newValue = oldValue + 1;
        } while (!compareAndSwap(oldValue,newValue));
    }

    private boolean compareAndSwap(long oldValue, long newValue){
        return true;
    }
}

package com.wangdong.multithreadprogram.shizhanzhinan.chapterthree;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author wangdong
 * @description: 3-4
 */
public class ReadWriteLockUsage {
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();
    private final Lock writeLock = rwLock.writeLock();

    public void reader() {
        readLock.lock();
        try {
            //-----在此区域读取共享变量
        } finally {
            readLock.unlock();
        }
    }

    public void writer() {
        writeLock.lock();
        try{
            //-----在此区域进行读写共享变量
        }finally {
            writeLock.unlock();
        }
    }

}

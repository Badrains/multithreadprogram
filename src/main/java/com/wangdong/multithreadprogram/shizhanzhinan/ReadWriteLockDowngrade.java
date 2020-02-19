package com.wangdong.multithreadprogram.shizhanzhinan;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author wangdong
 */
public class ReadWriteLockDowngrade {
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();
    private final Lock writeLock = rwLock.writeLock();

    public void operationWithLockDowngrade() {
        boolean readLockAcquire = false;
        writeLock.lock();
        try {
            //-----对共享数据进行更新

            //-----当前线程持有写锁的情况下申请读锁
            readLock.lock();
            readLockAcquire = true;
        }finally {
            writeLock.unlock();
        }
        if(readLockAcquire){
            try{
                //-----读取共享数据并进行其他操作
            }finally {
                readLock.unlock();
            }


        }
    }
}

package com.wangdong.multithreadprogram.shizhanzhinan;

import com.wangdong.multithreadprogram.shizhanzhinan.service.CircularSeqGenerator;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangdong
 */
public class LockbasedCircularSeqGenerator implements CircularSeqGenerator {

    private short sequence = -1;
    private final Lock lock = new ReentrantLock();

    @Override
    public short nextSequence() {
        lock.lock();
        try {
            if (sequence >= 999) {
                sequence = 0;
            } else {
                sequence++;
            }
            return sequence;
        } finally {
            lock.unlock();
        }
    }
}

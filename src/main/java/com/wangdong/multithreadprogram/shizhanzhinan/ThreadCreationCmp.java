package com.wangdong.multithreadprogram.shizhanzhinan;


/**
 * @author wangdong
 */
public class ThreadCreationCmp {
    public static void main(String[] args) {
        Thread t;
        CountingTask ct = new CountingTask();
        final int numOfProcess = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < 2 * numOfProcess; i++) {
            t = new Thread(ct);
            t.start();
        }

        for (int i = 0; i < 2 * numOfProcess; i++) {
            t = new CountingThread();
            t.start();
        }
    }

    static class Counter {
        private int count = 0;

        public void increment() {
            count++;
        }

        public int value() {
            return count;
        }
    }


    static class CountingTask implements Runnable {
        private Counter counter = new Counter();

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                counter.increment();
            }
            System.out.println("runnable 方式最终结果" + counter.value());
        }
    }

    static class CountingThread extends Thread {
        private Counter counter = new Counter();

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                counter.increment();
            }
            System.out.println("Thread 方式最终结果" + counter.value());
        }
    }

}

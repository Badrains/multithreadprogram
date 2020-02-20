package com.wangdong.multithreadprogram.shizhanzhinan.chaptertwo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wangdong
 * @description 2-2
 * @since 2020/2/9 17:31
 */
@Slf4j
public class RaceConditionDemo {
    public static void main(String[] args) {
        int numbersOfThread = 4;
        Thread[] workerThread = new Thread[numbersOfThread];

        for(int i = 0; i< numbersOfThread; i++){
            workerThread[i] = new WorkerThread(i, 10);
        }
        //-----使线程尽可能的同时启动
        for(Thread tr : workerThread){
            tr.start();
        }
    }

    static class WorkerThread extends Thread {
        private final int requestCount;

        public WorkerThread(int id, int requestCount) {
            super("worker-" + id);
            this.requestCount = requestCount;
        }

        @Override
        public void run() {
            int i = requestCount;
            String requestID;
            RequestIDGenerator requestIDGen = RequestIDGenerator.getInstance();
            while (i-- > 0) {
                //-----生成Request ID
                requestID = requestIDGen.nextID();
                processRequest(requestID);
            }
        }

        /**
         * 模拟处理业务消耗
         *
         * @param requestID
         */
        private void processRequest(String requestID) {
            log.info("{} got requestID: {}", Thread.currentThread().getName(), requestID);
        }
    }
}

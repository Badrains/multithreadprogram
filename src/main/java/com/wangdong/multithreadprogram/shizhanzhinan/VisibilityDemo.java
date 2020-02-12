package com.wangdong.multithreadprogram.shizhanzhinan;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wangdong
 * @description VIsibilityDemo
 * @since 2020/2/12 15:39
 */
@Slf4j
public class VisibilityDemo {

    public static void main(String[] args) throws Exception{
        TimeConsumingTask timeConsumingTask = new TimeConsumingTask();
        Thread thread = new Thread(new TimeConsumingTask());
        thread.start();
        Thread.sleep(1000);
        timeConsumingTask.cancel();
    }

    static class TimeConsumingTask implements Runnable {
        private boolean toCancel = false;

        @Override
        public void run() {
            while (!toCancel) {
                if(doExecute()){
                    break;
                }
            }
            if(toCancel){
                log.info("task was canceled");
            }else {
                log.info("task done");
            }
        }

        private boolean doExecute() {
            boolean isDone = false;
            log.info("executing...");

            return isDone;
        }

        public void cancel() {
            toCancel = true;
            log.info("canceled");
        }
    }
}
package com.wangdong.multithreadprogram.shizhanzhinan.chapterfive;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @description: 5-8
 * @author: wangdong
 * @date: 2020/2/24 13:09
 */
@Slf4j
public class ShootPractice {
    /**
     * 参与打靶训练的全部士兵
     */
    final Soldier[][] rank;

    /**
     * 靶的个数，即每排中士兵的个数
     */
    final int N;

    /**
     * 打靶的持续时间
     */
    final int lasting;

    /**
     * 标识是否继续打靶
     */
    volatile boolean done = false;

    /**
     * 用来指示进行下一轮打靶的是那一排士兵
     */
    volatile int nextLine = 0;
    final CyclicBarrier shiftBarrier;
    final CyclicBarrier startBarrier;

    public ShootPractice(int N, final int lineCount, int lasting) {
        this.N = N;
        this.lasting = lasting;
        this.rank = new Soldier[lineCount][N];
        for (int i = 0; i < lineCount; i++) {
            for (int j = 0; j < N; j++) {
                rank[i][j] = new Soldier(i * N + j);
            }
        }
        shiftBarrier = new CyclicBarrier(N, new Runnable() {
            @Override
            public void run() {
                //-----更新下一轮打靶的排
                nextLine = (nextLine + 1) % lineCount;
                log.info("Next turn is : {}", nextLine);
            }
        });
        startBarrier = new CyclicBarrier(N);
    }

    public static void main(String[] args) throws InterruptedException {
        ShootPractice sp = new ShootPractice(4, 5, 24);
        sp.start();
    }

    private void start() throws InterruptedException {
        //-----创建并启动工作者线程
        Thread[] threads = new Thread[N];
        for (int i = 0; i < N; ++i) {
            threads[i] = new Shooting(i);
            threads[i].start();
        }
        //-----指定时间后停止打靶
        Thread.sleep(lasting * 1000);
        stop();
        for (Thread t : threads) {
            t.join();
        }
        log.info("Practice finished.");
    }

    public void stop() {
        done = true;
    }

    class Shooting extends Thread {
        final int index;

        public Shooting(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            Soldier soldier;
            try {
                while (!done) {
                    soldier = rank[nextLine][index];
                    //-----一排的士兵必须同时开始射击
                    startBarrier.await();
                    soldier.fire();
                    //-----一排士兵必须等该排中所有的士兵都已经射击完毕才能够离开射击点
                    shiftBarrier.await();
                }
            }catch (InterruptedException e){

            }catch (BrokenBarrierException e){
                e.printStackTrace();
            }
        }

    }

    static class Soldier {
        private final int seqNo;

        public Soldier(int seqNo) {
            this.seqNo = seqNo;
        }

        public void fire() {

        }
    }
}



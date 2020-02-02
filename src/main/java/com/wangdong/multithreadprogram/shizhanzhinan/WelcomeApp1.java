package com.wangdong.multithreadprogram.shizhanzhinan;

/**
 * @author wangdong
 */
public class WelcomeApp1 {

    public static void main(String[] args) {
        Thread thread = new Thread(new WelcomeTask());
        thread.start();
        System.out.println("1.welcome! I'm " + Thread.currentThread().getName());
    }

    static class WelcomeTask implements Runnable {
        @Override
        public void run() {
            System.out.println("2.welcome! I'm " + Thread.currentThread().getName());
        }
    }
}

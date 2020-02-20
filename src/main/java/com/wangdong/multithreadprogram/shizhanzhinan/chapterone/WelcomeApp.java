package com.wangdong.multithreadprogram.shizhanzhinan.chapterone;

/**
 * @description: 1-2
 * @author wangdong
 */
public class WelcomeApp {

    public static void main(String[] args) {
        WelcomeThread welcomeThread = new WelcomeThread();
        welcomeThread.start();
        System.out.println("1.welcome! I'm " + Thread.currentThread().getName());
    }

    static class WelcomeThread extends Thread {
        @Override
        public void run() {
            System.out.println("2.welcome! I'm " + Thread.currentThread().getName());
        }
    }
}

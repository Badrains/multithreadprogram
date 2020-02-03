package com.wangdong.multithreadprogram.shizhanzhinan;

/**
 * @author wangdong
 */
public class WelcomeApp2 {
    public static void main(String[] args) {
        Thread welcomeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("2.welcome! I'm " + Thread.currentThread().getName());
            }
        });
        welcomeThread.start();
        welcomeThread.run();
        System.out.println("1.welcome! I'm " + Thread.currentThread().getName());
    }
}

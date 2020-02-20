package com.wangdong.multithreadprogram.shizhanzhinan.chapterone;

/**
 * @author wangdong
 * @description: 1-7
 */
public class JavaThreadAnywhere {

    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        System.out.println("The main thread was executed by " + thread.getName());
        Thread helperThread = new Thread(new Helper("Java thread anywhere"));
        helperThread.run();
    }

    static class Helper implements Runnable {
        private final String message;

        public Helper(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            doSomething(message);
        }

        private void doSomething(String message) {
            Thread thread = Thread.currentThread();
            System.out.println("The something thread was executed by " + thread.getName());
            System.out.println("Do something with " + message);
        }
    }
}

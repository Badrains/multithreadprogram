package com.wangdong.multithreadprogram.shizhanzhinan.chapterfour;

/**
 * @description:
 * @author: wangdong
 * @date: 2020/2/21 14:29
 */
public class WTSTMeasureDemo implements Runnable {
    final long waitTime;

    public WTSTMeasureDemo(long waitTime) {
        this.waitTime = waitTime;
    }

    public static void main(String[] args) throws Exception {
        main0(args);
    }

    private static void main0(String[] args) throws Exception {
        final int argc = args.length;
        int nThreads = argc > 0 ? Integer.valueOf(args[0]) : 1;
        long waitTime = argc >= 1 ? Long.valueOf(args[1]) : 4000L;
        WTSTMeasureDemo demo = new WTSTMeasureDemo(waitTime);
        Thread[] threads = new Thread[nThreads];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(demo);
        }
        long s = System.currentTimeMillis();
        //Tools.startAndWaitTerminated(threads);
        long duration = System.currentTimeMillis() - s;
        long serviceTIme = duration - waitTime;


    }


    @Override
    public void run() {
        try {
            Thread.sleep(waitTime);
            String result = null;
            for (int i = 0; i < 400000; i++) {
                //result = DESEncryption.encryptAsString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

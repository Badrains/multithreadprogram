package com.wangdong.multithreadprogram.shizhanzhinan.chapterfive;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @description: 5-1
 * @author: wangdong
 * @date: 2020/2/21 17:34
 */
@Slf4j
public class AlarmAgent {
    /**
     * 保存该类的唯一实例
     */
    private final static AlarmAgent INSTANCE = new AlarmAgent();
    /**
     * 是否连接上告警服务器
     */
    private boolean connectedToServer = false;
    /**
     * 心跳线程，用于检测告警代理与告警服务器的网络连接是否正常
     */
    private final HeartbeartThread heartbeartThread = new HeartbeartThread();

    private AlarmAgent() {

    }

    public static AlarmAgent getInstance() {
        return INSTANCE;
    }

    public void init() {
        connectToServer();
        heartbeartThread.setDaemon(true);
        heartbeartThread.start();
    }

    private void connectToServer() {
        //-----创建并启动网络连接线程，在该线程中与告警服务器建立连接
        new Thread() {
            @Override
            public void run() {
                doConnect();
            }
        }.start();
    }

    private void doConnect() {
        //-----模拟连接耗时
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this) {
            connectedToServer = true;
            //-----连接已经建立完毕，通知以唤醒告警发送线程
            notify();
        }
    }

    public void sendAlarm(String message) throws InterruptedException {
        synchronized (this) {
            //-----使当前线程等待，直到告警代理与告警服务器的连接建立完毕或者恢复
            while (!connectedToServer) {
                log.info("Alarm agent was not connected to server");
                wait();
            }
            doSendAlarm(message);
        }
    }

    private void doSendAlarm(String message) {
        //-----发送消息
        log.info("Alarm send : {}", message);
    }

    class HeartbeartThread extends Thread {
        @Override
        public void run() {
            try {
                //-----留一段时间给网络连接线程与告警服务器建立连接
                Thread.sleep(1000);
                while (true) {
                    if (checkConnect()) {
                        connectedToServer = true;
                    } else {
                        connectedToServer = false;
                        log.info("Alarm agent was disconnected from server");
                        //-----监测到中断，重新连接
                        connectToServer();
                    }
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {

            }
        }
    }

    private boolean checkConnect() {
        boolean isConnected = true;
        final Random random = new Random();
        int rand = random.nextInt(1000);

        if(rand <= 500){
            isConnected = false;
        }
        return isConnected;

    }
}

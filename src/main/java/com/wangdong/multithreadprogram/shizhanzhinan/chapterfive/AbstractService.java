package com.wangdong.multithreadprogram.shizhanzhinan.chapterfive;

import com.wangdong.multithreadprogram.shizhanzhinan.service.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * @description: 5-6
 * @author: wangdong
 * @date: 2020/2/24 10:56
 */
@Slf4j
public abstract class AbstractService implements Service {
    protected boolean started = false;
    protected final CountDownLatch latch;

    public AbstractService(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public boolean isStarted() {
        return started;
    }

    @Override
    public void stop() {

    }

    @Override
    public void start() {
        ServiceStarter serviceStarter = new ServiceStarter();
        serviceStarter.start();
    }

    class ServiceStarter extends Thread {
        @Override
        public void run() {
            final String serviceName = AbstractService.this.getClass().getSimpleName();
            log.info("Starting {}", serviceName);
            try {
                doStart();
                started = true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
                log.info("Done Starting {}", serviceName);
            }
        }
    }

    protected  void doStart(){

    }

}

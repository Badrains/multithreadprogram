package com.wangdong.multithreadprogram.shizhanzhinan.chapterfive;

import com.wangdong.multithreadprogram.shizhanzhinan.service.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * @description: 5-5
 * @author: wangdong
 * @date: 2020/2/24 10:48
 */
public class ServiceManager {
    static volatile CountDownLatch latch;
    static Set<Service> services;

    public static void startServices() {
        services = getServices();
        for (Service service : services) {
            service.start();
        }
    }

    public static boolean checkServiceStatus() {
        boolean allIsOk = true;
        //-----等待服务启动结束
        try {
            latch.await();
        } catch (InterruptedException e) {
            return false;
        }

        for (Service service : services) {
            if (!service.isStarted()) {
                allIsOk = false;
                break;
            }
        }
        return allIsOk;
    }

    static Set<Service> getServices() {
        latch = new CountDownLatch(3);
        HashSet<Service> services = new HashSet();
        services.add(new SampleSerivceC(latch));
        services.add(new SampleSerivceB(latch));
        services.add(new SampleSerivceA(latch));
        return services;
    }

    private static class SampleSerivceC extends AbstractService {

        public SampleSerivceC(CountDownLatch latch) {
            super(latch);
        }
    }

    private static class SampleSerivceB extends AbstractService {

        public SampleSerivceB(CountDownLatch latch) {
            super(latch);
        }
    }

    private static class SampleSerivceA extends AbstractService {

        public SampleSerivceA(CountDownLatch latch) {
            super(latch);
        }
    }
}

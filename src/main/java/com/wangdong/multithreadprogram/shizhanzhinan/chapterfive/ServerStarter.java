package com.wangdong.multithreadprogram.shizhanzhinan.chapterfive;

import lombok.extern.slf4j.Slf4j;

/**
 * @description: 5-4
 * @author: wangdong
 * @date: 2020/2/24 10:44
 */
@Slf4j
public class ServerStarter {
    public static void main(String[] args) {
        //-----启动所有的服务
        ServiceManager.startServices();
        //-----在所有其他操作执行后，监测服务启动状态
        Boolean allIsOk;
        //-----监测所有服务的启动状态
        allIsOk = ServiceManager.checkServiceStatus();
        if(allIsOk){
            log.info("All services were successfully started");
        }else {
            log.error("Some service(s) failed to start,exiting JVM");
            System.exit(1);
        }
    }
}

package com.wangdong.multithreadprogram.shizhanzhinan;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangdong
 */
@Slf4j
public class StaticVisibilityExample {
    private static Map<String, String> taskConfig;

    static {
        log.info("The class being initialized...");
        taskConfig = new HashMap<>();
        taskConfig.put("url", "www.wangdong.com");
        taskConfig.put("timeout", "1000");
    }

    public static void changeConfig(String url, int timeout) {
        taskConfig = new HashMap<>();
        taskConfig.put("url", url);
        taskConfig.put("timeout", String.valueOf(timeout));
    }

    public static void init(){
        Thread t = new Thread(){
            @Override
            public void run(){
                String url = taskConfig.get("url");
                String timeout = taskConfig.get("timeout");
                doTask(url,Integer.valueOf(timeout));
            }
        };
        t.start();
    }

    private static void doTask(String url, Integer timeout) {
        log.info("url:{}",url);
        log.info("timeout:{}",timeout);
    }

    public static void main(String[] args) {
        StaticVisibilityExample.init();
    }
}


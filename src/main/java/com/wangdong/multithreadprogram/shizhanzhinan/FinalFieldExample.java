package com.wangdong.multithreadprogram.shizhanzhinan;

import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: wangdong
 * @date: 2020/2/20 14:20
 */
@Slf4j
public class FinalFieldExample {
    final int x;
    int y;
    static FinalFieldExample instance;

    public FinalFieldExample() {
        x = 1;
        y = 2;
    }

    public static void writer() {
        instance = new FinalFieldExample();
    }

    public static void reader() {
        final FinalFieldExample theInstance = instance;
        if (null != theInstance) {
            int diff = instance.y - instance.x;
            log.info("diff:{}", diff);
        }
    }
}

package com.wangdong.multithreadprogram.shizhanzhinan;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wangdong
 */
@Slf4j
public class ClassLazyInitDemo {
    public static void main(String[] args) {
        log.info("hashCode:{}", Collaborator.class.hashCode());
        log.info("number:{}", Collaborator.number);
        log.info("flag:{}", Collaborator.flag);
    }

    static class Collaborator {
        static int number = 1;
        static boolean flag = true;

        static {
            log.info("Collaborator initializing...");
        }
    }
}

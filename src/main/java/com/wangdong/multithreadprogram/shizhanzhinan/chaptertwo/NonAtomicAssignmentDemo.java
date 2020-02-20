package com.wangdong.multithreadprogram.shizhanzhinan.chaptertwo;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * @author wangdong
 * @description 2-6
 * @since 2020/2/11 19:13
 */
@Slf4j
public class NonAtomicAssignmentDemo implements Runnable {
    static long value = 0;
    private final long valueToSet;

    public NonAtomicAssignmentDemo(long valueToSet) {
        this.valueToSet = valueToSet;
    }

    public static void main(String[] args) {
        Thread updateThread1 = new Thread(new NonAtomicAssignmentDemo(0L));
        Thread updateThread2 = new Thread(new NonAtomicAssignmentDemo(-1L));

        PrintStream ps = new PrintStream(new OutputStream() {
            @Override
            public void write(int i) throws IOException {

            }
        });
        updateThread1.start();
        updateThread2.start();

        long snapshot;
        while (0 == (snapshot = value) || -1 == snapshot) {
            log.info("正常输出：{}", snapshot);
        }
        log.error("发生竞态：{}",snapshot);
    }

    @Override
    public void run() {
        for (; ; ) {
            value = valueToSet;
        }
    }
}

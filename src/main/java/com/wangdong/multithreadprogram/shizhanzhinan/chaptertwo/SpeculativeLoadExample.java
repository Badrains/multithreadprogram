package com.wangdong.multithreadprogram.shizhanzhinan.chaptertwo;

/**
 * @author wangdong
 * @description 2-11
 * @since 2020/2/13 14:31
 */
public class SpeculativeLoadExample {
    private boolean ready = false;
    private int[] data = new int[]{1, 2, 3, 4, 5, 6, 7, 8};

    public void write() {
        int[] newData = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        for (int i = 0; i < newData.length; i++) {
            newData[i] = newData[i] - i;
        }
        ready = true;
        data = newData;
    }

    public int reader() {
        int sum = 0;
        int[] snapshot;
        if (ready) {
            snapshot = data;
            for (int i = 0; i < snapshot.length; i++) {
                sum = sum + snapshot[i];
            }
        }
        return sum;
    }
}

package com.wangdong.multithreadprogram.shizhanzhinan;

import com.wangdong.multithreadprogram.shizhanzhinan.service.CircularSeqGenerator;
import jdk.nashorn.internal.ir.RuntimeNode;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RequestIDGenerator implements CircularSeqGenerator {
    /**
     * 保存该类的唯一实例
     */
    private final static RequestIDGenerator INSTANCE = new RequestIDGenerator();
    private final static short SEQ_UPPER_LIMIT = 999;
    private short sequence = -1;

    private RequestIDGenerator() {
    }

    @Override
    public short nextSequence() {
        if (sequence >= SEQ_UPPER_LIMIT) {
            sequence = 0;
        } else {
            sequence++;
        }
        return sequence;
    }

    /**
     * 生成一个新的RequestID
     * @return
     */
    public String nextID(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        String timestamp = sdf.format(new Date());
        short sequenceNo = nextSequence();
        return "0049" + timestamp + sequenceNo;
    }

    public static RequestIDGenerator getInstance(){
        return INSTANCE;
    }
}

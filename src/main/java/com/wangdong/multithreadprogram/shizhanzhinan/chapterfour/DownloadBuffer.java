package com.wangdong.multithreadprogram.shizhanzhinan.chapterfour;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @description: 4-3
 * @author: wangdong
 * @date: 2020/2/21 10:08
 */
public class DownloadBuffer implements Closeable {
    /**
     * 当前 Buffer 中缓冲的数据相对于整个存储文件的位置偏移
     */
    private long globalOffset;
    private long upperBound;
    private int offset = 0;
    public final ByteBuffer byteBuf;
    private final Storage storage;

    public DownloadBuffer(long globalOffset, long upperBound, final Storage storage) {
        this.globalOffset = globalOffset;
        this.upperBound = upperBound;
        this.byteBuf = ByteBuffer.allocate(1024 * 1024);
        this.storage = storage;
    }

    public void write(ByteBuffer buf) throws Exception {
        int length = buf.position();
        final int capacity = byteBuf.capacity();
        //-----当缓冲区已满，或者剩余容量不足的时候
        if ((offset + length) > capacity || length == capacity) {
            flush();
        }
        byteBuf.position(offset);
        buf.flip();
        byteBuf.put(buf);
        offset += length;
    }

    private void flush() throws IOException{
        int length;
        byteBuf.flip();
        length = storage.store(globalOffset,byteBuf);
        byteBuf.clear();
        globalOffset += length;
        offset = 0;
    }


    @Override
    public void close() throws IOException {
        if(globalOffset < upperBound){
            flush();
        }
    }
}

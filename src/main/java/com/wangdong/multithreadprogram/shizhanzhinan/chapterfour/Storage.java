package com.wangdong.multithreadprogram.shizhanzhinan.chapterfour;

import lombok.extern.slf4j.Slf4j;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @description: 4-4
 * @author: wangdong
 * @date: 2020/2/21 9:43
 */
@Slf4j
public class Storage implements Closeable, AutoCloseable {
    private final RandomAccessFile storeFile;
    private final FileChannel storeChannel;
    protected final AtomicLong totalWrites = new AtomicLong(0);

    public Storage(long fileSize, String fileShortName) throws Exception {
        String fullFileName = "D:\\Demo\\" + fileShortName;
        String localFileName;
        localFileName = createStoreFile(fileSize, fullFileName);
        storeFile = new RandomAccessFile(localFileName, "rw");
        storeChannel = storeFile.getChannel();
    }

    public int store(long offset, ByteBuffer byteBuffer) throws IOException {
        int length;
        storeChannel.write(byteBuffer, offset);
        length = byteBuffer.limit();
        totalWrites.addAndGet(length);
        return length;
    }

    public long getTotalWrites() {
        return totalWrites.get();
    }

    private String createStoreFile(final long fileSize, String fullFileName) throws Exception {
        File file = new File(fullFileName);
        log.info("create local file : {}", fullFileName);
        RandomAccessFile raf;
        raf = new RandomAccessFile(file, "rw");
        try {
            raf.setLength(fileSize);
        }finally {
            raf.close();
        }
        return fullFileName;
    }


    @Override
    public synchronized void close() throws IOException {
        if(storeChannel.isOpen()){
            storeChannel.close();
        }
    }
}

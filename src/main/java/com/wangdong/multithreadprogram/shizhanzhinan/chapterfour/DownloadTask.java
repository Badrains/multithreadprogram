package com.wangdong.multithreadprogram.shizhanzhinan.chapterfour;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @description: 4-2
 * @author: wangdong
 * @date: 2020/2/21 9:30
 */
public class DownloadTask implements Runnable {
    private final long lowerBound;
    private final long upperBound;
    private final DownloadBuffer xbuf;
    private final URL requestURL;
    private final AtomicBoolean cancelFlag;

    public DownloadTask(long lowerBound, long upperBound, URL requestURL, Storage storage, AtomicBoolean cancelFlag) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.requestURL = requestURL;
        this.xbuf = new DownloadBuffer(lowerBound, upperBound, storage);
        this.cancelFlag = cancelFlag;
    }

    private static InputStream issueRequest(URL requestURL, long lowerBound, long upperBound) throws IOException {
        //-----略
        return null;
    }

    @Override
    public void run() {
        if (cancelFlag.get()) {
            return;
        }
        ReadableByteChannel channel = null;
        try {
            channel = Channels.newChannel(issueRequest(requestURL, lowerBound, upperBound));
            ByteBuffer buf = ByteBuffer.allocate(1024);
            while (!cancelFlag.get() && channel.read(buf) >0){
                //-----将网络读取到的数据写到缓冲区
                xbuf.write(buf);
                buf.clear();
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

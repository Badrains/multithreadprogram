package com.wangdong.multithreadprogram.shizhanzhinan.chapterfive;


import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @author wangdong
 * @description 5-12
 * @since 2020/3/2 20:40
 */
public class ConcurrentRSSReader {

    public static void main(String[] args) throws IOException {
        final int argc = args.length;
        String url = argc > 0 ? args[0] : "www.wangdong.com";
        //-----从网络加载RSS数据
        InputStream in = loadRSS(url);
        Document document = parseXML(in);
        //-----从XML里面读取数据
        Element eleRss = (Element) document.getFirstChild();
        Element eleChannel = (Element) eleRss.getElementsByTagName("channel").item(0);
        //-----略
    }

    private static Document parseXML(InputStream in) {
        //-----略
        return null;
    }

    private static InputStream loadRSS(String url) throws IOException {
        final PipedInputStream in = new PipedInputStream();
        final PipedOutputStream pos = new PipedOutputStream(in);

        Thread workerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    download(url, pos);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        workerThread.start();
        return in;
    }

    private static void download(String url, PipedOutputStream pos) throws IOException {
        ReadableByteChannel readChannel = null;
        WritableByteChannel writeChannel = null;

        try {
            //-----从URL发起HTTP请求
            BufferedInputStream in = issueRequest(url);
            readChannel = Channels.newChannel(in);
            ByteBuffer buf = ByteBuffer.allocate(1024);
            writeChannel = Channels.newChannel(pos);
            while (readChannel.read(buf) > 0) {
                buf.flip();
                writeChannel.write(buf);
                buf.clear();
            }
        } finally {

        }
    }

    private static BufferedInputStream issueRequest(String url) {
        //-----略
        return null;
    }


}

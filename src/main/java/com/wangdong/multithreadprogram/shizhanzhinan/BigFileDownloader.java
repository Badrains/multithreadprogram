package com.wangdong.multithreadprogram.shizhanzhinan;

import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @description:
 * @author: wangdong
 * @date: 2020/2/20 16:58
 */
@Slf4j
public class BigFileDownloader {
    protected final URL requestURL;
    protected final long fileSize;

    /**
     * 负责已下载数据的存储
     */
    protected final Storage storage;
    protected final AtomicBoolean taskCanceled = new AtomicBoolean(false);

    public BigFileDownloader(String strURL) throws Exception {
        requestURL = new URL(strURL);
        //-----获取待下载资源的大小
        fileSize = retieveFileSize(requestURL);
        log.info("file total size :{}", fileSize);
        String fileName = strURL.substring(strURL.lastIndexOf("/") + 1);
        storage = new Storage(fileSize, fileName);
    }

    /**
     * 下载指定的文件
     *
     * @param taskCount
     * @param reportInterval
     */
    public void download(int taskCount, long reportInterval) throws Exception {
        long chunkSizePerThread = fileSize / taskCount;
        //-----下载数据段的起始字节
        long lowerBound = 0;
        long upperBound = 0;
        DownloadTask dt;
        for (int i = taskCount - 1; i >= 0; i--) {
            lowerBound = i * chunkSizePerThread;
            if (i == taskCount - 1) {
                upperBound = fileSize;
            } else {
                upperBound = lowerBound + chunkSizePerThread - 1;
            }
            //-----创建下载任务
            dt = new DownloadTask(lowerBound, upperBound, requestURL, storage, taskCanceled);
            dispatchWork(dt, i);
        }
        reportProgress(reportInterval);
        doCleanup();
    }

    protected void dispatchWork(final DownloadTask dt, int workerIndex) {
        //-----创建下载线程
        Thread workerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    dt.run();
                }catch (Exception e){
                    e.printStackTrace();
                    //-----取消文件下载
                    cancelDownload();
                }
            }
        });
        workerThread.setName("downloader-" + workerIndex);
        workerThread.start();
    }




    protected void cancelDownload() {
        if (taskCanceled.compareAndSet(false, true)) {
            doCleanup();
        }
    }

    protected void doCleanup() {

    }

    private void reportProgress(long reportInterval) {

    }

    private long retieveFileSize(URL requestURL) {
        //-----略
        return 0L;
    }
}

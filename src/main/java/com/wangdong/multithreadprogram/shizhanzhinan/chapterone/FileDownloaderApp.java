package com.wangdong.multithreadprogram.shizhanzhinan.chapterone;

import lombok.extern.slf4j.Slf4j;

import java.io.FileOutputStream;
import java.net.URL;

/**
 * @description: 1-9
 * @author wangdong
 */
@Slf4j
public class FileDownloaderApp {

    public static void main(String[] args) {
        Thread downLoaderThread;
        for (String url : args) {
            downLoaderThread = new Thread(new FileDownloader(url));
            downLoaderThread.start();
        }
    }

    static class FileDownloader implements Runnable {
        private final String fileUrl;

        public FileDownloader(String fileUrl) {
            this.fileUrl = fileUrl;
        }

        @Override
        public void run() {
            log.info("Downloading from {}", fileUrl);
            String fileBaseName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
            try {
                URL url = new URL(fileUrl);
                String localFileName = "C:\\新建文件夹" + fileBaseName;
                log.info("save to {}", localFileName);
                downloadFile(url, new FileOutputStream(localFileName), 1024);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void downloadFile(URL url, FileOutputStream fileOutputStream, int bufSize) throws Exception {
            //-----略
        }
    }
}

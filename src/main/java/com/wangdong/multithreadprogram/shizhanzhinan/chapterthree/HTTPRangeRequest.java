package com.wangdong.multithreadprogram.shizhanzhinan.chapterthree;

/**
 * @description:
 * @author: wangdong
 * @date: 2020/2/20 14:47
 */
public class HTTPRangeRequest {
    private final Range range;
    private String url;

    public HTTPRangeRequest(String url, int lowerBound, int upperBound) {
        this.url = url;
        this.range = new Range(lowerBound, upperBound);
    }

    public Range getRange() {
        return range;
    }

    public static class Range {
        private long lowerBound;
        private long upperBound;

        public Range(long lowerBound, long upperBound) {
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }
    }
}

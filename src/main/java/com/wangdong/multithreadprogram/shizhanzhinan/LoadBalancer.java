package com.wangdong.multithreadprogram.shizhanzhinan;

import javax.xml.ws.Endpoint;

/**
 * @author wangdong
 */
public interface LoadBalancer {
    void updateCandidate(final Candidate candidate);
    Endpoint nextEndpoint();
}

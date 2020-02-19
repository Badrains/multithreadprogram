package com.wangdong.multithreadprogram.shizhanzhinan;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Request;

import javax.xml.ws.Endpoint;


/**
 * @author wangdong
 */
@Slf4j
public class ServiceInvoker {
    /**
     * 保存当前类的唯一实例
     */
    private static final ServiceInvoker INSTANCE = new ServiceInvoker();
    /**
     * 负载均衡类实例。使用volatile变量保障可见性
     */
    private volatile LoadBalancer loadBalancer;

    private ServiceInvoker() {

    }

    /**
     * 获取当前类的唯一实例
     *
     * @return
     */
    public static ServiceInvoker getInstance() {
        return INSTANCE;
    }

    /**
     * 根据指定的负载均衡器派发请求到指定的下游部件
     *
     * @param request
     */
    public void dispatchRequest(Request request) {
        Endpoint endpoint = getLoadBalancer().nextEndpoint();

        if (null == endpoint) {
            //-----省略
            return;
        }
        dispatchToDownstream(request, endpoint);
    }

    /**
     * 真正将请求指派给下油部件
     *
     * @param request
     * @param endpoint
     */
    private void dispatchToDownstream(Request request, Endpoint endpoint) {
        log.info("Dispatch request to {} : {}", endpoint, request);
        //-----省略
    }

    /**
     * 读取负载均衡器实例
     *
     * @return
     */
    public LoadBalancer getLoadBalancer() {
        return loadBalancer;
    }

    /**
     * 设置或者更新负载均衡器实例
     * @param loadBalancer
     */
    public void setLoadBalancer(LoadBalancer loadBalancer){
        this.loadBalancer = loadBalancer;
    }
}

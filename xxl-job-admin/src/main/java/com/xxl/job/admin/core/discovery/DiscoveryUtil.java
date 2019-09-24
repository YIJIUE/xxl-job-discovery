package com.xxl.job.admin.core.discovery;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.LookupService;
import com.xxl.job.core.biz.model.ReturnT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;


/**
 * @author YIJIUE
 */
@Component
public class DiscoveryUtil implements ApplicationContextAware {

    private static Logger logger = LoggerFactory.getLogger(DiscoveryUtil.class);

    private static LookupService<InstanceInfo> lookupService;
    private static LoadBalancerClient loadBalanced;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.loadBalanced = applicationContext.getBean(LoadBalancerClient.class);
        this.lookupService = applicationContext.getBean(LookupService.class);
    }

    public ServiceInstance getClientExecutor(String serviceName){
        return loadBalanced.choose(serviceName);
    }

    public ReturnT remoteProcess(String serviceName, String param){
        ServiceInstance serviceInstance = getClientExecutor(serviceName);
        String uri = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort();
        ResponseEntity<ReturnT> returnTResponseEntity = restTemplate.postForEntity(uri, param, ReturnT.class);
        return returnTResponseEntity.getBody();
    }

    /**
     * 获取注册中心中指定注册名称的所有服务实例
     * 支持eureka nacos
     * @param appName 应用实例名
     * @return List<InstanceInfo>
     */
    public List<InstanceInfo> getServicesByDiscovery(String appName){
        Application application = lookupService.getApplication(appName);
        List<InstanceInfo> instances = null;
        if (application != null) {
            instances = application.getInstances();
        } else {
            logger.error("service does not exist -> {}", appName);
        }
        return instances;
    }

}

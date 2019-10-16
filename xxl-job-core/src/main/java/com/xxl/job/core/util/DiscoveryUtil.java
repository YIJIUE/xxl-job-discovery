package com.xxl.job.core.util;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import com.netflix.discovery.shared.LookupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * JOB ADMIN CONSOLE SERVICE LIST
 *
 * @author YIJIUE
 */
public class DiscoveryUtil {

    private static Logger logger = LoggerFactory.getLogger(DiscoveryUtil.class);

    public static Map<String, List<String>> adminServicesList = new HashMap<>();

    private static LookupService<InstanceInfo> lookupService;

    public DiscoveryUtil(LookupService<InstanceInfo> lookupService) {
        this.lookupService = lookupService;
    }

    /**
     * add JOB ADMIN service list
     * @param name 名称
     */
    public static void addList(String name) {
        List<InstanceInfo> servicesByDiscovery = getServicesByDiscovery(name);
        if (!CollectionUtils.isEmpty(servicesByDiscovery)) {
            List<String> serviceList = new ArrayList<>();
            servicesByDiscovery.forEach(instanceInfo -> {
                String service = instanceInfo.getIPAddr() + ":" + instanceInfo.getPort();
                serviceList.add(service);
            });
            adminServicesList.put(name, serviceList);
            logger.info("registry scheduled success : {}", name);
        }
    }

    /**
     * 获取注册中心中指定注册名称的所有服务实例
     * 支持eureka nacos
     *
     * @param appName 应用实例名
     * @return List<InstanceInfo>
     */
    public static List<InstanceInfo> getServicesByDiscovery(String appName) {
        Application application = null;
        try {
            application = lookupService.getApplication(appName);
        } catch (NullPointerException e){
            logger.error("service first discovery fail : {}", e.toString());
        }

        List<InstanceInfo> instances = null;
        if (application != null) {
            instances = application.getInstances();
        } else {
            logger.error("service does not exist -> {}", appName);
        }
        return instances;
    }

    /**
     * Determines if the remote host already exists
     * @param host admin console address
     * @return true | false
     */
    public static boolean hostExist(String host){
        for (String admin : adminServicesList.keySet()) {
            List<String> urls = adminServicesList.get(admin);

            List<String> ipList = new ArrayList<>(3);
            for (String url : urls) {
                String[] split = url.split(":");
                ipList.add(split[0]);
            }

            if (ipList.contains(host)) {
                return true;
            }
        }
        return false;
    }

}

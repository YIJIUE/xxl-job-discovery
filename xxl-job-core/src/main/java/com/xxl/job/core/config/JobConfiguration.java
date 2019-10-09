package com.xxl.job.core.config;

import com.netflix.discovery.shared.LookupService;
import com.xxl.job.core.util.DiscoveryUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author YIJIUE
 */
@ComponentScan("com.xxl.job.core.endpoint")
public class JobConfiguration implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        new DiscoveryUtil(applicationContext.getBean(LookupService.class));
    }
}

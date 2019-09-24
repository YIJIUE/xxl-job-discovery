package com.xxl.job.admin.controller;

import com.xxl.job.admin.controller.annotation.PermissionLimit;
import com.xxl.job.admin.core.conf.XxlJobScheduler;
import com.xxl.job.core.biz.AdminBiz;
import com.xxl.job.core.biz.model.ReturnT;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xuxueli on 17/5/10.
 */
@Controller
public class JobApiController implements InitializingBean {

    @Autowired
    private AdminBiz adminBiz;

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @RequestMapping(AdminBiz.MAPPING)
    @PermissionLimit(limit=false)
    public void api(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        XxlJobScheduler.invokeAdminService(request, response);
    }

    /*@RequestMapping("/test")
    public ReturnT<String> api(@RequestParam String appName, @RequestParam String group){
        ReturnT<String> result = adminBiz.registryByDiscovery(appName, group);
        return result;
    }*/


}

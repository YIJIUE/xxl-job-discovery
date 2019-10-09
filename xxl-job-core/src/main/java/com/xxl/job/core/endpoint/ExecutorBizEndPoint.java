package com.xxl.job.core.endpoint;

import com.xxl.job.core.biz.ExecutorBiz;
import com.xxl.job.core.biz.impl.ExecutorBizImpl;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.biz.model.TriggerParam;
import com.xxl.job.core.util.DiscoveryUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * executor EndPoint
 * @author YIJIUE
 */
@RestController
public class ExecutorBizEndPoint {

    private static ExecutorBiz executorBiz = new ExecutorBizImpl();

    /**
     * Task execution
     * @param triggerParam execution param
     * @return success | fail
     */
    @PostMapping("/v1/executor/api")
    public ReturnT<String> run(@RequestBody TriggerParam triggerParam, HttpServletRequest httpServletRequest) {
        String remoteHost = httpServletRequest.getRemoteHost();

        if (!DiscoveryUtil.hostExist(remoteHost)) {
            return ReturnT.FAIL;
        }
        return executorBiz.run(triggerParam);
    }

    /**
     * ADD JOB ADMIN CONSOLE SERVICE LIST
     * @param adminName console name
     */
    @PostMapping("/v1/executor/list")
    public void addServices(@RequestParam String adminName){
        DiscoveryUtil.addList(adminName);
    }

}

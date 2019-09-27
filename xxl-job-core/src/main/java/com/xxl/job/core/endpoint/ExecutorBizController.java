package com.xxl.job.core.endpoint;

import com.xxl.job.core.biz.ExecutorBiz;
import com.xxl.job.core.biz.impl.ExecutorBizImpl;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.biz.model.TriggerParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ExecutorBizController {

    private static ExecutorBiz executorBiz = new ExecutorBizImpl();

    @PostMapping("/v1/executor/api")
    public ReturnT<String> run(@RequestBody TriggerParam triggerParam) {
        ReturnT<String> result = executorBiz.run(triggerParam);
        return result;
    }

}

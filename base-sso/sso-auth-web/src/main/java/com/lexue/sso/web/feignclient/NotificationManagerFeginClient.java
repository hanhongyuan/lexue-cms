package com.lexue.sso.web.feignclient;

import com.lexue.sso.web.feignclient.fallback.NotificationManagerFeginHystrix;
import main.java.com.lexue.push.domain.ManagerResult;
import main.java.com.lexue.push.domain.PushPlanInfo;
import main.java.com.lexue.push.domain.PushTaskInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * author lilong
 */
@FeignClient(name = "PUSH-PROVIDER-SERVICE",fallback = NotificationManagerFeginHystrix.class)
public interface NotificationManagerFeginClient {

    @PostMapping({"/push/plan"})
    ManagerResult<Long> syncPushPlanInfo(@RequestBody PushPlanInfo var1);

    @GetMapping({"/task/get"})
    ManagerResult<PushTaskInfo> getPushTask(Long var1);

    @GetMapping({"/task/cancel"})
    ManagerResult<PushTaskInfo> cancelPushTask(Long var1);
}
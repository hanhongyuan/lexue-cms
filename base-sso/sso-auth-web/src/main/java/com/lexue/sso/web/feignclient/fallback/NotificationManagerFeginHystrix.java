package com.lexue.sso.web.feignclient.fallback;

import com.lexue.base.util.CodeEnum;
import com.lexue.base.util.ResultUtil;
import com.lexue.sso.web.feignclient.NotificationManagerFeginClient;
import main.java.com.lexue.push.domain.ManagerResult;
import main.java.com.lexue.push.domain.PushPlanInfo;
import main.java.com.lexue.push.domain.PushTaskInfo;
import org.springframework.stereotype.Component;

/**
 * author lilong
 */
@Component
public class NotificationManagerFeginHystrix implements NotificationManagerFeginClient {
    @Override
    public ManagerResult<Long> syncPushPlanInfo(PushPlanInfo var1) {
        return new ManagerResult(500,"调用远程服务错误");
    }

    @Override
    public ManagerResult<PushTaskInfo> getPushTask(Long var1) {
        return new ManagerResult(500,"调用远程服务错误");
    }

    @Override
    public ManagerResult<PushTaskInfo> cancelPushTask(Long var1) {
        return new ManagerResult(500,"调用远程服务错误");
    }
}
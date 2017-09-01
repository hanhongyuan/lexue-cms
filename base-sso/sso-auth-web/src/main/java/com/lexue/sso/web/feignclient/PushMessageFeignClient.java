package com.lexue.sso.web.feignclient;

import com.lexue.base.domain.PushMessage;
import com.lexue.base.util.ResultData;
import com.lexue.sso.web.feignclient.fallback.PushMessageFeignHystrix;
import main.java.com.lexue.push.domain.RuleInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author lilong
 */
@FeignClient(name = "${spring.sso-auth-name}",fallback = PushMessageFeignHystrix.class)
public interface PushMessageFeignClient {


    @GetMapping("/push/getPushMessage")
    ResultData<PushMessage> getPushMessage(@RequestHeader("id")String id);

    @GetMapping("/push/findAll")
    ResultData<List<PushMessage>> findAll(@RequestHeader("client") String client);

    @GetMapping("/push/findPage")
    ResultData<List<PushMessage>> findPage(@RequestHeader("pageIndex") int pageIndex, @RequestHeader("pageSize") int pageSize, @RequestHeader("client") String client);

    @GetMapping("/push/findPageCount")
    ResultData<Integer> findPageCount( @RequestHeader("client") String client);

    @DeleteMapping("/push/deleteRuleInfo")
    ResultData<Boolean> deleteRuleInfo(@RequestHeader("id") String id);

    @PostMapping("/push")
    ResultData<Boolean> addRuleInfo(@RequestBody(required = true) PushMessage ruleInfo);

    @PutMapping("/push")
    ResultData<Boolean> updateRuleInfo(@RequestBody(required = true) PushMessage ruleInfo);
}
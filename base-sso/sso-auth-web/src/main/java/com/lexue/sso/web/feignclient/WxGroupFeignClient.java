package com.lexue.sso.web.feignclient;

import com.lexue.base.domain.Dict;
import com.lexue.base.domain.Group;
import com.lexue.base.domain.WxGroup;
import com.lexue.base.util.ResultData;
import com.lexue.sso.web.feignclient.fallback.LogFeignHystrix;
import com.lexue.sso.web.feignclient.fallback.WxFeignClient;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lilong on 17-8-3.
 */

@FeignClient(name = "${spring.sso-auth-name}",fallback = WxFeignClient.class)
public interface WxGroupFeignClient {

    @PostMapping("/wx/WxGroup")
    ResultData<Boolean> addWxGroup(@RequestBody(required = true) WxGroup wxGroup);

    @PutMapping("/wx/WxGroup")
    ResultData<Boolean> updateWxGroup(@RequestBody(required = true) WxGroup wxGroup);

    @GetMapping("/wx/WxGroup/findPage")
    ResultData<List<WxGroup>> findPage(@RequestHeader("pageIndex") int pageIndex, @RequestHeader("pageSize") int pageSize);

    @GetMapping("/wx/WxGroup/findPageCount")
    ResultData<Integer> findPageCount();

    @GetMapping("/wx/WxGroup/getWxGroup")
    ResultData<WxGroup> getWxGroup(@RequestHeader("id")String id);

    @DeleteMapping("/wx/WxGroup")
    ResultData<Boolean> deleteWxGroup(@RequestHeader("id") String id);

    @PostMapping("/wx/Group")
    ResultData<Boolean> addGroup(@RequestBody(required = true) Group group);

    @PutMapping("/wx/Group")
    ResultData<Boolean> updateGroup(@RequestBody(required = true) Group group);

    @GetMapping("/wx/Group/findPage")
    ResultData<List<Group>> findPageGroup(@RequestHeader("pageIndex") int pageIndex, @RequestHeader("pageSize") int pageSize);

    @GetMapping("/wx/Group/findPageCount")
    ResultData<Integer> findPageGroupCount();

    @GetMapping("/wx/Group/getGroup")
    ResultData<Group> getGroup(@RequestHeader("id")String id);

    @DeleteMapping("/wx/Group")
    ResultData<Boolean> deleteGroup(@RequestHeader("id") String id);

    @GetMapping("/wx/Group/findGroupList")
    ResultData<List<Group>> findGroupList();
}

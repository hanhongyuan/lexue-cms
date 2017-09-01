package com.lexue.sso.web.feignclient;

import com.lexue.base.domain.AdFrames;
import com.lexue.base.domain.AdRela;
import com.lexue.base.util.ResultData;
import com.lexue.sso.web.feignclient.fallback.AdFrameFeignHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author lilong
 */
@FeignClient(name = "${spring.sso-auth-name}",fallback = AdFrameFeignHystrix.class)
public interface AdFrameFeignClient  {

    @GetMapping("/adFrame/findAll")
    ResultData<List<AdFrames>> findAll(@RequestHeader("client") String client);

    @GetMapping("/adFrame/findPage")
    ResultData<List<AdFrames>> findPage(@RequestHeader("pageIndex") int pageIndex, @RequestHeader("pageSize") int pageSize,@RequestHeader("client") String client);

    @GetMapping("/adFrame/findPageCount")
    ResultData<Integer> findPageCount(@RequestHeader("client") String client);

    @DeleteMapping("/adFrame/deleteAdFrame")
    ResultData<Boolean> deleteAdFrame(@RequestHeader("id") String id);

    @PostMapping("/adFrame")
    ResultData<Boolean> addAdFrame(@RequestBody(required = true) AdFrames adFrames);

    @PutMapping("/adFrame")
    ResultData<Boolean> updateAdFrame(@RequestBody(required = true) AdFrames adFrames);


    @GetMapping("/adFrame/getAdFrame")
    ResultData<AdFrames> getAdFrame(@RequestHeader("id") String id);

    @GetMapping("/adFrame/getAdFrameToFmId")
    ResultData<AdFrames> getAdFrameToFmId(@RequestHeader("fmId") String fmId);

    @GetMapping("/adFrame/getAdFrameAll")
    ResultData<AdFrames> getAdFrameAll(@RequestHeader("id") String id);

    @PostMapping("/adFrame/addAdRela")
    ResultData<Boolean> addAdRela(@RequestBody(required = true) AdRela adRela);

    @PutMapping("/adFrame/updateAdRela")
    ResultData<Boolean> updateAdRela(@RequestBody(required = true) AdRela adRela);


    @GetMapping("/adFrame/getAdRela")
    ResultData<AdRela> getAdRela(@RequestHeader("id") String id);

    @GetMapping("/adFrame/getAdRelaToFarmeId")
    ResultData<AdRela> getAdRelaToFarmeId(@RequestHeader("id") String id);

    @GetMapping("/adFrame/getAdFrameToTplId")
    ResultData<List<AdFrames>> getAdFrameToTplId(@RequestHeader("tplId") String tplId);
    @GetMapping("/adFrame/getAdRelaToRsId")
    ResultData<List<AdRela>> getAdRelaToRsId(@RequestHeader("rsId") String rsId);
}
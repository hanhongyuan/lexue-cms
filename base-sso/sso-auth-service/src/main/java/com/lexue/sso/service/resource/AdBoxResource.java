package com.lexue.sso.service.resource;

import com.lexue.base.domain.AdBoxs;
import com.lexue.base.util.ResultData;
import com.lexue.base.util.ResultUtil;
import com.lexue.sso.service.service.AdBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author lilong
 */
@RestController
@RequestMapping("/adBox")
public class AdBoxResource {

    @Autowired
    private AdBoxService adBoxService;
    @GetMapping("/findAll")
    ResultData<List<AdBoxs>> findAll(@RequestHeader("client") String client){
        return ResultUtil.success(adBoxService.findAll(client));
    }

    @GetMapping("/findPage")
    ResultData<List<AdBoxs>> findPage(@RequestHeader("pageIndex") int pageIndex, @RequestHeader("pageSize") int pageSize, @RequestHeader("client") String client){
        return ResultUtil.success(adBoxService.findPage(pageIndex,pageSize,client));
    }

    @GetMapping("/findPageCount")
    ResultData<Integer> findPageCount( @RequestHeader("client") String client){
        return ResultUtil.success(adBoxService.findPageCount(client));
    }

    @DeleteMapping("/deleteAdBox")
    ResultData<Boolean> deleteAdBox(@RequestHeader("id") String id){
        return ResultUtil.success(adBoxService.deleteById(id));
    }

    @PostMapping
    ResultData<Boolean> addAdBox(@RequestBody(required = true) AdBoxs adBox){
        return ResultUtil.success(adBoxService.add(adBox));
    }

    @PutMapping
    ResultData<Boolean> updateAdBox(@RequestBody(required = true) AdBoxs adBox){
        return ResultUtil.success(adBoxService.update(adBox));
    }


    @GetMapping("/getAdBoxs")
    ResultData<AdBoxs> getAdBoxs(@RequestHeader("id") String id){
        return ResultUtil.success(adBoxService.getAdBoxs(id));
    }

    @GetMapping("/getAdBoxsToBoxId")
    ResultData<AdBoxs> getAdBoxsToBoxId(@RequestHeader("id") String id){
        return ResultUtil.success(adBoxService.getAdBoxsToBoxId(id));
    }
    @GetMapping("/getAdBoxsToFmId")
    ResultData<AdBoxs> getAdBoxsToFmId(@RequestHeader("fmId") String fmId){
        return ResultUtil.success(adBoxService.getAdBoxsToFmId(fmId));
    }
}
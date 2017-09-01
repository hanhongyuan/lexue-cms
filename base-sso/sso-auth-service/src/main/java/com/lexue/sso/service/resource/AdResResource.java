package com.lexue.sso.service.resource;

import com.lexue.base.domain.AdRes;
import com.lexue.base.util.ResultData;
import com.lexue.base.util.ResultUtil;
import com.lexue.sso.service.service.AdResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author lilong
 */
@RestController
@RequestMapping("/adRes")
public class AdResResource  {

    @Autowired
    private AdResourceService adResourceService;

    @GetMapping("/getAdResource")
    ResultData<AdRes> getAdResource(@RequestHeader("id") String id){
        return ResultUtil.success(adResourceService.getAdResource(id));
    }

    @GetMapping("/getAdResourceToRsId")
    ResultData<AdRes> getAdResourceToRsId(@RequestHeader("rsId") String rsId){
        return ResultUtil.success(adResourceService.getAdResourceToRsId(rsId));
    }

    @GetMapping("/findAll")
    ResultData<List<AdRes>> findAll(@RequestHeader("client") String client){
        return ResultUtil.success(adResourceService.findAll(client));
    }

    @GetMapping("/findPage")
    ResultData<List<AdRes>> findPage(@RequestHeader("pageIndex") int pageIndex, @RequestHeader("pageSize") int pageSize,@RequestHeader("client") String client){
        return ResultUtil.success(adResourceService.findPage(pageIndex,pageSize,client));
    }

    @GetMapping("/findPageCount")
    ResultData<Integer> findPageCount(@RequestHeader("client") String client){
        return ResultUtil.success(adResourceService.findPageCount(client));
    }

    @DeleteMapping("/deleteAdResource")
    ResultData<Boolean> deleteAdResource(@RequestHeader("id") String id){
        return ResultUtil.success(adResourceService.deleteAdResource(id));
    }

    @PostMapping
    ResultData<Boolean> addAdResource(@RequestBody(required = true) AdRes adRes){
        return ResultUtil.success(adResourceService.add(adRes));
    }

    @PutMapping
    ResultData<Boolean> updateAdResource(@RequestBody(required = true) AdRes adRes){
        return ResultUtil.success(adResourceService.update(adRes));
    }

}
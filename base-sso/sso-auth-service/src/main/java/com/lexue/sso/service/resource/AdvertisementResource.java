package com.lexue.sso.service.resource;

import com.lexue.base.domain.Advertisement;
import com.lexue.base.util.ResultData;
import com.lexue.base.util.ResultUtil;
import com.lexue.sso.service.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author lilong
 */
@RestController
@RequestMapping("/ad")
public class AdvertisementResource {

    @Autowired
    private AdvertisementService advertisementService;

    @GetMapping("/findPage")
    ResultData<List<Advertisement>> findPage(@RequestHeader("pageIndex") int pageIndex, @RequestHeader("pageSize") int pageSize){
        return ResultUtil.success(advertisementService.findPage(pageIndex,pageSize));
    }

    @GetMapping("/findPageCount")
    ResultData<Integer> findPageCount(){
        return ResultUtil.success(advertisementService.findPageCount());
    }

    @GetMapping("/getAdvertisement")
    ResultData<Advertisement> getAdvertisement(@RequestHeader("id") String id){
        return ResultUtil.success(advertisementService.getAdvertisement(id));
    }

    @DeleteMapping("/deleteAdvertisement")
    ResultData<Boolean> deleteAdvertisement(@RequestHeader("id") String id){
        return ResultUtil.success(advertisementService.deleteAdvertisement(id));
    }

    @PostMapping
    ResultData<Boolean> addAdvertisement(@RequestBody(required = true) Advertisement advertisement){
        return ResultUtil.success(advertisementService.addAdvertisement(advertisement));
    }

    @PutMapping
    ResultData<Boolean> updateAdvertisement(@RequestBody(required = true) Advertisement advertisement){
        return ResultUtil.success(advertisementService.updateAdvertisement(advertisement));
    }
}
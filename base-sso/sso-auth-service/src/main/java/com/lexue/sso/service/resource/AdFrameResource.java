package com.lexue.sso.service.resource;

import com.lexue.base.domain.AdFrames;
import com.lexue.base.domain.AdRela;
import com.lexue.base.domain.AdTemplates;
import com.lexue.base.util.DateUtils;
import com.lexue.base.util.ResultData;
import com.lexue.base.util.ResultUtil;
import com.lexue.sso.service.service.AdFarmeService;
import com.lexue.sso.service.service.AdRelaService;
import com.lexue.sso.service.service.AdResourceService;
import com.lexue.sso.service.service.AdTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author lilong
 */
@RestController
@RequestMapping("/adFrame")
public class AdFrameResource {

    @Autowired
    private AdFarmeService adFrameService;
    @Autowired
    private AdRelaService adRelaService;
    @Autowired
    private AdTemplateService adTemplateService;
    @Autowired
    private AdResourceService adResourceService;

    @GetMapping("/findAll")
    ResultData<List<AdFrames>> findAll(@RequestHeader("client") String client){
        return ResultUtil.success(adFrameService.findAll(client));
    }

    @GetMapping("/findPage")
    ResultData<List<AdFrames>> findPage(@RequestHeader("pageIndex") int pageIndex, @RequestHeader("pageSize") int pageSize,@RequestHeader("client") String client){
        List<AdFrames> list=adFrameService.findPage(pageIndex,pageSize,client);
        for(AdFrames adFrames:list){
            AdTemplates adTemplates=adTemplateService.getAdTpl(adFrames.getTplId()+"");
            adFrames.setAdTplName(adTemplates.getTitle());
            AdRela adRela=adRelaService.getAdRelaToFarmeId(adFrames.getFrameId()+"");
            adFrames.setAdResName(adResourceService.getAdResourceToRsId(adRela.getResourceId()+"").getTitle());
            try {
                adFrames.setStartTime(DateUtils.timestamp2Str(adFrames.getEnableTimestamp(),DateUtils.DATE_FORMAT_STR7));
                adFrames.setEndTime(DateUtils.timestamp2Str(adFrames.getDisableTimestamp(),DateUtils.DATE_FORMAT_STR7));
            }catch (Exception e){

            }
        }
        return ResultUtil.success(list);
    }

    @GetMapping("/findPageCount")
    ResultData<Integer> findPageCount(@RequestHeader("client") String client){
        return ResultUtil.success(adFrameService.findPageCount(client));
    }

    @DeleteMapping("/deleteAdFrame")
    ResultData<Boolean> deleteAdFrame(@RequestHeader("id") String id){
        return ResultUtil.success(adFrameService.deleteAdFrame(id));
    }

    @PostMapping
    ResultData<Boolean> addAdFrame(@RequestBody(required = true) AdFrames adFrames){
        return ResultUtil.success(adFrameService.add(adFrames));
    }

    @PutMapping
    ResultData<Boolean> updateAdFrame(@RequestBody(required = true) AdFrames adFrames){
        return ResultUtil.success(adFrameService.update(adFrames));
    }


    @GetMapping("/getAdFrame")
    ResultData<AdFrames> getAdFrame(@RequestHeader("id") String id){
        AdFrames adFrames=adFrameService.getAdFrame(id);
        return ResultUtil.success(adFrames);
    }
    @GetMapping("/getAdFrameAll")
    ResultData<AdFrames> getAdFrameAll(@RequestHeader("id") String id){
        AdFrames adFrames=adFrameService.getAdFrame(id);
        AdRela adRela=adRelaService.getAdRelaToFarmeId(adFrames.getFrameId()+"");
        adFrames.setAdResId(adRela.getResourceId());
        adFrames.setAdResName(adResourceService.getAdResourceToRsId(adRela.getResourceId()+"").getTitle());
        try {
            adFrames.setStartTime(DateUtils.timestamp2Str(adFrames.getEnableTimestamp(),DateUtils.DATE_FORMAT_STR7));
            adFrames.setEndTime(DateUtils.timestamp2Str(adFrames.getDisableTimestamp(),DateUtils.DATE_FORMAT_STR7));
        }catch (Exception e){

        }
        return ResultUtil.success(adFrames);
    }

    @PostMapping("/addAdRela")
    ResultData<Boolean> addAdRela(@RequestBody(required = true) AdRela adRela){
        return ResultUtil.success(adRelaService.add(adRela));
    }

    @PutMapping("/updateAdRela")
    ResultData<Boolean> updateAdRela(@RequestBody(required = true) AdRela adRela){
        return ResultUtil.success(adRelaService.update(adRela));
    }


    @GetMapping("/getAdRela")
    ResultData<AdRela> getAdRela(@RequestHeader("id") String id){
        return ResultUtil.success(adRelaService.getAdRela(id));
    }
    @GetMapping("/getAdRelaToFarmeId")
    ResultData<AdRela> getAdRelaToFarmeId(@RequestHeader("id") String id){
        return ResultUtil.success(adRelaService.getAdRelaToFarmeId(id));
    }

    @GetMapping("/getAdFrameToTplId")
    ResultData<List<AdFrames>> getAdFrameToTplId(@RequestHeader("tplId") String tplId){
        return ResultUtil.success(adFrameService.getAdFrameToTplId(tplId));
    }
    @GetMapping("/getAdRelaToRsId")
    ResultData<List<AdRela>> getAdRelaToRsId(@RequestHeader("rsId") String rsId){
        return ResultUtil.success(adRelaService.getAdRelaToRsId(rsId));
    }
    @GetMapping("/getAdFrameToFmId")
    ResultData<AdFrames> getAdFrameToFmId(@RequestHeader("fmId") String fmId){
        return ResultUtil.success(adFrameService.getAdFrameToFmId(fmId));
    }
}
package com.lexue.sso.service.resource;

import com.lexue.base.domain.AdBoxs;
import com.lexue.base.domain.AdFilters;
import com.lexue.base.domain.AdTemplates;
import com.lexue.base.model.AdFilterTplModel;
import com.lexue.base.util.ResultData;
import com.lexue.base.util.ResultUtil;
import com.lexue.sso.service.service.AdBoxService;
import com.lexue.sso.service.service.AdFilterService;
import com.lexue.sso.service.service.AdResourceService;
import com.lexue.sso.service.service.AdTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author lilong
 */
@RestController
@RequestMapping("/adTpl")
public class AdTemplateResource {

    @Autowired
    private AdTemplateService adTemplateService;
    @Autowired
    private AdFilterService adFilterService;
    @Autowired
    private AdBoxService adBoxService;

    @GetMapping("/findAll")
    ResultData<List<AdTemplates>> findAll(@RequestHeader("client") String client){
        return ResultUtil.success(adTemplateService.findAll(client));
    }

    @GetMapping("/findPage")
    ResultData<List<AdTemplates>> findPage(@RequestHeader("pageIndex") int pageIndex, @RequestHeader("pageSize") int pageSize,@RequestHeader("client") String client){
        List<AdTemplates> list=adTemplateService.findPage(pageIndex,pageSize,client);
        for(AdTemplates adTemplates:list){
            AdFilters adFilters=adFilterService.getAdFilterToTplId(adTemplates.getTemplateId()+"");
            AdBoxs adBoxs=adBoxService.getAdBoxsToBoxId(adFilters.getBoxId()+"");
            adTemplates.setAdBoxName(adBoxs.getTitle());
            adTemplates.setConditionMap(adFilters.getConditionMap());
            adTemplates.setDefaultFilter(adFilters.getDefaultFilter());
        }
        return ResultUtil.success(list);
    }

    @GetMapping("/findPageCount")
    ResultData<Integer> findPageCount(@RequestHeader("client") String client){
        return ResultUtil.success(adTemplateService.findPageCount(client));
    }

    @DeleteMapping("/deleteAdTpl")
    ResultData<Boolean> deleteAdTpl(@RequestHeader("id") String id){
        return ResultUtil.success(adTemplateService.deleteAdTpl(id));
    }

    @PostMapping
    ResultData<Boolean> addAdTpl(@RequestBody(required = true) AdTemplates adTemplates){
        return ResultUtil.success(adTemplateService.add(adTemplates));
    }

    @PutMapping
    ResultData<Boolean> updateAdTpl(@RequestBody(required = true) AdTemplates adTemplates){
        return ResultUtil.success(adTemplateService.update(adTemplates));
    }

    @GetMapping("/getAdTpl")
    ResultData<AdTemplates> getAdTpl(@RequestHeader("id") String id){
        return ResultUtil.success(adTemplateService.getAdTpl(id));
    }
    @GetMapping("/getAdTplToId")
    ResultData<AdTemplates> getAdTplToId(@RequestHeader("id") String id){
        return ResultUtil.success(adTemplateService.getAdTplToId(id));
    }
    @PostMapping("/addFilter")
    ResultData<Boolean> addFilter(@RequestBody(required = true) AdFilters adFilters){
        return ResultUtil.success(adFilterService.add(adFilters));
    }
    @GetMapping("/getAdFilter")
    ResultData<AdFilters> getAdFilter(@RequestHeader("id") String id){
        return ResultUtil.success(adFilterService.getAdFilterById(id));
    }
    @GetMapping("/getAdFilterToTplId")
    ResultData<AdFilters> getAdFilterToTplId(@RequestHeader("id") String id){
        return ResultUtil.success(adFilterService.getAdFilterToTplId(id));
    }
    @PutMapping("/updateAdFilter")
    ResultData<Boolean> updateAdFilter(@RequestBody(required = true) AdFilters adFilters){
        return ResultUtil.success(adFilterService.update(adFilters));
    }

    @GetMapping("/findAllFilterToBoxId")
    ResultData<List<AdFilters>> findAllFilterToBoxId(@RequestHeader("boxId") String boxId){
        return ResultUtil.success(adFilterService.findAllFilterToBoxId(boxId));
    }



    @GetMapping("/getAdFilterTpl")
    ResultData<AdFilterTplModel> getAdFilterTpl(@RequestHeader("id") String id){
        AdTemplates adTemplates=adTemplateService.getAdTplToId(id);
        AdFilters adFilters=adFilterService.getAdFilterToTplId(adTemplates.getTemplateId()+"");
        AdFilterTplModel adFilterTplModel=new AdFilterTplModel();
        adFilterTplModel.setBoxId(adFilters.getBoxId());
        adFilterTplModel.setConditionMap(adFilters.getConditionMap().replace("\"","'"));
        adFilterTplModel.setDefaultFilter(adFilters.getDefaultFilter());
        adFilterTplModel.setFrameCapacity(adTemplates.getFrameCapacity());
        adFilterTplModel.setFramePickType(adTemplates.getFramePickType());
        adFilterTplModel.setFrameSwitchTime(adTemplates.getFrameSwitchTime());
        adFilterTplModel.setFilterId(adFilters.getFilterId());
        adFilterTplModel.setLogic(adFilters.getLogic());
        adFilterTplModel.setNote(adTemplates.getNote());
        adFilterTplModel.setPriority(adFilters.getPriority());
        adFilterTplModel.setStatus(adFilters.getStatus());
        adFilterTplModel.setTitle(adTemplates.getTitle());
        adFilterTplModel.setTplid(adTemplates.getTemplateId());
        return ResultUtil.success(adFilterTplModel);
    }
}
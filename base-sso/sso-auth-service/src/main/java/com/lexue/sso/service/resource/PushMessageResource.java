package com.lexue.sso.service.resource;

import com.alibaba.fastjson.JSON;
import com.lexue.base.domain.Dict;
import com.lexue.base.domain.PushMessage;
import com.lexue.base.util.ResultData;
import com.lexue.base.util.ResultUtil;
import com.lexue.sso.service.service.DictService;
import com.lexue.sso.service.service.PushMessageService;
import com.lexue.type.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author lilong
 */
@RestController
@RequestMapping("/push")
public class PushMessageResource {

    @Autowired
    private PushMessageService pushMessageService;
    @Autowired
    private DictService dictService;

    @GetMapping("/getPushMessage")
    ResultData<PushMessage> getPushMessage(@RequestHeader("id")String id){
        PushMessage pushMessage=pushMessageService.getPushMessage(id);
        Map<String, Object> transmition = new HashMap<String, Object>();
        if(pushMessage.getTransparent()==2){
            switch (pushMessage.getBusinessType()){
                case JUNIOR_TEACH:
                    transmition.put("url","lexuezhongkao://video/"+pushMessage.getTransmission());
                    break;
                case JUNIOR_TEACH_VIP:
                    transmition.put("url","lexuezhongkao://video/"+pushMessage.getTransmission());
                    break;
                case JUNIOR_VOLUNTEER:
                    transmition.put("url","lexuezhongkao://video/"+pushMessage.getTransmission());
                    break;
                case SENIOR_TEACH:
                    transmition.put("url","lexuegaokao://video/"+pushMessage.getTransmission());
                    break;
                case SENIOR_TEACH_VIP:
                    transmition.put("url","lexuegaokao://video/"+pushMessage.getTransmission());
                    break;
                case SENIOR_VOLUNTEER:
                    transmition.put("url","lexuegaokao://video/"+pushMessage.getTransmission());
                    break;
                case TEENS_ENGLISH_GAME:
                    transmition.put("url","lexuegaokao://video/"+pushMessage.getTransmission());
                    break;
            }
        }
        pushMessage.setTransm(transmition);
        return ResultUtil.success(pushMessage);
    }

    @GetMapping("/findAll")
    ResultData<List<PushMessage>> findAll(@RequestHeader("client") String client){
        return ResultUtil.success(pushMessageService.findAll(client));
    }

    @GetMapping("/findPage")
    ResultData<List<PushMessage>> findPage(@RequestHeader("pageIndex") int pageIndex, @RequestHeader("pageSize") int pageSize, @RequestHeader("client") String client){
        List<PushMessage> list=pushMessageService.findPage(pageIndex,pageSize,client);
        List<Dict> dict=dictService.findDictByTitle("TargetType");
        List<Dict> dict2=dictService.findDictByTitle("BusinessType");
        List<Dict> dict3=dictService.findDictByTitle("transparent");
        for(PushMessage pushMessage:list){
           for(Dict dd:dict){
               if(pushMessage.getTargetType().name().equals(dd.getValue())){
                    pushMessage.setTt(dd.getLabel());
               }
           }
           for(Dict tt:dict2){
               if(pushMessage.getBusinessType().getValue()==Integer.parseInt(tt.getValue())){
                    pushMessage.setBt(tt.getLabel());
               }
           }
           for(Dict ss:dict3){
               if(pushMessage.getTransparent()==Integer.parseInt(ss.getValue())){
                    pushMessage.setTp(ss.getLabel());
               }
           }
        }
        return ResultUtil.success(list);
    }

    @GetMapping("/findPageCount")
    ResultData<Integer> findPageCount( @RequestHeader("client") String client){
        return ResultUtil.success(pushMessageService.findPageCount(client));
    }

    @DeleteMapping("/deleteRuleInfo")
    ResultData<Boolean> deleteRuleInfo(@RequestHeader("id") String id){
        return ResultUtil.success(pushMessageService.deleteRuleInfo(id));
    }

    @PostMapping
    ResultData<Boolean> addRuleInfo(@RequestBody(required = true) PushMessage ruleInfo){
        return ResultUtil.success(pushMessageService.addRuleInfo(ruleInfo));
    }

    @PutMapping
    ResultData<Boolean> updateRuleInfo(@RequestBody(required = true) PushMessage ruleInfo){
        return ResultUtil.success(pushMessageService.updateRuleInfo(ruleInfo));
    }
}
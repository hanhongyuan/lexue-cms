package com.lexue.sso.service.service;

import com.lexue.base.domain.PushMessage;
import com.lexue.base.mybatis.BaseService;
import com.lexue.sso.service.mapper.PushMessageMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author lilong
 */
@Service
public class PushMessageService extends BaseService<PushMessageMapper, PushMessage> {

    public PushMessage getPushMessage(String id){
        return mapper.getPushMessage(id);
    }

    public List<PushMessage> findAll(String client){
        return mapper.findAllList(client);
    }

    public List<PushMessage> findPage(int pageIndex,int pageSize,String client){
        List<PushMessage> list=mapper.findPage((pageIndex-1)*pageSize,pageSize,client);
        return list;
    }

    public Integer findPageCount(String client){
        return mapper.findPageCount(client);
    }

    public Boolean deleteRuleInfo(String id){
        mapper.deleteRuleInfo(id);
        return true;
    }

    public Boolean addRuleInfo(PushMessage ruleInfo){
        return add(ruleInfo);
    }

    public Boolean updateRuleInfo(PushMessage ruleInfo){
        return update(ruleInfo);
    }

}
package com.lexue.sso.service.service;

import com.lexue.base.domain.AdBoxs;
import com.lexue.base.mybatis.BaseService;
import com.lexue.base.util.DateUtils;
import com.lexue.sso.service.mapper.AdBoxMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author lilong
 */

@Service
public class AdBoxService  extends BaseService<AdBoxMapper,AdBoxs>{

    public List<AdBoxs> findAll(String client){
        return mapper.getAll(client);
    }

    public List<AdBoxs> findPage(int pageIndex, int pageSize,String client){
        List<AdBoxs> list=mapper.findPage((pageIndex-1)*pageSize,pageSize,client);
        for(AdBoxs adBoxs:list){
            adBoxs.setStatus((int)adBoxs.getStat());
            adBoxs.setUpdateDate(DateUtils.formatLongToString(adBoxs.getUpdateTime()));
        }
        return list;
    }

    public Integer findPageCount(String client){
        return mapper.findPageCount(client);
    }

    void deleteAdBox(String id){
        mapper.deleteAdBox(id);
    }

    public AdBoxs getAdBoxs(String id){
        AdBoxs adBoxs=mapper.getAdBoxs(id);
        adBoxs.setStatus((int)adBoxs.getStat());
        adBoxs.setCompany((int)adBoxs.getUnit());
        return adBoxs;
    }

    public AdBoxs getAdBoxsToBoxId(String id){
        return mapper.getAdBoxsToBoxId(id);
    }
    public AdBoxs getAdBoxsToFmId(String fmId){
        return mapper.getAdBoxsToFmId(fmId);
    }
}
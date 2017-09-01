package com.lexue.sso.service.service;

import com.lexue.base.domain.AdRela;
import com.lexue.base.mybatis.BaseService;
import com.lexue.sso.service.mapper.AdRelaMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author lilong
 */
@Service
public class AdRelaService extends BaseService<AdRelaMapper,AdRela>{


    public AdRela getAdRela(String id){
        return mapper.getAdRela(id);
    }

    public AdRela getAdRelaToFarmeId(String id){
        return mapper.getAdRelaToFarmeId(id);
    }
    public List<AdRela> getAdRelaToRsId(String rsId){
        return mapper.getAdRelaToRsId(rsId);
    }

}
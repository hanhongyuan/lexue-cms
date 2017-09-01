package com.lexue.sso.service.service;

import com.lexue.base.domain.AdRes;
import com.lexue.base.mybatis.BaseService;
import com.lexue.base.util.DateUtils;
import com.lexue.sso.service.mapper.AdResourceMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author lilong
 */
@Service
public class AdResourceService extends BaseService<AdResourceMapper,AdRes> {

    public AdRes getAdResource(String id){
        AdRes adRes=mapper.getAdResource(id);
        adRes.setStat((int)adRes.getStatus());
       return adRes;
    }

    public List<AdRes> findAll(String client){
        return mapper.getAll(client);
    }

    public List<AdRes> findPage(int pageIndex, int pageSize,String client){
        List<AdRes> list= mapper.findPage((pageIndex-1)*pageSize,pageSize,client);
        for(AdRes adRes:list){
            adRes.setStat((int)adRes.getStatus());
            adRes.setUpdateDate(DateUtils.formatLongToString(adRes.getUpdateTime()));
        }
        return list;
    }

    public Integer findPageCount(String client){
        return mapper.findPageCount(client);
    }

    public Boolean deleteAdResource(String id){
        return deleteById(id);
    }

    public Boolean addAdResource(AdRes adRes){
        return add(adRes);
    }

    public Boolean updateAdResource(AdRes adRes){
        return update(adRes);
    }

    public AdRes getAdResourceToRsId(String id){
        AdRes adRes=mapper.getAdResourceToRsId(id);
        adRes.setStat((int)adRes.getStatus());
        return adRes;
    }

}
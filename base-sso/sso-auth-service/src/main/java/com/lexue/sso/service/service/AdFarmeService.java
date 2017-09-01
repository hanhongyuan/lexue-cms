package com.lexue.sso.service.service;

import com.lexue.base.domain.AdFrames;
import com.lexue.base.mybatis.BaseService;
import com.lexue.base.util.DateUtils;
import com.lexue.sso.service.mapper.AdFarmeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author lilong
 */

@Service
public class AdFarmeService extends BaseService<AdFarmeMapper,AdFrames>{

    public List<AdFrames> findAll(String client){
        return mapper.getAll(client);
    }

    public List<AdFrames> findPage(int pageIndex, int pageSize,String client){
        List<AdFrames> list=mapper.findPage((pageIndex-1)*pageSize,pageSize,client);
        for(AdFrames adBoxs:list){

        }
        return list;
    }

    public Integer findPageCount(String client){
        return mapper.findPageCount(client);
    }

    public boolean deleteAdFrame(String id){
        mapper.deleteAdFrame(id);
        return true;
    }

    public AdFrames getAdFrame(String id){
        AdFrames adBoxs=mapper.getAdFrame(id);
        return adBoxs;
    }

    public List<AdFrames> getAdFrameToTplId(String tplId){
        return mapper.getAdFrameToTplId(tplId);
    }
    public AdFrames getAdFrameToFmId(String fmId){
        return mapper.getAdFrameToFmId(fmId);
    }
}
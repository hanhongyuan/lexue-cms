package com.lexue.sso.service.service;

import com.lexue.base.domain.AdBoxs;
import com.lexue.base.domain.AdFilters;
import com.lexue.base.domain.AdTemplates;
import com.lexue.base.mybatis.BaseService;
import com.lexue.sso.service.mapper.AdBoxMapper;
import com.lexue.sso.service.mapper.AdFilterMapper;
import com.lexue.sso.service.mapper.AdTemplatesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author lilong
 */
@Service
public class AdTemplateService extends BaseService<AdTemplatesMapper,AdTemplates> {

    @Autowired
    private AdBoxMapper adBoxMapper;
    @Autowired
    private AdFilterMapper adFilterMapper;
    public List<AdTemplates> findAll(String client){
        return mapper.getAll(client);
    }

    public List<AdTemplates> findPage(int pageIndex,int pageSize,String client){
        List<AdTemplates> list=mapper.findPage((pageIndex-1)*pageSize,pageSize,client);
        return list;
    }

    public Integer findPageCount(String client){
        return mapper.findPageCount(client);
    }

    public Boolean deleteAdTpl(String id){
        mapper.deleteAdTpl(id);
        return true;
    }

    public AdTemplates getAdTpl(String id){
        return mapper.getAdTpl(id);
    }
    public AdTemplates getAdTplToId(String id){
        return mapper.getAdTplToId(id);
    }
}
package com.lexue.sso.service.service;

import com.lexue.base.domain.AdFilters;
import com.lexue.base.mybatis.BaseService;
import com.lexue.sso.service.mapper.AdFilterMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author lilong
 */
@Service
public class AdFilterService extends BaseService<AdFilterMapper,AdFilters>{


    public AdFilters getAdFilterToTplId(String id){
        return mapper.getAdFilterToTplId(id);
    }

    public AdFilters getAdFilterById(String id){
        return mapper.getAdFilterById(id);
    }

    public List<AdFilters> findAllFilterToBoxId(String boxId){
        return mapper.findAllFilterToBoxId(boxId);
    }
}
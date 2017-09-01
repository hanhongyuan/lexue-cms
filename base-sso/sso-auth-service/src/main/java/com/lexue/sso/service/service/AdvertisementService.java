package com.lexue.sso.service.service;

import com.lexue.base.domain.Advertisement;
import com.lexue.base.mybatis.BaseService;
import com.lexue.sso.service.mapper.AdvertisementMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author lilong
 */
@Service
public class AdvertisementService extends BaseService<AdvertisementMapper, Advertisement> {

    public List<Advertisement> findPage(int pageIndex, int pageSize){
       return mapper.findPage((pageIndex-1)*pageSize,pageSize);
    }

    public Integer findPageCount(){
        return mapper.findPageCount();
    }

    public Advertisement getAdvertisement(String id){
        return mapper.getAdvertisement(id);
    }

    public Boolean deleteAdvertisement(String id){
         mapper.deleteAdvertisement(id);
        return true;
    }

    public Boolean addAdvertisement(Advertisement advertisement){
        mapper.insert(advertisement);
        return true;
    }

    public Boolean updateAdvertisement(Advertisement advertisement){
        mapper.update(advertisement);
        return true;
    }
}
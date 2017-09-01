package com.lexue.sso.service.service;

import com.lexue.base.domain.WxGroup;
import com.lexue.base.mybatis.BaseService;
import com.lexue.sso.service.mapper.WxGroupMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lilong on 17-8-3.
 */
@Service
public class WxGroupService extends BaseService<WxGroupMapper,WxGroup>{

    @Transactional(readOnly = true)
    public List<WxGroup> findPage(int pageIndex, int pageSize){
        return mapper.findPage((pageIndex-1)*pageSize,pageSize);
    }
    @Transactional(readOnly = true)
    public int findPageCount(){
        return mapper.findPageCount();
    }
    @Transactional(readOnly = true)
    public int getWxGroupCount(String groupId){
        return mapper.getWxGroupCount(groupId);
    }
    @Transactional(readOnly = true)
    public int getWxGroupSum(String groupId){
        return mapper.getWxGroupSum(groupId);
    }

}

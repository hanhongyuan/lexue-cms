package com.lexue.sso.service.service;

import com.lexue.base.domain.Group;
import com.lexue.base.mybatis.BaseService;
import com.lexue.sso.service.mapper.GroupMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lilong on 17-8-3.
 */
@Service
public class GroupService extends BaseService<GroupMapper, Group> {

    @Transactional(readOnly = true)
    public List<Group> findPage(int pageIndex, int pageSize){
        return mapper.findPage((pageIndex-1)*pageSize,pageSize);
    }
    @Transactional(readOnly = true)
    public int findPageCount(){
        return mapper.findPageCount();
    }

    @Transactional(readOnly = true)
    public List<Group> findGroupList(){
        return mapper.findGroupList();
    }
}

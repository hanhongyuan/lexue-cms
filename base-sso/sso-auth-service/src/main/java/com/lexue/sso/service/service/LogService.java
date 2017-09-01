package com.lexue.sso.service.service;

import com.lexue.base.domain.Dict;
import com.lexue.base.domain.Log;
import com.lexue.base.mybatis.BaseService;
import com.lexue.base.util.DateUtils;
import com.lexue.sso.service.mapper.DictMapper;
import com.lexue.sso.service.mapper.LogMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 字典的服务
 * <P>
 * 
 */
@Service
public class LogService extends BaseService<LogMapper, Log> {

    @Transactional(readOnly = true)
    public List<Log> findPage(int pageIndex, int pageSize){
        List<Log>  list=mapper.findPage((pageIndex-1)*pageSize,pageSize);
        for(Log log:list){
            log.setOperTime(DateUtils.format(log.getCreateDate()));
        }
        return list;
    }
    @Transactional(readOnly = true)
    public int findPageCount(){
        return mapper.findPageCount();
    }
}

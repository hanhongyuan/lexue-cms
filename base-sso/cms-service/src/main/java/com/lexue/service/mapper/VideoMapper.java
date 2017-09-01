package com.lexue.service.mapper;

import com.lexue.base.domain.Video;
import com.lexue.base.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * author lilong
 */
@Mapper
public interface VideoMapper extends BaseMapper<Video>{

    @Select("select a.video_id id,a.video_title title from video a where a.client='${client}' order by a.video_id desc")
    public List<Video> findAllVideo(@Param("client") String client);
}
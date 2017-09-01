package com.lexue.service.service;

import com.lexue.base.domain.Video;
import com.lexue.base.mybatis.BaseService;
import com.lexue.service.mapper.VideoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author lilong
 */
@Service
public class VideoService extends BaseService<VideoMapper,Video>{


    public List<Video> findAllVideo(String client){
        return mapper.findAllVideo(client);
    }

}
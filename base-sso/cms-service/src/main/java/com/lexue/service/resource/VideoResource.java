package com.lexue.service.resource;

import com.lexue.base.domain.Video;
import com.lexue.base.util.ResultData;
import com.lexue.base.util.ResultUtil;
import com.lexue.service.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author lilong
 */
@RestController
@RequestMapping("/video")
public class VideoResource {

    @Autowired
    private VideoService videoService;

    @GetMapping("/findAll")
    ResultData<List<Video>> findAllVideo(@RequestHeader("client")String client){
        List<Video> list=videoService.findAllVideo(client);
        return ResultUtil.success(list);
    }
}
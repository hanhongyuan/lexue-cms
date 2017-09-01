package com.lexue.sso.service.resource;

import com.lexue.base.domain.Group;
import com.lexue.base.domain.WxGroup;
import com.lexue.base.util.ResultData;
import com.lexue.base.util.ResultUtil;
import com.lexue.sso.service.service.GroupService;
import com.lexue.sso.service.service.WxGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lilong on 17-8-3.
 */
@RestController
@RequestMapping("/wx")
public class WxResource {

    @Autowired
    private WxGroupService wxGroupService;
    @Autowired
    private GroupService groupService;

    @PostMapping("/WxGroup")
    public ResultData<Boolean> addWxGroup(@RequestBody(required = true) WxGroup wxGroup){
        return ResultUtil.success(wxGroupService.add(wxGroup));
    }

    @PutMapping("/WxGroup")
    public ResultData<Boolean> updateWxGroup(@RequestBody(required = true) WxGroup wxGroup){
        return ResultUtil.success(wxGroupService.update(wxGroup));
    }

    @GetMapping("/WxGroup/findPage")
    public ResultData<List<WxGroup>> findPage(@RequestHeader("pageIndex") int pageIndex, @RequestHeader("pageSize") int pageSize){
        List<WxGroup> list=wxGroupService.findPage(pageIndex,pageSize);
        for(WxGroup wxGroup:list){
            wxGroup.setGroupName(groupService.getById(wxGroup.getGroupId()).getGroupName());
        }
        return ResultUtil.success(list);
    }

    @GetMapping("/WxGroup/findPageCount")
    public ResultData<Integer> findPageCount(){
        return ResultUtil.success(wxGroupService.findPageCount());
    }

    @GetMapping("/WxGroup/getWxGroup")
    public ResultData<WxGroup> getWxGroup(@RequestHeader("id")String id){
        return ResultUtil.success(wxGroupService.getById(id));
    }

    @DeleteMapping("/WxGroup")
    public ResultData<Boolean> deleteWxGroup(@RequestHeader("id") String id){
        return ResultUtil.success(wxGroupService.deleteById(id));
    }

    @PostMapping("/Group")
    public ResultData<Boolean> addGroup(@RequestBody(required = true) Group group){
        return ResultUtil.success(groupService.add(group));
    }

    @PutMapping("/Group")
    public ResultData<Boolean> updateGroup(@RequestBody(required = true) Group group){
        return ResultUtil.success(groupService.update(group));
    }

    @GetMapping("/Group/findPage")
    public ResultData<List<Group>> findPageGroup(@RequestHeader("pageIndex") int pageIndex, @RequestHeader("pageSize") int pageSize){
        List<Group> list=groupService.findPage(pageIndex, pageSize);
        for(Group group:list){
            group.setGroupSum(wxGroupService.getWxGroupCount(group.getId())+"");
            group.setSurplusNum(wxGroupService.getWxGroupSum(group.getId())+"");
        }
        return ResultUtil.success(list);
    }

    @GetMapping("/Group/findPageCount")
    public ResultData<Integer> findPageGroupCount(){
        return ResultUtil.success(groupService.findPageCount());
    }

    @GetMapping("/Group/getGroup")
    public ResultData<Group> getGroup(@RequestHeader("id")String id){
        return ResultUtil.success(groupService.getById(id));
    }

    @DeleteMapping("/Group")
    public ResultData<Boolean> deleteGroup(@RequestHeader("id") String id){
        return ResultUtil.success(groupService.deleteById(id));
    }

    @GetMapping("/Group/findGroupList")
    public ResultData<List<Group>> findGroupList(){
        return ResultUtil.success(groupService.findGroupList());
    }

}

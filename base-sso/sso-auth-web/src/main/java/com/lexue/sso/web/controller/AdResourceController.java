package com.lexue.sso.web.controller;

import com.lexue.base.annotation.SystemControllerLog;
import com.lexue.base.annotation.ssoauth.Permission;
import com.lexue.base.domain.AdFrames;
import com.lexue.base.domain.AdRela;
import com.lexue.base.domain.AdRes;
import com.lexue.base.domain.AdResource;
import com.lexue.base.util.*;
import com.lexue.sso.web.feignclient.AdFrameFeignClient;
import com.lexue.sso.web.feignclient.AdResourceFeignClient;
import com.lexue.sso.web.webconfig.WebConfig;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * author lilong
 */
@Controller
@RequestMapping("/adRes")
public class AdResourceController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    AdResourceFeignClient adResourceFeignClient;
    @Autowired
    AdFrameFeignClient adFrameFeignClient;

    @SystemControllerLog(module = "广告资源管理",method = "广告资源列表")
    @Permission("sys:adres:list")
    @RequestMapping(value ={"/","/list"},method = RequestMethod.GET)
    public String index(){
        return "ad/adRes_list";
    }

    @SystemControllerLog(module = "广告资源管理",method = "新增广告资源")
    @Permission("sys:adres:add")
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(String id,Model model){
        if(id!=null){
            model.addAttribute("adRes",adResourceFeignClient.getAdResource(id).getData());
        }else
            model.addAttribute("adRes",new AdResource());
        return "ad/adRes_add";
    }
    @RequestMapping(value = "/data",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<AdRes> data(@RequestParam(value = "pageIndex",defaultValue ="1")Integer pageIndex,
                                              @RequestParam(value = "pageSize",defaultValue = "15")Integer pageSize){
        ResponseResult<AdRes> data=new ResponseResult<AdRes>();
        logger.info("AdResourceController init PageData");
        List<AdRes> list=adResourceFeignClient.findPage(pageIndex,pageSize,getClient()).getData();
        data.setList(list);
        data.setRel(true);
        data.setCount(adResourceFeignClient.findPageCount(getClient()).getData());
        return data;
    }

    @SystemControllerLog(module = "广告资源管理",method = "保存广告资源")
    @Permission("sys:adres:save")
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(AdRes adRes){
        logger.info("Advertisement save start");
        if(adRes.getId()==null||"".equals(adRes.getId())){
            adRes.setUpdateTime(new Date().getTime());
            adRes.setClient(getClient());
            //adRes.setStatus((byte) adRes.getStatus());
            adRes.setUpName(getLoginUser().getLoginName());
            adResourceFeignClient.addAdResource(adRes);
        }else{
            adRes.setUpdateTime(new Date().getTime());
            //adRes.setStatus((byte) adRes.getStat());
            adRes.setUpName(getLoginUser().getLoginName());
            adResourceFeignClient.updateAdResource(adRes);
            adRes=adResourceFeignClient.getAdResource(adRes.getId()).getData();
            if(adRes.getStatus()==1){
                List<AdRela> list=adFrameFeignClient.getAdRelaToRsId(adRes.getResourceId()+"").getData();
                for(AdRela adRela:list){
                    AdFrames adFrames=adFrameFeignClient.getAdFrameToFmId(adRela.getFrameId()+"").getData();
                    adFrames.setStatus((byte)1);
                    adFrameFeignClient.updateAdFrame(adFrames);
                }
            }

        }
        return "success";
    }

    @SystemControllerLog(module = "广告资源管理",method = "删除广告资源")
    @Permission("sys:adres:delete")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public void delete(String id){
        logger.info("Advertisement delete id is {}"+id);
        adResourceFeignClient.deleteAdResource(id);
    }
    @RequestMapping("/upload")
    @ResponseBody
    public ResultData<String> uploadQcCode(@RequestParam("file") MultipartFile file) throws Exception{
        if(!file.isEmpty()){
            String trueName=file.getOriginalFilename();
            String fileName=generateFileName("",trueName);
            String type=trueName.substring(trueName.lastIndexOf(".")+1,trueName.length());
            String path="";
            try {
                if("mp4".equals(type)){
                    path=webConfig.getVideo();
                }else if("jpeg".equals(type)||"bng".equals(type)||"jpg".equals(type)){
                    path=webConfig.getPicture();
                }
                logger.info("getEndpoint" + webConfig.getEndpoint() + "getKey" + webConfig.getKey() + "getSecret" + webConfig.getSecret() + "getBucket" + webConfig.getBucket() + "getVideo" + webConfig.getVideo() + "");
                String result=OSSUtils.uploadOSS(file,fileName,webConfig.getEndpoint(),webConfig.getKey(),webConfig.getSecret(),webConfig.getBucket(),path);
                logger.info("OSS UPLOAD SUCCESS is {}"+result);
            } catch (Exception e) {
                logger.info("Upload Pictures Exception error"+e.getMessage());
                return ResultUtil.error();
            }
            logger.info("resourceURL:"+webConfig.getDnsurl()+path+fileName);
            return ResultUtil.success(webConfig.getDnsurl()+path+fileName,trueName);
        }else {
            return ResultUtil.error();
        }
    }
    public String generateFileName(String path,String fileName) throws Exception {
        fileName = path+"_"+fileName;
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
        String formattedDate = formatter.format(date);
        String fileExt = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        return MD5.md5(fileName).substring(0, 8) + formattedDate + fileExt;
    }
    /*private File multipartToFile(MultipartFile multfile) throws IOException {
        CommonsMultipartFile cf = (CommonsMultipartFile)multfile;
        //这个myfile是MultipartFile的
        DiskFileItem fi = (DiskFileItem) cf.getFileItem();
        File file = fi.getStoreLocation();
        if(file.length() < CommonConstants.MIN_FILE_SIZE){
            File tmpFile = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") +
                    file.getName());
            multfile.transferTo(tmpFile);
            return tmpFile;
        }
        return file;
    }*/
}
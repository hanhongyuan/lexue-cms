package com.lexue.sso.web.freemarker;

import com.lexue.base.domain.User;
import com.lexue.sso.web.feignclient.MenuFeignClient;
import com.lexue.sso.web.feignclient.SsoFeignClient;
import com.lexue.sso.web.service.SsoUserService;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 页面dom层权限验证
 */
@Component
public class FreemarkerValidtPermission implements TemplateDirectiveModel {

    @Autowired
    private SsoUserService ssoUserService;
    @Autowired
    SsoFeignClient ssoFeignClient;

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        //获取当前登录用户
        User user = ssoUserService.getUser();
        //初始化状态为FALSE，如果验证通过则状态修改成TRUE
        templateModels[0] = TemplateBooleanModel.FALSE;
        //获取页面传递过来的permission参数
        TemplateScalarModel permission = (TemplateScalarModel) map.get("permission");
        //判断当前登录用户是否存在
        if(user != null){
                //通过用户ID获取用户的权限标识列表
                List<String> permissions = ssoFeignClient.getPermissionByCache(user.getId()).getData();
                //判断是否包含权限标识
                if(permissions.contains(permission.getAsString())){
                    //包含权限标识返回TRUE
                    templateModels[0] = TemplateBooleanModel.TRUE;
                }
        }
        templateDirectiveBody.render(environment.getOut());
    }
}

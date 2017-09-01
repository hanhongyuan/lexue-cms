<div style="margin: 15px;">
	<form class="layui-form" id="userForm">
		<div class="layui-form-item">
			<input type="hidden" name="id" value="${(user.id)!}"/>
			<input type="hidden" name="pageIndex" value=""/>
			<label class="layui-form-label">登录账号</label>
			<div class="layui-input-block">
				<input type="text" name="loginName" value="${(user.loginName)!}"  lay-verify="required|loginName" placeholder="请输入" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">登录密码</label>
			<div class="layui-input-inline">
				<input type="password" name="password" value=""  placeholder="请输入密码" autocomplete="off" class="layui-input">
			</div>
			<div class="layui-form-mid layui-word-aux">不填默认为123456</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">用户名称</label>
			<div class="layui-input-block">
				<input type="text" name="name" required lay-verify="required" value="${(user.name)!}" placeholder="请输入" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">复选框</label>
			<div class="layui-input-block">
				<#if roles??>
					<#list roles as role>
						<input name="roleIds" title="${role.name}" lay-verify="roles" lay-skin="primary" value="${role.id}" type="checkbox">
					</#list>
				</#if>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">是否启用</label>
			<div class="layui-input-block">
				<input type="radio" name="loginFlag" value="1" title="启用" checked="" >
				<input type="radio" name="loginFlag" value="2" title="禁用" ${((user.loginFlag)?? && user.loginFlag=="2")?string("checked","")}>
			</div>
		</div>
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">备注</label>
			<div class="layui-input-block">
				<textarea name="remarks" placeholder="请输入内容" class="layui-textarea">${(user.remarks)!}</textarea>
			</div>
		</div>
		<button lay-filter="edit" lay-submit style="display: none;"></button>
	</form>
	<script>
        layui.use(['form','laypage','layer'],function() {
            var form = layui.form();
            var layer = layui.layer;
            var $ = layui.jquery;
            form.verify({
                loginName: function(value,item){
                    var login= value;
                    var datas=false;
                    $.ajax({
                        url:'/user/checkLoginName',
                        data:{loginName:login},
                        type:'POST',
                        success:function (data) {
                            if(data.code==500){
                                datas=true;
                            }
                        }
                    });
                    if(datas){
                        console.log("该登陆账号已经存在");
                        return "该登陆账号已经存在";
                    }
                }
            });

        });
	</script>
</div>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>用户详情</title>
		<link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/plugins/layui/css/layui.css" />
		<link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/global.css" />
		<link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
	</head>
	<body class="anim-fadeInUp">
		<div style="margin: 15px;">
			<form id="saveForm" class="layui-form layui-form-pane mt10" >
				<div class="layui-form-item">
					<label class="layui-form-label">用户账号</label>
					<div class="layui-input-block">
						<input type="hidden" name="id" value="${(user.id)!}"/>
						<input type="text" name="loginName" value="${(user.loginName)!}" class="layui-input" >
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">用户姓名</label>
					<div class="layui-input-block">
						<input type="text" name="name" value="${(user.name)!}" class="layui-input" >
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">手机号码</label>
					<div class="layui-input-block">
						<input type="text" name="mobile" value="${(user.mobile)!}" class="layui-input" >
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">邮箱</label>
					<div class="layui-input-block">
						<input type="text" name="email" value="${(user.email)!}" class="layui-input" >
					</div>
				</div>
				<button class="layui-btn fr" lay-submit="" style="float:right;"  lay-filter="demo2">确认修改</button>
			</form>
		</div>
		<script type="text/javascript" src="${springMacroRequestContext.contextPath}/static/js/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="${springMacroRequestContext.contextPath}/static/plugins/layui/layui.js"></script>
		<script>
            layui.use(['form','laypage','layer','laydate'],function(){
                var form = layui.form();
                form.on('submit(demo2)',function(data){
                    $.ajax({
                        url:'/user/save',
                        data:$("#saveForm").serialize(),
                        type:'POST',
                        success:function (data) {
                            if(data=='success'){
                                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                                parent.layer.close(index); //再执行关闭 layer.msg("保存成功");
                            }
                        }
                    });

                });
            });
		</script>
	</body>
</html>

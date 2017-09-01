<#import "../macro/common.ftl" as common>
<div style="margin: 15px;">
	<form class="layui-form" id="roleForm">
		<div class="layui-form-item">
			<input type="hidden" name="id" value="${(role.id)!}"/>
			<input type="hidden" value="${(menuIds)!}" name="menuIds" />
			<label class="layui-form-label">角色名称</label>
			<div class="layui-input-block">
				<input type="text" name="name" value="${(role.name)!}" required lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">是否启用</label>
			<div class="layui-input-block">
				<input type="radio" name="useable" value="1" title="启用" checked="">
				<input type="radio" name="useable" value="2" title="禁用" ${((role.useable)?? && role.useable=="2")?string("checked","")}>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">角色权限</label>
			<div class="layui-input-block">
				<ul id="tree" class="ztree"></ul>
			</div>
		</div>
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">备注</label>
			<div class="layui-input-block">
				<textarea name="remarks" placeholder="请输入内容" class="layui-textarea">${(role.remarks)!}</textarea>
			</div>
		</div>
		<button lay-filter="edit" lay-submit style="display: none;"></button>
	</form>
	<script type="text/javascript" src="${springMacroRequestContext.contextPath}/static/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${springMacroRequestContext.contextPath}/static/js/jquery.ztree.all.min.js"></script>
	<script>
        $(function(){
            var setting = {check: {enable: true},data: {simpleData: {enable: true}}};
            $.ajax({
                url:"/menu/getZTree?roleId="+$("input[name='id']").val(),
                type:"POST",
                async: false,
                dataType:"json",
                success:function(d){
                    if(d.code == 200){
                        $.fn.zTree.init($("#tree"), setting,d.data);
                    }else{
                        layer.msg("对不起，访问不成功！错误编码：" + d.code);
                    }
                }
            });
        })
	</script>
</div>
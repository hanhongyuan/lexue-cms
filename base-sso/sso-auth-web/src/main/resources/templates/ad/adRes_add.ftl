<#import "../macro/common.ftl" as common>
<div style="margin: 15px;">
	<form class="layui-form" id="dictForm">
		<div class="layui-form-item">
			<input type="hidden" name="id" value="${(adRes.id)!}"/>
			<label class="layui-form-label">广告名称</label>
			<div class="layui-input-block">
				<input type="text" name="title" value="${(adRes.title)!}" required lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">广告链接</label>
			<div class="layui-input-inline">
				<input type="text" name="forwardURL" lay-verify="required|url" value="${(adRes.forwardURL)!}"  placeholder="请输入标题名称" autocomplete="off" class="layui-input">
			</div>
		</div>
		<#--<div class="layui-form-item">
			<label class="layui-form-label">广告时间</label>
			<div class="layui-input-block">
				<input type="text" name="value" required lay-verify="required" value="${(adRes.value)!}" placeholder="请输入值" autocomplete="off" class="layui-input">
			</div>
		</div>-->
        <div class="layui-form-item">
            <label class="layui-form-label">上传视频</label>
            <div class="layui-input-block">
                <input type="hidden"  lay-verify="resourceURL" name="resourceURL" value="${(adRes.resourceURL)!}"/>
                <input type="file" name="file"  class="layui-upload-file">
            </div>
            <div id="codePath" style="margin-left: 300px;margin-top: -35px;color: red;" class="layui-form-mid layui-word-aux"></div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">是否启用</label>
            <div class="layui-input-block">
				<@common.status title="status" value="${(adRes.status)!}" />
                <#--<input type="radio" name="stat" value="2" title="启用" checked="" >
                <input type="radio" name="stat" value="1" title="禁用" ${((adRes.status)?? && adRes.status==1)?string("checked","")}>-->
            </div>
        </div>
		<button lay-filter="edit" lay-submit style="display: none;"></button>
	</form>
</div>
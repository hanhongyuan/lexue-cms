<#import "../macro/common.ftl" as common>
<div style="margin: 15px;">
	<form class="layui-form" id="groupForm">
		<div class="layui-form-item">
			<input type="hidden" name="id" value="${(group.id)!}"/>
			<label class="layui-form-label">群组名称</label>
			<div class="layui-input-block">
				<input type="text" name="groupName" value="${(group.groupName)!}" required lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
			</div>
		</div>
        <div class="layui-form-item">
            <label class="layui-form-label">H5链接</label>
            <div class="layui-input-block">
                <input type="text" name="h5url" required lay-verify="required" value="${(group.h5url)!}" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">群背景图</label>
            <div class="layui-input-block">
                <input type="file" name="file" class="layui-upload-file">
                <input type="hidden" lay-verify="backgroundImage"  name="backgroundImage" value="${(group.backgroundImage)!}"/>
            </div>
            <div id="codePath" style="margin-left: 300px;margin-top: -35px;" class="layui-form-mid layui-word-aux"></div>
        </div>
		<button lay-filter="edit" lay-submit style="display: none;"></button>
	</form>
</div>
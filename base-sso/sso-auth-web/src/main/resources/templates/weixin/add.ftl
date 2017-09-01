<#import "../macro/common.ftl" as common>
<div style="margin: 15px;">
	<form class="layui-form" id="wxForm">
		<div class="layui-form-item">
			<input type="hidden" name="id" value="${(wxGroup.id)!}"/>

			<label class="layui-form-label">群名称</label>
			<div class="layui-input-block">
				<input type="text" name="name" value="${(wxGroup.name)!}" required lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
			</div>
		</div>
        <div class="layui-form-item">
            <label class="layui-form-label">群人数</label>
            <div class="layui-input-block">
                <input type="text" name="groupNum" required lay-verify="required" value="${(wxGroup.groupNum)!}" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
		<div class="layui-form-item">
            <label class="layui-form-label">群二维码</label>
            <div class="layui-input-block">
                <input type="hidden"  lay-verify="qrCodePath" name="qrCodePath" value="${(wxGroup.qrCodePath)!}"/>
                <input type="file" name="file"  class="layui-upload-file">
            </div>
            <div id="codePath" style="margin-left: 300px;margin-top: -35px;" class="layui-form-mid layui-word-aux"></div>
        </div>
		<div class="layui-form-item">
            <label class="layui-form-label">所在群组</label>
            <div class="layui-input-block">
                <select name="groupId" lay-verify="required">
					<#if groupList??>
						<#list groupList as group>
							<option value="${group.id}" ${((wxGroup.groupId)?? && wxGroup.groupId==group.id)?string("selected","")}>${group.groupName}</option>
						</#list>
						<#else >
						<option value="">无群组信息</option>
					</#if>
                </select>
            </div>
        </div>
		<button lay-filter="edit" lay-submit style="display: none;"></button>
	</form>
</div>
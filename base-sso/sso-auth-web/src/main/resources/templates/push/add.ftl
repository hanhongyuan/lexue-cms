<#import "../macro/common.ftl" as common>
<div style="margin: 15px;">
	<form class="layui-form" id="userForm">
		<div class="layui-form-item">
			<label class="layui-form-label">消息标题</label>
			<div class="layui-input-block">
				<input type="text" name="title"  lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">透传类型</label>
			<div class="layui-input-inline">
                <select name="transparent" lay-verify="required" lay-filter="transparent">
                   <#-- <option value="1" >无透传</option>
                    <option value="2" >视频透传</option>-->
                    <@common.mdictOptions title='transparent' value=''/>
                </select>
			</div>
		</div>
        <div class="layui-form-item hide">
            <label class="layui-form-label">透传视频</label>
            <div class="layui-input-inline">
                <select id="videoId" name="videoId" lay-verify="required">
                    <#if videoList??>
                        <#list videoList as videos>
                            <option value="${videos.id}">${videos.title}</option>
                        </#list>
                    </#if>
                    <option value="1" >无透传</option>
                </select>
            </div>
        </div>
		<div class="layui-form-item">
			<label class="layui-form-label">推送时间</label>
			<div class="layui-input-inline">
				<input type="text" name="startTime" required lay-verify="required" onclick="layui.laydate({elem: this,type: 'datetime',format:'YYYY-MM-DD hh:mm:ss'})"  value="${(pushMessage.cronExpression)!}" placeholder="请输入" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">过期时间</label>
			<div class="layui-input-inline">
				<input type="text" name="endTime" required lay-verify="required" onclick="layui.laydate({elem: this,type: 'datetime',format:'YYYY-MM-DD hh:mm:ss'})"  value="${(pushMessage.className)!}" placeholder="请输入" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">是否离线保存信息</label>
			<div class="layui-input-block">
                <input type="checkbox" name="sOffline" lay-verify="required"  lay-skin="switch" lay-text="是|否">
			</div>
		</div>
        <div class="layui-form-item">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-inline">
               <#-- <input type="text" name="status" lay-verify="required"  placeholder="请输入" autocomplete="off" class="layui-input">-->
                <@common.status title="status" value="" />
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">推送客户端</label>
            <div class="layui-input-inline">
                <select name="bType" lay-verify="required" >
                <@common.mdictOptions title='BusinessType' value=''/>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">优先级</label>
            <div class="layui-input-inline">
                <input type="text" name="priority" lay-verify="required"   placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">推送平台</label>
            <div class="layui-input-inline">
                <select name="targetTypes" lay-verify="required" lay-filter="targetType">
                <@common.mdictOptions title='TargetType' value=''/>
                </select>
            </div>
        </div>
        <div class="layui-form-item hide ">
            <label class="layui-form-label">平台选择</label>
            <div class="layui-input-inline">
                <select name="plantforms" id="target1">
                <@common.mdictOptions title='plantforms' value=''/>
                </select>
            </div>
        </div>
		<div class="layui-form-item hide">
            <label class="layui-form-label">用户ID</label>
            <div class="layui-input-inline">
                <input type="text" id="userId" name="user"  placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
		<div class="layui-form-item hide">
            <label class="layui-form-label">标签</label>
            <div class="layui-input-inline">
                <input type="text" id="tag" name="tag"  placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">消息内容</label>
            <div class="layui-input-block">
                <textarea name="content" placeholder="请输入内容" lay-verify="required" class="layui-textarea"></textarea>
            </div>
        </div>
		<button lay-filter="edit" lay-submit style="display: none;"></button>
	</form>
</div>
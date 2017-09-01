<#import "../macro/common.ftl" as common>
<div style="margin: 15px;">
	<form class="layui-form" id="dictForm">
		<div class="layui-form-item">
			<input type="hidden" name="id" value="${(adBoxs.id)!}"/>
			<label class="layui-form-label">广告位标题</label>
			<div class="layui-input-block">
				<input type="text" name="title" value="${(adBoxs.title)!}" required lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">显示宽度</label>
			<div class="layui-input-inline">
				<input type="text" name="viewWidth" value="${(adBoxs.viewWidth)!}"  placeholder="宽度<=0不限制显示宽度" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">显示高度</label>
			<div class="layui-input-inline">
				<input type="text" name="viewHeight" value="${(adBoxs.viewHeight)!}"  placeholder="高度<=0不限制显示宽度" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">宽高单位</label>
			<div class="layui-input-inline">
                <select name="company" lay-verify="required">
					<@common.mdictOptions title="company" value="${(adBoxs.company)!}"/>
                    <#--<option value="1" ${((adBoxs.company)?? && adBoxs.company==1)?string("selected","")}>像素</option>
                    <option value="2" ${((adBoxs.company)?? && adBoxs.company==2)?string("selected","")}>磅</option>
                    <option value="3" ${((adBoxs.company)?? && adBoxs.company==3)?string("selected","")}>百分比</option>-->
                </select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">显示时长</label>
			<div class="layui-input-inline">
				<input type="text" name="viewTime" value="${(adBoxs.viewTime)!}"  placeholder="单位秒<=0表示永久显示" autocomplete="off" class="layui-input">
			</div>
		</div>
        <div class="layui-form-item">
            <label class="layui-form-label">是否启用</label>
            <div class="layui-input-block">
				<@common.status title="status" value="${(adBoxs.status)!}" />
               <#-- <input type="radio" name="status" value="2" title="启用" checked="" >
                <input type="radio" name="status" value="1" title="禁用" ${((adBoxs.status)?? && adBoxs.status==1)?string("checked","")}>-->
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">广告描述</label>
            <div class="layui-input-block">
                <textarea name="note" placeholder="请输入内容" class="layui-textarea">${(adBoxs.note)!}</textarea>
            </div>
        </div>
		<button lay-filter="edit" lay-submit style="display: none;"></button>
	</form>
</div>
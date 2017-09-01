<#import "../macro/common.ftl" as common>
<div style="margin: 15px;">
	<form class="layui-form" id="dictForm">
		<div class="layui-form-item">
			<input type="hidden" name="id" value="${(adFrame.id)!}"/>
			<label class="layui-form-label">广告模版帧标题</label>
			<div class="layui-input-block">
				<input type="text" name="title" value="${(adFrame.title)!}" required lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">帧条目容量</label>
			<div class="layui-input-inline">
				<input type="text" name="itemCapacity" value="${(adFrame.itemCapacity)!}"  placeholder="" autocomplete="off" class="layui-input">
			</div>
            <div class="layui-form-mid layui-word-aux">仅当资源为文本时有效0表示不限制条目数量</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">帧优先级</label>
			<div class="layui-input-inline">
				<input type="text" name="priority" value="${(adFrame.priority)!}"  placeholder="" autocomplete="off" class="layui-input">
			</div>
            <div class="layui-form-mid layui-word-aux">默认1</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">帧条目类型</label>
			<div class="layui-input-inline">
                <select name="frameType" lay-verify="required">
				<@common.mdictOptions title='frameType' value='${(adFrame.frameType)!}'/>
                    <#--<option value="1" ${((adFrame.frameType)?? && adFrame.frameType==1)?string("selected","")}>文字竖排</option>
                    <option value="2" ${((adFrame.frameType)?? && adFrame.frameType==2)?string("selected","")}>图片</option>
                    <option value="3" ${((adFrame.frameType)?? && adFrame.frameType==3)?string("selected","")}>流媒体</option>
                    <option value="4" ${((adFrame.frameType)?? && adFrame.frameType==4)?string("selected","")}>动画</option>
                    <option value="5" ${((adFrame.frameType)?? && adFrame.frameType==5)?string("selected","")}>文字横排</option>
                    <option value="10" ${((adFrame.frameType)?? && adFrame.frameType==10)?string("selected","")}>多图帧</option>-->
                </select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">帧选取类型</label>
			<div class="layui-input-inline">
                <select name="itemPickType" lay-verify="required">
				<@common.mdictOptions title='itemPickType' value='${(adFrame.itemPickType)!}'/>
                    <#--<option value="1" ${((adFrame.itemPickType)?? && adFrame.itemPickType==1)?string("selected","")}>全部</option>
                    <option value="2" ${((adFrame.itemPickType)?? && adFrame.itemPickType==2)?string("selected","")}>随机</option>
                    <option value="3" ${((adFrame.itemPickType)?? && adFrame.itemPickType==3)?string("selected","")}>优先级</option>
                    <option value="4" ${((adFrame.itemPickType)?? && adFrame.itemPickType==4)?string("selected","")}>特殊筛选</option>-->
                </select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">单元格布局X轴数量</label>
			<div class="layui-input-inline">
				<input type="text" name="cellLayoutXCount" required lay-verify="required" value="${(adFrame.cellLayoutXCount)!}"  placeholder="" autocomplete="off" class="layui-input">
			</div>
            <div class="layui-form-mid layui-word-aux">单元格X的数量</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">单元格布局Y轴数量</label>
			<div class="layui-input-inline">
				<input type="text" name="cellLayoutYCount" required lay-verify="required" value="${(adFrame.cellLayoutYCount)!}"  placeholder="" autocomplete="off" class="layui-input">
			</div>
            <div class="layui-form-mid layui-word-aux">单元格Y的数量</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">条目滚动时间</label>
			<div class="layui-input-inline">
				<input type="text" name="itemScrollTime"  required lay-verify="required" value="${(adFrame.itemScrollTime)!}"  placeholder="" autocomplete="off" class="layui-input">
			</div>
            <div class="layui-form-mid layui-word-aux">单位秒,仅当资源为文本时有效</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">单元格布局X轴起始</label>
			<div class="layui-input-inline">
				<input type="text" name="cellLayoutXStart" required lay-verify="required" value="${(adFrame.cellLayoutXStart)!}"  placeholder="" autocomplete="off" class="layui-input">
			</div>
            <div class="layui-form-mid layui-word-aux">单元格从X轴起始位置</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">单元格布局X轴结束</label>
			<div class="layui-input-inline">
				<input type="text" name="cellLayoutXEnd" required lay-verify="required" value="${(adFrame.cellLayoutXEnd)!}"  placeholder="" autocomplete="off" class="layui-input">
			</div>
            <div class="layui-form-mid layui-word-aux">单元格从X轴结束位置</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">单元格布局Y轴起始</label>
			<div class="layui-input-inline">
				<input type="text" name="cellLayoutYStart" required lay-verify="required" value="${(adFrame.cellLayoutYStart)!}"  placeholder="" autocomplete="off" class="layui-input">
			</div>
            <div class="layui-form-mid layui-word-aux">单元格从Y轴起始位置</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">单元格布局Y轴结束</label>
			<div class="layui-input-inline">
				<input type="text" name="cellLayoutYEnd" required lay-verify="required" value="${(adFrame.cellLayoutYEnd)!}"  placeholder="" autocomplete="off" class="layui-input">
			</div>
            <div class="layui-form-mid layui-word-aux">单元格从Y轴结束位置</div>
		</div>
        <div class="layui-form-item">
            <label class="layui-form-label">开始时间</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" id="startTime" lay-verify="startTime" name="startTime" placeholder="yyyy-MM-dd HH:mm:ss" onclick="layui.laydate({elem: this,type: 'datetime',format:'YYYY-MM-DD hh:mm:ss'})" value="${(adFrame.startTime)!}" >
            </div>
        </div>
		<div class="layui-form-item">
            <label class="layui-form-label">结束时间</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" id="endTime" lay-verify="endTime" name="endTime" placeholder="yyyy-MM-dd HH:mm:ss" onclick="layui.laydate({elem: this,type: 'datetime',format:'YYYY-MM-DD hh:mm:ss'})" value="${(adFrame.endTime)!}" >
            </div>
        </div>
        <#--<div class="layui-form-item">
            <label class="layui-form-label">是否启用</label>
            <div class="layui-input-block">
				<@common.status title="status" value="${(adFrame.status)!}" />
               &lt;#&ndash; <input type="radio" name="status" value="2" title="启用" checked="" >
                <input type="radio" name="status" value="1" title="禁用" ${((adFrame.status)?? && adFrame.status==1)?string("checked","")}>&ndash;&gt;
            </div>
        </div>-->
        <div class="layui-form-item">
            <label class="layui-form-label">广告模板归属</label>
            <div class="layui-input-inline">
                <select name="tplId" lay-verify="required">
				<#if adTpl??>
					<#list adTpl as tpl>
                        <option value="${tpl.templateId}" ${((adFrame.tplId)?? && adFrame.tplId==tpl.templateId)?string("selected","")}>${tpl.title}</option>
					</#list>
				</#if>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">广告资源归属</label>
            <div class="layui-input-inline">
                <select name="adResId" lay-verify="required">
				<#if adRes??>
					<#list adRes as res>
                        <option value="${res.resourceId}" ${((adFrame.adResId)?? && adFrame.adResId==res.resourceId)?string("selected","")}>${res.title}</option>
					</#list>
				</#if>
                </select>
            </div>
        </div>
		<button lay-filter="edit" lay-submit style="display: none;"></button>
	</form>
</div>
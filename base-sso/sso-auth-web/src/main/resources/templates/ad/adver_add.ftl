<div style="margin: 15px;">
	<form class="layui-form" id="dictForm">
		<div class="layui-form-item">
			<input type="hidden" name="id" value="${(advertisement.id)!}"/>
			<label class="layui-form-label">广告投放计划名称</label>
			<div class="layui-input-block">
				<input type="text" name="type" value="${(advertisement.title)!}" required lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">广告位</label>
			<div class="layui-input-inline">
				<input type="text" name="description" value="${(advertisement.description)!}"  placeholder="请输入标题名称" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">广告模板</label>
			<div class="layui-input-block">
				<input type="text" name="value" required lay-verify="required" value="${(advertisement.value)!}" placeholder="请输入值" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">广告帧信息</label>
			<div class="layui-input-block">
				<input type="text" name="label" required lay-verify="required" value="${(advertisement.label)!}" placeholder="请输入值描述" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">广告资源名称</label>
			<div class="layui-input-block">
				<input type="text" name="sort" required lay-verify="required" value="${(advertisement.sort)!}" placeholder="请输入排序" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">上线时间</label>
			<div class="layui-input-block">
				<input type="text" name="sort" required lay-verify="required" value="${(advertisement.startTime)!}" placeholder="请输入排序" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">下线时间</label>
			<div class="layui-input-block">
				<input type="text" name="sort" required lay-verify="required" value="${(advertisement.endTime)!}" placeholder="请输入排序" autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">状态</label>
			<div class="layui-input-block">
				<textarea name="remarks" placeholder="请输入内容" class="layui-textarea">${(advertisement.status)!}</textarea>
			</div>
		</div>
		<button lay-filter="edit" lay-submit style="display: none;"></button>
	</form>
</div>
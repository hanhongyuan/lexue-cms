<div style="margin: 15px;">
	<form class="layui-form" id="dictForm">
		<div class="layui-form-item">
			<input type="hidden" name="id" value="${(dict.id)!}"/>
			<label class="layui-form-label">标题</label>
			<div class="layui-input-block">
				<input type="text" name="type" value="${(dict.type)!}" required lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">标题名称</label>
			<div class="layui-input-inline">
				<input type="text" name="description" value="${(dict.description)!}"  placeholder="请输入标题名称" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">值</label>
			<div class="layui-input-block">
				<input type="text" name="value" required lay-verify="required" value="${(dict.value)!}" placeholder="请输入值" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">值描述</label>
			<div class="layui-input-block">
				<input type="text" name="label" required lay-verify="required" value="${(dict.label)!}" placeholder="请输入值描述" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">排序</label>
			<div class="layui-input-block">
				<input type="text" name="sort" required lay-verify="required" value="${(dict.sort)!}" placeholder="请输入排序" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">备注</label>
			<div class="layui-input-block">
				<textarea name="remarks" placeholder="请输入内容" class="layui-textarea">${(dict.remarks)!}</textarea>
			</div>
		</div>
		<button lay-filter="edit" lay-submit style="display: none;"></button>
	</form>
</div>
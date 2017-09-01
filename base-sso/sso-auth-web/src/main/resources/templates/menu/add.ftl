<div style="margin: 15px;">
	<form class="layui-form" id="menuForm">
		<div class="layui-form-item">
			<label class="layui-form-label">上级菜单</label>
			<div class="layui-input-block">
				<input type="text" id="pname" value="${(menu.pname)!}" lay-verify="pname" onclick="layui.showTree();" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">菜单名称</label>
			<div class="layui-input-block">
				<input type="hidden" name="id" id="id" value="${(menu.id)!}"/>
				<input type="hidden" name="parentId" id="parentId" value="${(menu.parentId)!}"/>
				<input type="text" name="name" value="${(menu.name)!}" required lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">访问地址</label>
			<div class="layui-input-block">
				<input type="text" name="href" value="${(menu.href)!}" required lay-verify="required" placeholder="请输入访问地址" autocomplete="off" class="layui-input">
			</div>
			<div class="layui-form-mid layui-word-aux"></div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">权限标识</label>
			<div class="layui-input-block">
				<input type="text" name="permission" required lay-verify="required" value="${(menu.permission)!}" placeholder="请输入" autocomplete="off" class="layui-input">
			</div>
		</div>
        <div class="layui-form-item">
            <label class="layui-form-label">排序</label>
            <div class="layui-input-block">
                <input type="text" name="sort" required lay-verify="required" value="${(menu.sort)!}" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
		<!--<div class="layui-form-item">
			<label class="layui-form-label">选择框</label>
			<div class="layui-input-block">
				<select name="city" lay-verify="required">
					<option value=""></option>
					<option value="0">北京</option>
					<option value="1">上海</option>
					<option value="2">广州</option>
					<option value="3">深圳</option>
					<option value="4">杭州</option>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">复选框</label>
			<div class="layui-input-block">
				<input type="checkbox" name="like[write]" title="写作">
				<input type="checkbox" name="like[read]" title="阅读" checked>
				<input type="checkbox" name="like[dai]" title="发呆">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">是否启用</label>
			<div class="layui-input-block">
				<input type="checkbox" name="switch" lay-skin="switch">
			</div>
		</div>-->
		<div class="layui-form-item">
			<label class="layui-form-label">是否启用</label>
			<div class="layui-input-block">
				<input type="radio" name="isShow" value="1" title="启用" checked="">
				<input type="radio" name="isShow" value="2" title="禁用" ${((menu.isShow)?? && menu.isShow=="2")?string("checked","")}>
			</div>
		</div>
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">备注</label>
			<div class="layui-input-block">
				<textarea name="remarks" placeholder="请输入内容" class="layui-textarea">${(menu.remarks)!}</textarea>
			</div>
		</div>
		<button lay-filter="edit" lay-submit style="display: none;"></button>
	</form>
</div>
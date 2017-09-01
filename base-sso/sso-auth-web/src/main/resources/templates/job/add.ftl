<div style="margin: 15px;">
	<form class="layui-form" id="userForm">
		<div class="layui-form-item">
			<input type="hidden" name="id" value="${(job.id)!}"/>
			<label class="layui-form-label">任务组</label>
			<div class="layui-input-block">
				<input type="text" name="jobGroup" value="${(job.jobGroup)!}" required lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">任务名</label>
			<div class="layui-input-block">
                <input type="text" name="jobName"  lay-verify="required" value="${(job.jobName)!}" placeholder="请输入" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">cron表达式</label>
			<div class="layui-input-block">
				<input type="text" name="cronExpression" required lay-verify="required" value="${(job.cronExpression)!}" placeholder="请输入" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">任务执行类名称</label>
			<div class="layui-input-block">
				<input type="text" name="className" required lay-verify="required" value="${(job.className)!}" placeholder="请输入" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">任务执行类方法</label>
			<div class="layui-input-block">
				<input type="text" name="methodName" required lay-verify="required" value="${(job.methodName)!}" placeholder="请输入" autocomplete="off" class="layui-input">
			</div>
		</div>
        <div class="layui-form-item">
            <label class="layui-form-label">方法参数</label>
            <div class="layui-input-block">
                <input type="text" name="params"  value="${(job.params)!}" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">备注</label>
			<div class="layui-input-block">
				<textarea name="description" placeholder="请输入内容" class="layui-textarea">${(job.description)!}</textarea>
			</div>
		</div>
		<button lay-filter="edit" lay-submit style="display: none;"></button>
	</form>
</div>
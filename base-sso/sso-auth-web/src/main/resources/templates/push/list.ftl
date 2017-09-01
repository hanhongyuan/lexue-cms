<#assign base=request.contextPath />
<!DOCTYPE html>
<#import "../macro/common.ftl" as common>
<html>

	<head>
		<meta charset="UTF-8">
		<title>JOB</title>
		<link rel="stylesheet" href="${base}/static/plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="${base}/static/css/global.css" media="all">
		<link rel="stylesheet" href="${base}/static/plugins/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="${base}/static/css/table.css" />
	</head>

	<body>
		<div class="admin-main">

			<blockquote class="layui-elem-quote" style="border-left:0px solid #009688;">
				<@common.permission per="sys:job:add">
                    <a href="javascript:;" class="layui-btn layui-btn-small" id="add">
                        <i class="layui-icon">&#xe608;</i> 添加信息
                    </a>
				</@common.permission>
			</blockquote>
			<fieldset class="layui-elem-field">
				<legend>数据列表</legend>
				<div class="layui-field-box layui-form">
					<table class="layui-table admin-table">
						<thead>
							<tr>
								<th style="width: 30px;"><input type="checkbox" lay-filter="allselector" lay-skin="primary"></th>
								<th>ID</th>
								<th>标题</th>
								<th>消息内容</th>
								<th>透传类型</th>
								<th>透传内容</th>
								<th>推送时间</th>
								<th>过期时间</th>
								<th>推送客户端</th>
								<th>推送平台</th>
								<th>推送目标</th>
								<th>推送状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="content">
						</tbody>
					</table>
				</div>
			</fieldset>
			<div class="admin-table-page">
				<div id="paged" class="page">
				</div>
			</div>
		</div>
		<!--模板-->
		<script type="text/html" id="tpl">
			{{# layui.each(d.list, function(index, item){ }}
			<tr>
				<td><input type="checkbox" lay-skin="primary" data-id="{{ item.id }}"></td>
				<td>{{ item.ruleId || '' }}</td>
				<td>{{ item.title || '' }}</td>
				<td>{{ item.content || '' }}</td>
				<#--<td><@common.mdictInfo title="transparent" value='{{ item.transparent }}'></@common.mdictInfo></td>-->
                <#--<td>
                    {{#
                    if(item.transparent == 1){ }}
                    无透传
                    {{# } else if(item.transparent == 2){ }}
                    视频透传
                    {{# }else { }}
                    无
                    {{# };
                    }}
                </td>-->
				<td>{{ item.tp || '' }}</td>
				<td>{{ item.transmission || '' }}</td>
				<td>{{ item.startTime || '' }}</td>
				<td>{{ item.endTime || '' }}</td>
                <#--<td>
                    {{#
                    if(item.businessType == 'JUNIOR_TEACH'){ }}
                    中考
                    {{# } else if(item.businessType == 'JUNIOR_VOLUNTEER'){ }}
                    中考志愿
                    {{# } else if(item.businessType == 'SENIOR_TEACH'){ }}
                    高考
                    {{# } else if(item.businessType == 'SENIOR_VOLUNTEER'){ }}
                    高考志愿
                    {{# } else if(item.businessType == 'TEENS_ENGLISH_GAME'){ }}
                    少儿英语游戏
                    {{# }else { }}
                    无
                    {{# };
                    }}
                </td>-->
                <td>{{ item.bt || '' }}</td>
                <td>{{ item.tt || '' }}</td>
                <#--<td>
                    {{#
                    if(item.targetType == 'ALL'){ }}
                    全部
                    {{# } else if(item.targetType == 'PLANTFORM'){ }}
                    平台
					{{# } else if(item.targetType == 'USERID'){ }}
                    用户ID
                    {{# } else if(item.targetType == 'TAG'){ }}
                    标签
                    {{# }else { }}
                    无
                    {{# };
                    }}
                </td>-->
                <td>{{ item.value || '' }}</td>
                <td>{{ item.pushStatus || '' }}</td>
				<td>
                    {{#if(item.pushId == null){ }}
                    <a href="javascript:layui.resumePush('{{ item.id }}');" data-id="{{ item.id }}" data-opt="resume" class="layui-btn layui-btn-mini layui-btn-normal"><i class="layui-icon">&#xe609;</i>重新推送</a>
                    {{# } else { }}
                    <a href="javascript:layui.updateStatus('{{ item.id }}');" data-id="{{ item.id }}" data-opt="edit" class="layui-btn layui-btn-mini"><i class="layui-icon">&#x1002;</i>更新状态</a>
                    {{# };}}
				</td>
			</tr>
			{{# }); }}
			{{#  if(d.list.length === 0){ }}
			无数据
			{{#  } }}
		</script>
		<script type="text/javascript" src="${base}/static/plugins/layui/layui.js"></script>
		<script>
			layui.config({
				base: '${base}/static/js/'
			});

			layui.use(['paging', 'laydate','form'], function() {
				var $ = layui.jquery,
					paging = layui.paging(),
					layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
					layer = layui.layer, //获取当前窗口的layer对象
					form = layui.form();

                paging.init({
                    openWait: true,
                    url: '/push/data?v=' + new Date().getTime(), //地址
					elem: '#content', //内容容器
					params: { //发送到服务端的参数
					},
					type: 'POST',
					tempElem: '#tpl', //模块容器
					pageConfig: { //分页参数配置

						elem: '#paged', //分页容器
						pageSize: 15 //分页大小
					},
					success: function() { //渲染成功的回调
						layer.msg('数据加载完成');
					},
					fail: function(msg) { //获取数据失败的回调
                        layer.msg('获取数据失败')
					},
					complate: function() { //完成的回调
						//alert('处理完成');
						var form=layui.form();
						form.render('checkbox');
						form.on('checkbox(allselector)', function(data) {
							var elem = data.elem;

							$('#content').children('tr').each(function() {
								var $that = $(this);
								//全选或反选
								$that.children('td').eq(0).children('input[type=checkbox]')[0].checked = elem.checked;
								form.render('checkbox');
							});
						});

						//绑定所有编辑按钮事件						
						$('#content').children('tr').each(function() {
							var $that = $(this);
							$that.children('td:last-child').children('a[data-opt=edit]').on('click', function() {
								//layer.msg($(this).data('id'));
                                $.get('/push/add', {id:$(this).data('id')}, function(form) {
                                    addBoxIndex = layer.open({
                                        type: 1,
                                        title: '修改用户',
                                        content: form,
                                        btn: ['保存', '取消'],
                                        shade: false,
                                        offset: ['100px', '20%'],
                                        area: ['800px', '600px'],
                                        maxmin: true,
										yes:function () {
                                            $('form.layui-form').find('button[lay-filter=edit]').click();
                                        },
                                        success: function(layero, index) {
                                            //弹出窗口成功后渲染表单
                                            var form = layui.form();
                                            form.render();
                                            form.on('submit(edit)', function(data) {
                                                $.ajax({
                                                    url:'/push/save',
                                                    data:$("#userForm").serialize(),
                                                    type:'POST',
                                                    success:function (data) {
                                                        if(data=='success'){
                                                            layer.close(index);
                                                            location.reload();
                                                        }
                                                    }
                                                });
                                            });
                                        },
                                        end: function() {
                                            addBoxIndex = -1;
                                        }
                                    });
								});
							});
						});
						//绑定删除按钮
                        $('#content').children('tr').each(function() {
                            var $that = $(this);
                            $that.children('td:last-child').children('a[data-opt=del]').on('click', function () {
                                console.log($(this).data('id'));
                                var id=$(this).data('id');
                                layer.confirm('确定删除吗?', {icon: 3, title:'提示'}, function(index){
                                    $.ajax({
                                        url:'/push/delete',
                                        data:{id:id},
                                        type:'POST',
                                        success:function (data) {
                                        }
                                    });
                                    layer.close(index);
                                    location.reload();
                                });
                            });
                        });
					},
				});
                layui.resumePush=function (id) {
					$.ajax({
                        url:'/push/updateStatus',
                        data:{id:id,status:1},
                        type:'POST',
                        success:function (data) {
                            if(data=='success'){
                                layer.msg("重新推送成功");
                                location.reload();
                            }
                        }
					});
                };
                layui.updateStatus=function (id) {
                    $.ajax({
                        url:'/push/updateStatus',
                        data:{id:id,status:2},
                        type:'POST',
                        success:function (data) {
                            if(data=='success'){
                                layer.msg("更新成功");
                                location.reload();
                            }
                        }
                    });
                };
				//获取所有选择的列
				$('#getSelected').on('click', function() {
					var names = '';
					$('#content').children('tr').each(function() {
						var $that = $(this);
						var $cbx = $that.children('td').eq(0).children('input[type=checkbox]')[0].checked;
						if($cbx) {
							var n = $that.children('td:last-child').children('a[data-opt=edit]').data('id');
							names += n + ',';
						}
					});
					layer.msg('你选择的名称有：' + names);
				});

				var addBoxIndex = -1;
				$('#add').on('click', function() {
					if(addBoxIndex !== -1)
						return;
					//本表单通过ajax加载 --以模板的形式，当然你也可以直接写在页面上读取
					$.get('/push/add', null, function(form) {
						addBoxIndex = layer.open({
							type: 1,
							title: '添加表单',
							content: form,
							btn: ['保存', '取消'],
							shade: false,
							offset: ['100px', '20%'],
							area: ['800px', '600px'],
							maxmin: true,
							yes: function(index) {
								//触发表单的提交事件
								$('form.layui-form').find('button[lay-filter=edit]').click();
							},
							full: function(elem) {
								var win = window.top === window.self ? window : parent.window;
								$(win).on('resize', function() {
									var $this = $(this);
									elem.width($this.width()).height($this.height()).css({
										top: 0,
										left: 0
									});
									elem.children('div.layui-layer-content').height($this.height() - 95);
								});
							},
							success: function(layero, index) {
								//弹出窗口成功后渲染表单
								var form = layui.form();
								form.render();
								form.on('select(transparent)',function (data) {
                                    var type=data.value;
                                    switch (type){
                                        case "1":
                                            $("#videoId").parent().parent().hide();
                                            break;
                                        case "2":
                                            $("#videoId").parent().parent().show();
                                            break;
                                    }
                                });
								form.on('select(targetType)',function (data) {
                                    var type=data.value;
                                    switch (type){
                                        case "ALL":
                                            $("#target1").parent().parent().hide();
                                            $("#userId").parent().parent().hide();
                                            $("#tag").parent().parent().hide();
                                            break;
                                        case "PLANTFORM":
                                            $("#target1").parent().parent().show();
                                            $("#userId").parent().parent().hide();
                                            $("#tag").parent().parent().hide();
                                            break;
                                        case "USERID":
                                            $("#target1").parent().parent().hide();
                                            $("#userId").parent().parent().show();
                                            $("#tag").parent().parent().hide();
                                            break;
                                        case "TAG":
                                            $("#target1").parent().parent().hide();
                                            $("#userId").parent().parent().hide();
                                            $("#tag").parent().parent().show();
                                            break;
                                    }
                                });
								form.on('submit(edit)', function(data) {
									$.ajax({
										url:'/push/save',
										data:$("#userForm").serialize(),
										type:'POST',
										success:function (data) {
											if(data=='success'){
											    layer.close(index);
											    location.reload();
											}
                                        }
									});
								});
							},
							end: function() {
								addBoxIndex = -1;
							}
						});
					});
				});
			});
		</script>
	</body>

</html>
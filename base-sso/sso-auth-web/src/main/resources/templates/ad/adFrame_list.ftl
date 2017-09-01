<#assign base=request.contextPath />
<!DOCTYPE html>
<#import "../macro/common.ftl" as common>
<html>

	<head>
		<meta charset="UTF-8">
		<title>数据字典</title>
		<link rel="stylesheet" href="${base}/static/plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="${base}/static/css/global.css" media="all">
		<link rel="stylesheet" href="${base}/static/plugins/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="${base}/static/css/table.css" />
	</head>

	<body>
		<div class="admin-main">

			<blockquote class="layui-elem-quote" style="border-left:0px solid #009688;">
				<@common.permission per="sys:dict:add">
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
                                <th>帧ID</th>
                                <th>帧名称</th>
                                <th>模板名称</th>
                                <th>资源名称</th>
                                <th>开始时间</th>
                                <th>结束时间</th>
                                <th>状态</th>
                                <th>操作人</th>
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
				<td>{{ item.frameId || '' }}</td>
				<td>{{ item.title || '' }}</td>
                <td>{{ item.adTplName || '' }}</td>
                <td>{{ item.adResName || '' }}</td>
                <td>{{ item.startTime || '' }}</td>
                <td>{{ item.endTime || '' }}</td>
                <td>{{#
                    if(item.status == 2){ }}
						已上线
                    {{# } else { }}
						已下线
                    {{# };}}</td>
				<td>{{ item.upName || '' }}</td>
                <td>
                        {{#if(item.status == 2){ }}
                        <a href="javascript:layui.stopAd('{{ item.id }}');" data-id="{{ item.id }}" data-opt="stop" class="layui-btn layui-btn-mini layui-btn-warm"><i class="layui-icon">&#xe651;</i>下线</a>
                        {{# } else { }}
                        <a href="javascript:layui.startAd('{{ item.id }}');" data-id="{{ item.id }}" data-opt="open" class="layui-btn layui-btn-mini"><i class="layui-icon">&#xe652;</i>上线</a>
                        <a href="javascript:;" data-id="{{ item.id }}" data-opt="edit" class="layui-btn layui-btn-mini"><i class="layui-icon">&#xe642;</i>编辑</a>
                        <a href="javascript:;" data-id="{{ item.id }}" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini"><i class="layui-icon">&#xe640;</i>删除</a>
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

			layui.use(['paging', 'upload', 'laydate','form'], function() {
				var $ = layui.jquery,
					paging = layui.paging(),
					layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
					layer = layui.layer, //获取当前窗口的layer对象
                    laydata=layui.laydate,
					form = layui.form();

                paging.init({
                    openWait: true,
                    url: '/adFrame/data?v=' + new Date().getTime(), //地址
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
                        var form = layui.form();
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
                                $.get('/adFrame/add', {id:$(this).data('id')}, function(form) {
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
                                            form.verify({
												startTime:function (value) {
													if(value.length<1){
														return "开始时间不能为空";
													}
                                                },
												endTime:function (value) {
												    var startTime=$("#startTime").val();
                                                    var stdt=new Date(startTime.replace("-","/"));
                                                    var etdt=new Date(value.replace("-","/"));
                                                    if(value.length<1){
                                                        return "结束时间不能为空";
                                                    }else if(stdt>etdt){
														return "开始时间必须小于结束时间";
													}
                                                }
											});
                                            form.on('submit(edit)', function(data) {
                                                $.ajax({
                                                    url:'/adFrame/save',
                                                    data:$("#dictForm").serialize(),
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
                                            location.reload();
                                        },
                                        cancel: function(index, layero){
                                            location.reload();
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
                                        url:'/adFrame/delete',
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

				$('#search').on('click', function() {
					parent.layer.alert('你点击了搜索按钮')
				});

				var addBoxIndex = -1;
				$('#add').on('click', function() {
					if(addBoxIndex !== -1)
						return;
					//本表单通过ajax加载 --以模板的形式，当然你也可以直接写在页面上读取
					$.get('/adFrame/add', null, function(form) {
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
                                form.verify({
                                    startTime:function (value) {
                                        if(value.length<1){
                                            layer.msg("开始时间不能为空");
                                            return "开始时间不能为空";
                                        }
                                    },
                                    endTime:function (value) {
                                        var startTime=$("#startTime").val();
                                        var stdt=new Date(startTime.replace("-","/"));
                                        var etdt=new Date(value.replace("-","/"));
                                        if(value.length<1){
                                            layer.msg("结束时间不能为空");
                                            return "结束时间不能为空";
                                        }else if(stdt>etdt){
                                            layer.msg("开始时间必须小于结束时间");
                                            return "开始时间必须小于结束时间";
                                        }
                                    }
                                });
								form.on('submit(edit)', function(data) {
									$.ajax({
										url:'/adFrame/save',
										data:$("#dictForm").serialize(),
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
                                location.reload();
							},
                            cancel: function(index, layero){
                                location.reload();
                            }
						});
					});
				});
				layui.startAd=function (id) {
                    $.ajax({
                        url:'/adFrame/updateStatus',
                        data:{id:id,status:2},
                        type:'POST',
                        success:function (data) {
                            if(data=='success'){
                                layer.msg("上线成功");
                                location.reload();
                            }else if(data=='adres'){
                                layer.msg('上线失败！资源被禁用状态', {
                                    icon: 1,
                                    time: 2000 //2秒关闭（如果不配置，默认是3秒）
                                }, function(){
                                    location.reload();
                                });
                            }else if(data=='adfilter'){
                                layer.msg('上线失败！模板被禁用状态', {
                                    icon: 1,
                                    time: 2000 //2秒关闭（如果不配置，默认是3秒）
                                }, function(){
                                    location.reload();
                                });
                            }else if(data=='adbox'){
                                layer.msg('上线失败！广告位被禁用状态', {
                                    icon: 1,
                                    time: 2000 //2秒关闭（如果不配置，默认是3秒）
                                }, function(){
                                    location.reload();
                                });
                            }
                        }
                    });
                };
				layui.stopAd=function (id) {
                    $.ajax({
                        url:'/adFrame/updateStatus',
                        data:{id:id,status:1},
                        type:'POST',
                        success:function (data) {
                            if(data=='success'){
                                layer.msg("下线成功");
                                location.reload();
                            }
                        }
                    });
                };
				$('#import').on('click', function() {
					var that = this;
					var index = layer.tips('只想提示地精准些', that, { tips: [1, 'white'] });
					$('#layui-layer' + index).children('div.layui-layer-content').css('color', '#000000');
				});
			});
		</script>
	</body>

</html>
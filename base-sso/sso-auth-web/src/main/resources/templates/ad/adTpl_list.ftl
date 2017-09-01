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
				<@common.permission per="sys:adtpl:add">
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
								<th>模板ID</th>
                                <th>模板名称</th>
                                <th>广告位名称</th>
                                <th>模板帧容量</th>
                                <th>帧切换时间</th>
                                <th>过滤条件</th>
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
				<td>{{ item.templateId || '' }}</td>
				<td>{{ item.title || '' }}</td>
				<td>{{ item.adBoxName || '' }}</td>
                <td>{{#
                    if(item.frameCapacity == 0){ }}
                    不限制
                    {{# } else { }}
                    {{ item.frameCapacity }}
                    {{# };}}</td>
				<td>{{ item.frameSwitchTime || 0 }}</td>
				<td>{{ item.conditionMap || '' }}</td>
				<td>
					<@common.permission per="sys:adtpl:save">
                        <a href="javascript:;" data-id="{{ item.id }}" data-opt="edit" class="layui-btn layui-btn-mini"><i class="layui-icon">&#xe642;</i>编辑</a>
					</@common.permission>
					<@common.permission per="sys:adtpl:delete">
                        <a href="javascript:;" data-id="{{ item.id }}" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini"><i class="layui-icon">&#xe640;</i>删除</a>
					</@common.permission>
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

			layui.use(['paging', 'upload', 'form'], function() {
				var $ = layui.jquery,
					paging = layui.paging(),
					layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
					layer = layui.layer, //获取当前窗口的layer对象
					form = layui.form();

                paging.init({
                    openWait: true,
                    url: '/adTpl/data?v=' + new Date().getTime(), //地址
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
                                $.get('/adTpl/add', {id:$(this).data('id')}, function(form) {
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
                                            var facmap="{";
                                            for(var i=0;i<num;i++){
                                                var key=$("#key_"+i).val();
                                                var value=$("#value_"+i).val();
                                                if(key==undefined||key=="undefined"||key=='undefined'){

                                                }else{
                                                    facmap+="'"+key+"':'"+value+"',";
                                                }
                                            }
                                            facmap=facmap.substring(0,facmap.length-1);
                                            facmap+="}";
                                            $("#conditionMap").val(facmap);
                                            $('form.layui-form').find('button[lay-filter=edit]').click();
                                        },
                                        success: function(layero, index) {
                                            //弹出窗口成功后渲染表单
                                            var form = layui.form();
                                            form.on('radio(defaultFilter)', function(data){
                                                var type=data.value;
                                                if(type==1){
                                                    $("#filter").attr("style","display: none;");
                                                }else {
                                                    $("#filter").attr("style","display: block;");
                                                }
                                            });
                                            var data=$("#conditionMap").val();
                                            console.log("data:"+data);
                                            if(data!=''){
                                                var json=eval('('+data+')');
                                                var i=0;
                                                for (var key in json){
                                                    for(var j=1;j<=i;j++){
                                                        addFilter();
                                                    }
                                                    $("#key_"+i).val(key);
                                                    $("#value_"+i).val(json[key]);
                                                    i++;
                                                }
											}
                                            form.render();
                                            form.on('submit(edit)', function(data) {
                                                $.ajax({
                                                    url:'/adTpl/save',
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
                                            num = 0;
                                            addBoxIndex = -1;
                                            location.reload();
                                        },
                                        cancel: function(index, layero){
                                            num = 0;
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
                                        url:'/adTpl/delete',
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

				var num=1;
                layui.addFcap=function () {
                    var html="";
                    html+="<div class='layui-inline' style=\"    margin-top: 10px;\"><label class=\"layui-form-label\"></label><div class=\"layui-input-inline\" style=\"    margin-top: 10px;\"><div class=\"layui-input-inline\" style=\"width: 100px;\">" +
                            "键： <input style=\"    margin-left: 30px;    margin-top: -25px;\" type=\"text\" id=\"key_"+num+"\" name=\"key_"+num+"\"  class=\"layui-input\"></div>" +
                            "<div class=\"layui-input-inline\" style=\"width: 100px;    margin-left: 35px;\">" +
                            "值：<input  style=\"    margin-left: 30px;    margin-top: -25px;\" type=\"text\" id=\"value_"+num+"\" name=\"value_"+num+"\"  class=\"layui-input\"></div>" +
                            "<div style=\"    margin-left: 350px;margin-top: -35px;\"><input type='button' class=\"layui-btn layui-btn-small\" onclick=\"layui.delFacp(this)\" value='删除过滤条件'/></div></div>"+
                            "</div>";
                    $("#addFcap").append(html);
                    num++;
                };
                layui.delFacp=function (obj) {
                    $(obj).parent().parent().parent().remove();
                };
				var addBoxIndex = -1;
				$('#add').on('click', function() {
					if(addBoxIndex !== -1)
						return;
					//本表单通过ajax加载 --以模板的形式，当然你也可以直接写在页面上读取
					$.get('/adTpl/add', null, function(form) {
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
                                var facmap="{";
                                for(var i=0;i<num;i++){
                                    var key=$("#key_"+i).val();
                                    var value=$("#value_"+i).val();
                                    if(key==undefined||key=="undefined"||key=='undefined'){

                                    }else{
                                        facmap+="'"+key+"':'"+value+"',";
                                    }
                                }
                                facmap=facmap.substring(0,facmap.length-1);
                                facmap+="}";
                                $("#conditionMap").val(facmap);
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
                                form.on('radio(defaultFilter)', function(data){
                                    var type=data.value;
                                    if(type==1){
                                        $("#filter").attr("style","display: none;");
									}else {
                                        $("#filter").attr("style","display: block;");
									}
                                });
                                form.on('submit(edit)', function(data) {
									$.ajax({
										url:'/adTpl/save',
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
                                num = 0;
								addBoxIndex = -1;
                                location.reload();
							},
                            cancel: function(index, layero){
                                num = 0;
                                location.reload();
							}
						});
					});
				});
                function addFilter() {
                    var html="";
                    html+="<div class='layui-inline' style=\"    margin-top: 10px;\"><label class=\"layui-form-label\"></label><div class=\"layui-input-inline\" style=\"    margin-top: 10px;\"><div class=\"layui-input-inline\" style=\"width: 100px;\">" +
                            "键： <input style=\"    margin-left: 30px;    margin-top: -25px;\" type=\"text\" id=\"key_"+num+"\" name=\"key_"+num+"\"  class=\"layui-input\"></div>" +
                            "<div class=\"layui-input-inline\" style=\"width: 100px;    margin-left: 35px;\">" +
                            "值：<input  style=\"    margin-left: 30px;    margin-top: -25px;\" type=\"text\" id=\"value_"+num+"\" name=\"value_"+num+"\"  class=\"layui-input\"></div>" +
                            "<div style=\"    margin-left: 350px;margin-top: -35px;\"><input type='button' class=\"layui-btn layui-btn-small\" onclick=\"layui.delFacp(this)\" value='删除过滤条件'/></div></div>"+
                            "</div>";
                    $("#addFcap").append(html);
                    num++;
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
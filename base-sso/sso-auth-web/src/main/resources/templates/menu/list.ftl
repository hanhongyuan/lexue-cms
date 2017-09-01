<#assign base=request.contextPath />
<!DOCTYPE html>
<#import "../macro/common.ftl" as common>
<html>

	<head>
		<meta charset="UTF-8">
		<title>菜单管理</title>
		<link rel="stylesheet" href="${base}/static/plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="${base}/static/css/global.css" media="all">
		<link rel="stylesheet" href="${base}/static/plugins/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="${base}/static/css/table.css" />
		<style type="text/css">
			.hide{display: none;}
		</style>
	</head>

	<body>
		<div class="admin-main">

			<blockquote class="layui-elem-quote" style="border-left:0px solid #009688;">
				<@common.permission per="sys:menu:add">
                    <a href="javascript:;" class="layui-btn layui-btn-small" id="add">
                        <i class="layui-icon">&#xe608;</i> 添加信息
                    </a>
				</@common.permission>

				<!--<a href="#" class="layui-btn layui-btn-small" id="getSelected">
					<i class="fa fa-shopping-cart" aria-hidden="true"></i> 获取全选信息
				</a>
				<a href="javascript:;" class="layui-btn layui-btn-small" id="search">
					<i class="layui-icon">&#xe615;</i> 搜索
				</a>-->
			</blockquote>
			<fieldset class="layui-elem-field">
				<legend>菜单列表</legend>
				<div class="layui-field-box layui-form">
					<table class="layui-table admin-table">
						<thead>
						<tr>
							<th>菜单名称</th>
							<th>访问地址</th>
							<th>权限标识</th>
							<th>是否禁用</th>
							<th>排序</th>
							<th>操作</th>
						</tr>
						</thead>
						<tbody id="content">
						<!-- 定义宏 -->
						<#macro treeArray entityArr>
							<#if entityArr??>
								<#list entityArr as ent>
									<tr data_pid="${(ent.parentId)!}" ${((ent.level??)&&ent.level>2)?string("class='hide'","")}>
										<td>
											<#list 1..ent.level as t>&nbsp;&nbsp;&nbsp;&nbsp;</#list>
											<#if ent.chindMenu??>
												<#if ent.level lt 2>
													<a href="javascript:" title="关闭" class="tree" data_tree="${(ent.id)!}"><i class="layui-icon" style="color: #999;font-size: 14px;">&#xe625;</i></a>
													<#else>
														<a href="javascript:" class="tree" title="展开" data_tree="${(ent.id)!}"><i class="layui-icon" style="color: #999;font-size: 14px;">&#xe623;</i></a>
												</#if>
											</#if>
											<a href="javascript:showMenu(this);" title="展开">${(ent.name)!}</a>
										</td>
										<td>${(ent.href)!}</td>
										<td>${(ent.permission)!}</td>
										<td>${((ent.isShow)??&& ent.isShow=="1")?string("开启","禁用")}</td>
										<td>${(ent.sort)!}</td>
										<td>
											<@common.permission per="sys:menu:save">
                                                <a href="javascript:;" data-id="${(ent.id)!}" data-opt="edit" class="layui-btn layui-btn-mini"><i class="layui-icon">&#xe642;</i>编辑</a>
											</@common.permission>
											<@common.permission per="sys:menu:delete">
                                                <a href="javascript:;" data-id="${(ent.id)!}" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini"><i class="layui-icon">&#xe640;</i>删除</a>
											</@common.permission>
										</td>
									</tr>
									<#if ent.chindMenu??> <@treeArray entityArr=ent.chindMenu /></#if>
								</#list>
							</#if>
						</#macro>
						<!-- 调用宏 -->
						<@treeArray entityArr=list>	</@treeArray>
						</tbody>
					</table>
				</div>
			</fieldset>
		</div>
		<script type="text/javascript" src="${base}/static/js/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="${base}/static/plugins/layui/layui.js"></script>
		<script>
			layui.config({
				base: '${base}/static/js/'
			});

			layui.use(['paging', 'form'], function() {
				var $ = layui.jquery,
					paging = layui.paging(),
					layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
					layer = layui.layer, //获取当前窗口的layer对象
					form = layui.form();
                layui.showTree=function () {
                    layer.open({
                        type: 2,
                        title:'请选择上级菜单',
                        area: ['400px', '500px'],
                        fixed: false, //不固定
                        maxmin: true,
                        content: '/menu/getTreeFrom'
                    });
                };
                //上级菜单
                layui.addPValue = function(name,pid){
                    $("#pname").val(name);
                    $("input[name='parentId']").val(pid);
                }
				var addBoxIndex = -1;
				$('#add').on('click', function() {
					if(addBoxIndex !== -1)
						return;
					//本表单通过ajax加载 --以模板的形式，当然你也可以直接写在页面上读取
					$.get('/menu/add', null, function(form) {
						addBoxIndex = layer.open({
							type: 1,
							title: '添加菜单',
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
								form.on('submit(edit)', function(data) {
									$.ajax({
										url:'/menu/save',
										data:$("#menuForm").serialize(),
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
                //绑定所有编辑按钮事件
                $('#content').children('tr').each(function() {
                    var $that = $(this);
                    $that.children('td:last-child').children('a[data-opt=edit]').on('click', function() {
                        //layer.msg($(this).data('id'));
                        $.get('/menu/add', {id:$(this).data('id')}, function(form) {
                            addBoxIndex = layer.open({
                                type: 1,
                                title: '修改用户',
                                content: form,
                                btn: ['保存', '取消'],
                                shade: false,
                                offset: ['100px', '20%'],
                                area: ['800px', '600px'],
                                success: function(layero, index) {
                                    //弹出窗口成功后渲染表单
                                    var form = layui.form();
                                    form.render();
                                    form.on('submit(edit)', function(data) {
                                        console.log(data.form);
                                        $.ajax({
                                            url:'/menu/save',
                                            data:$("#menuForm").serialize(),
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
								yes:function () {
                                    $('form.layui-form').find('button[lay-filter=edit]').click();
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
                                url:'/menu/delete',
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

			});
            //菜单展开关闭图标被点击
            $(".tree").click(function(){
                var a_tree = $(this);
                var title = a_tree.attr("title");
                if(title == '展开'){
                    openTr(a_tree);
                }else{
                    closeTr(a_tree);
                }
            });

            //开启子菜单
            function openTr(a){
                a.attr("title","关闭");
                a.find("i").html("&#xe625;");
                $("tr[data_pid='"+a.attr("data_tree")+"']").show();
            }

            //关闭子菜单
            function closeTr(a){
                a.attr("title","展开");
                a.find("i").html("&#xe623;");
                var childs = $("tr[data_pid='"+a.attr("data_tree")+"']");
                childs.hide();
                $.each(childs,function(idx,ele){
                    var array = $(ele).find(".tree");
                    if(array.size() > 0){
                        $.each(array,function(i,e){
                            closeTr($(e));
                        });
                    }
                });
            }
		</script>
	</body>

</html>
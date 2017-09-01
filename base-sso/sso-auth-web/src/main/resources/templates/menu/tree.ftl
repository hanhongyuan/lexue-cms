<#assign base=request.contextPath />
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>JeeBoom — 系统菜单树</title>
		<link rel="stylesheet" href="${base}/static/plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="${base}/static/css/global.css" media="all">
		<link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
	</head>
	<body class="anim-fadeInUp">
		<div style="margin: 15px;">
			<div class="layui-form layui-form-pane mt20" >
				<ul id="menuTree"></ul>
			</div>
		</div>
		<script type="text/javascript" src="${base}/static/plugins/layui/layui.js"></script>
		<script>
			layui.use(['tree', 'layer'],function(){
				var layer = layui.layer;
				var $ = layui.jquery;
				//获取菜单列表
				$.ajax({
					url:"/menu/getTree",
					type:"POST",
                    async: false,
					dataType:"json",
					success:function(data){
                            layui.tree({
                                elem:"#menuTree",
                                nodes:data,
                                click:function(item){
                                    parent.layui.addPValue(item.name,item.id);
                                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                                    parent.layer.close(index);
                                }
                            });
					}
				});

			});
		</script>
	</body>
</html>

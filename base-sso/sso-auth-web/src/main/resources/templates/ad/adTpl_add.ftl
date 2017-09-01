<#import "../macro/common.ftl" as common>
<div style="margin: 15px;">
	<form class="layui-form" id="dictForm">
		<div class="layui-form-item">
			<input type="hidden" name="filterId" value="${(adFilter.filterId)!}"/>
			<input type="hidden" name="tplid" value="${(adFilter.tplid)!}"/>
			<label class="layui-form-label">广告模版标题</label>
			<div class="layui-input-block">
				<input type="text" name="title" style="width: 90%" value="${(adFilter.title)!}" required lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">帧容量</label>
			<div class="layui-input-inline">
				<input type="text" name="frameCapacity"  required lay-verify="required" value="${(adFilter.frameCapacity)!}"  placeholder="0表示不限制帧数量" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">帧切换时间</label>
			<div class="layui-input-inline">
				<input type="text" name="frameSwitchTime" required lay-verify="required" value="${(adFilter.frameSwitchTime)!}"  placeholder="单位秒" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">帧选取类型</label>
			<div class="layui-input-inline">
                <select name="fcaps" lay-verify="required">
                <@common.mdictOptions title='itemPickType' value='${(adFilter.fcaps)!}'/>
                    <#--<option value="1" ${((adFilter.fcaps)?? && adFilter.fcaps==1)?string("selected","")}>全部</option>
                    <option value="2" ${((adFilter.fcaps)?? && adFilter.fcaps==2)?string("selected","")}>随机</option>
                    <option value="3" ${((adFilter.fcaps)?? && adFilter.fcaps==3)?string("selected","")}>优先级</option>
                    <option value="4" ${((adFilter.fcaps)?? && adFilter.fcaps==4)?string("selected","")}>特殊筛选</option>-->
                </select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">广告模版描述</label>
			<div class="layui-input-inline">
				<input type="text" name="note" value="${(adFilter.note)!}"  placeholder="" autocomplete="off" class="layui-input">
			</div>
		</div>
        <div class="layui-form-item">
            <label class="layui-form-label">是否默认过滤器</label>
            <div class="layui-input-block">
                <input type="radio" name="defaultFilter" value="1" title="是" lay-filter="defaultFilter"  checked="">
                <input type="radio" name="defaultFilter" value="0" title="否" lay-filter="defaultFilter" ${((adFilter.defaultFilter)?? && adFilter.defaultFilter==0)?string("checked","")}>
            </div>
        </div>

		<div
        <#if (adFilter.defaultFilter)?? && adFilter.defaultFilter= 1>
                class="hide"
        </#if>
                 id="filter">
            <input type="hidden" id="conditionMap" name="conditionMap" value="${(adFilter.conditionMap)!}"/>
            <div class="layui-inline" id="addFcap">
                <label class="layui-form-label">过滤条件</label>
                <div class="layui-input-inline" style="    margin-top: 10px;">
                    <div class="layui-input-inline" style="width: 100px;">
                       键： <input style="    margin-left: 30px;    margin-top: -25px;" type="text" id="key_0" name="key_0" placeholder="" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-input-inline" style="width: 100px;    margin-left: 35px;">
                        值：<input  style="    margin-left: 30px;    margin-top: -25px;" type="text" id="value_0" name="value_0" placeholder="" autocomplete="off" class="layui-input">
                    </div>
					<div style="    margin-left: 350px;margin-top: -35px;">
						<input type="button" class="layui-btn layui-btn-small" onclick="layui.addFcap()" value="增加过滤条件"/>
					</div>
                </div>
            </div>
            <div class="layui-form-item" style="margin-top: 15px;">
                <label class="layui-form-label">匹配逻辑</label>
                <div class="layui-input-inline">
                    <select name="logic" lay-verify="required">
                        <option value="1" ${((adFilter.logic)?? && adFilter.logic==1)?string("selected","")}>与</option>
                        <option value="2" ${((adFilter.logic)?? && adFilter.logic==2)?string("selected","")}>或</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">优先级</label>
                <div class="layui-input-inline">
                    <input type="text" name="priority" value="${(adFilter.priority)!}"  placeholder="单位秒<=0表示永久显示" autocomplete="off" class="layui-input">
                </div>
            </div>
		</div>
        <div class="layui-form-item">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-block">
                <@common.status title="status" value="${(adFilter.status)!}" />
                <#--<input type="radio" name="status" value="2" title="启用" checked="" >
                <input type="radio" name="status" value="1" title="禁用" ${((adFilter.status)?? && adFilter.status==1)?string("checked","")}>-->
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">广告位</label>
            <div class="layui-input-inline">
                <select name="boxId" lay-verify="required">
					<#if adBox??>
						<#list adBox as box>
								<option value="${box.boxId}" ${((adFilter.boxId)?? && adFilter.boxId==box.boxId)?string("selected","")}>${box.title}</option>
						</#list>
					</#if>
					<option></option>
                </select>
            </div>
        </div>
		<button lay-filter="edit" lay-submit style="display: none;"></button>
	</form>
	<script src="/static/js/jquery-1.4.4.min.js" type="text/javascript"></script>
	<script type="text/javascript">

		/*var num=1;
		function addFcap() {
            var html="";
            html+="<div class='layui-inline' style=\"    margin-top: 10px;\"><label class=\"layui-form-label\"></label><div class=\"layui-input-inline\" style=\"    margin-top: 10px;\"><div class=\"layui-input-inline\" style=\"width: 100px;\">" +
                    "键： <input style=\"    margin-left: 30px;    margin-top: -25px;\" type=\"text\" id=\"key_"+num+"\" name=\"key_"+num+"\"  class=\"layui-input\"></div>" +
                    "<div class=\"layui-input-inline\" style=\"width: 100px;    margin-left: 35px;\">" +
                    "值：<input  style=\"    margin-left: 30px;    margin-top: -25px;\" type=\"text\" id=\"value_"+num+"\" name=\"value_"+num+"\"  class=\"layui-input\"></div>" +
                    "<div style=\"    margin-left: 350px;margin-top: -35px;\"><button class=\"layui-btn layui-btn-small\" onclick=\"delFacp(this)\">删除过滤条件</button></div></div>"+
                    "</div>";
            $("#addFcap").append(html);
            num++;
        }
        function delFacp(obj) {
            $(obj).parent().parent().parent().remove();
        }*/
	</script>
</div>
<#-- 权限宏 -->
<#macro permission per>
    <@role permission=per;result><#if result>
        <#nested />
    </#if></@role>
</#macro>

<#-- 根据title与value获取字典对象info值 -->
<#macro mdictInfo title value>
    <@mdict t=title v=value>
    ${fre_info}
    </@mdict>
</#macro>

<#macro productName>
    <@name>
        ${info}
    </@name>
</#macro>

<#-- 根据title获取字典列表拼装option的dom -->
<#macro mdictOptions title value>
    <@mdict t=title>
        <#list fre_mdicts as mdict>
        <option value = "${mdict.value}" ${(mdict.value == value)?string("selected","")}>${(mdict.label)!}</option>
        </#list>
    </@mdict>
</#macro>


<#macro status title value>
    <@mdict t=title>
        <#list fre_mdicts as mdict>
            <input type="radio" name="status" value="${mdict.value}" title="${mdict.label}" ${(mdict.value == value)?string("checked","")}>
        </#list>
    </@mdict>
</#macro>
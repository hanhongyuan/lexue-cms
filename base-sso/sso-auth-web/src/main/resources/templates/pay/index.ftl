<#assign base=request.contextPath />
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>日志管理</title>
    <link rel="stylesheet" href="${base}/static/plugins/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${base}/static/css/global.css" media="all">
    <link rel="stylesheet" href="${base}/static/plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${base}/static/css/table.css" />
</head>

<body>
<div class="admin-main">
    <fieldset class="layui-elem-field">
        <legend>数据列表</legend>
        <div class="layui-field-box layui-form">
            <div class="bd">
                <ul class="payment" style="padding: 50px 0;overflow: hidden;text-align: center;">
                    <li style="float: left;margin-left: 320px;">
                        <img src="${base}/static/images/weixin.png" alt="微信支付">
                        <form action="/pay/scanPay" method="post">
                            <input type="hidden" name="productName" value="微信支付产品测试"><br>
                            <input type="hidden" name="orderPrice" value="0.1"><br>
                            <input type="hidden" name="payType" value="WEIXIN">
                            <input type="hidden" name="remark" value="微信支付备注信息"><br>
                            <p class="pay_btn" style="text-align: center;margin-top: 45px;">
                                <button  lay-submit lay-filter="formDemo" class="layui-btn layui-btn-small" value="0.1元支付体验">0.1元支付体验</button>
                            </p>
                        </form>
                    </li>
                    <li style="float: left;margin-left: 320px;">
                        <img src="${base}/static/images/pay.png" alt="支付宝支付">
                        <form action="/pay/scanPay" method="post">
                            <input type="hidden" name="productName" value="支付宝支付产品测试"><br>
                            <input type="hidden" name="orderPrice" value="0.1"><br>
                            <input type="hidden" name="payType" value="ALIPAY">
                            <input type="hidden" name="remark" value="支付宝支付备注信息"><br>
                            <p class="pay_btn"  style="text-align: center;margin-top: 45px;">
                                <button  lay-submit lay-filter="formDemo" class="layui-btn layui-btn-small"  value="">0.1元支付体验</button>
                            </p>
                        </form>
                    </li>
                </ul>
            </div>
        </div>
    </fieldset>
</div>
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

        form.on("submit(formDemo)",function (data) {
            console.log(data);
            layer.msg(JSON.stringify(data.field));
            return false;
        })

    });
</script>
</body>

</html>
package com.lexue.base.config;

/* *

 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public interface PayConfig {

	public interface AliConfig {
		// ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
		// 合作身份者ID，以2088开头由16位纯数字组成的字符串
		public static final String ALI_PARTNER = "2088911968060703";
		public static final String ALI_SELLER = "appdev@lexue.com";
		public static final String ALI_SERVICE = "mobile.securitypay.pay";
		// 商户的私钥
		public static final String ALI_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIMQ3LZD6dAEytHvtditc+/zqNYg26nE0g5ekTsgbBPWt8wnzOy1Z9/83PDfWJCcCbDP41jBfQnltP5pDAMjO6I6v0ye5GTlzm5OO8FkXY0lR4jFgvVd1CqzgXECVnCdYLwmUIrMwvOkcgAfaGhHiaWW1w/gPKUPBp4+N/Fim7EVAgMBAAECgYBrs4mLBRe3Zpi8Fv0Zhr9EqLdO7kVTpnbuc+tn6OanDsSOMQ6GXTN0rjdogEAo5PBZqRsrLiRv46DthqjmUyeGoEh6lxGplKvh+m1xawmd6O7Ikj765eKknQ4psxOoV9Dxcr1NXph7etzL/kgLMqdAdSkmHgUdEsIWXZIgbGTmAQJBANtbx7Zf61CR23ip6Gfz9khvz/tHauTovEXoXBFu+2G238hPuOl9XiDCdLcF/d+iR1/lxFLRgjVYf87T7hjWudUCQQCY9YHjG1AXjd3Oc5shRFN9/HW/+flSb86ar9aiccMBHLYLlPNpHVvYz87Rbd1h6YB5/6UYVOcz7SVsrcUF63pBAkEAkWlB9o+XOg22Y0KeoWqDtHkKchRhs+TkCaOChLwBQEEuD2dpuL42i5jcLgd2x6yXi+aQQiZIHnzQHJc4mpadEQJAWsyipiZ4jQLyQLPvyve8eHV9kNDbaExm4Pyw9Q2DLQ3WI1nwhN2gwDvcEgad/JmYYtOXNbJw7SpBpRoiczkuAQJAMoJ1H1L34WrjWpbwS+2RuxtNrFNIeE4FfhOv9Osq+JDjYKVmYgSg+TrGB40csbInWMTR4AlzJd1HZc3dKSRIiA==";
		//public static final String ALI_PRIVATE_KEY = "MIICXAIBAAKBgQDk7QZ01sQYLjfAdAfS7g4EQnpOLFVeDaq4meQnp3J/ouPng7+MXwEGByfIsOf5jpvmhcapXFXwVJrxXJUY6BhRQLmpeo4qDrq6wOzJnlGGi5rjbAa4TNVBl0I1KyIVhXvPKRbec0EirJez8Y5MGvqtl3ZV8a8+/XDBOFOXuiUBjQIDAQABAoGAUwp75hQly9OwG5q7X9NEuFzY+tRwHw78F7yTG6NNEGmpHEkZSbUIrQQZdV5et5L4kHWEa9j3v5chIV6BIyjF8LvE7+5jq5PdNuDeQwuUmfbQ3nUzwavRvSgNq0FhkIKETPo16cCDy4DSbPC+lfbbuXUv2YDeMJtVIl1flrM7ZmECQQD4tDX9rBItPB6xCPu5aiZHacLXUoXKCnNYtfYHoATui6VjXkpmGgvjfSKo/Kb25sYbi+KHxaakBZqTxzdfJjMZAkEA66RHe/CZTUoor/DbTXsPdzcGhTHmS7/5B6bc82gzMuEMs940NPrD/ZRcrWvURRuvvINKsLJ/jfYA5MW1Cn/klQJAVTeqqFklgUpwWcjyN/4fT6j1kkI8hZY/H855lvNFvVcsPHg6lfGm0TQ3hB/ONhutFhNAc7VN/DXPWYORfZDhQQJAQwoAwFUJ+EAx5SQ78AYjt0HzW+9/WmuqWK/zSliJwF7gc0ezSKNTIRCVcU0tHeArlhEwLacKvQOQ64UH5Y3PJQJBAOpTWsT0nf2b/ls3aX4/JDuyPa8iYVy/i2/Idyf08ecAF3fpp9ScC/+EJjot7u7NcRsuogR9HZkeUI8UumCraSg=";


		// 字符编码格式 目前支持 gbk 或 utf-8
		public static final String INPUT_CHARSET = "utf-8";

		// 签名方式 不需修改
		public static final String SIGN_TYPE = "MD5";

		public static final String ALI_PAY_TIME = "30m";
		public static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";
		
		public static final String ALI_KEY="wj5d4ndqnq26ne8eehvsds8ss71djd0o";
		
		public static final String ALI_QUERY_SERVICE = "single_trade_query";
		public static final String ALI_QUERY_BY_PAGE_SERVICE = "account.page.query";
		
		//扫码支付
		public static final String ALIPAY_GATEWAY_QRCODE = "https://openapi.alipay.com/gateway.do?";
	}

	/**
	 * lexue0526
	 * 
	 * @author fly
	 * 
	 */
	public interface TencentConfig {
		public static final String TENCENT_ORDER_RUL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		public static final String TENCENT_CLOSE_RUL = "https://api.mch.weixin.qq.com/pay/closeorder";
		public static final String TENCENT_QUERY_RUL = "https://api.mch.weixin.qq.com/pay/orderquery";
		public static final String TENCENT_DOWNLOAD_BILL_URL = "https://api.mch.weixin.qq.com/pay/downloadbill";
		
		//高考微信支付
		public static final String APP_ID = "wx8490e13c4adcaad2";
		public static final String APP_SECRET="f549325608fbc5028f6757ace09500e7";
		public static final String MERCHANT_ID = "1251234101";
		public static final String PARTNER = "1251234101";
		public static final String PACKAGE_NAME = "Sign=WXPay";
		
		//志愿微信支付
		public static final String ZY_APP_ID = "wx7a9745b5272a3665";
		public static final String ZY_APP_SECRET="bZNx5iqEKeur9FA6QoZI5LknQxbEaNQd";
		public static final String ZY_MERCHANT_ID = "1338604201";
		public static final String ZY_PARTNER = "1338604201";

		//中考微信支付
		public static final String ZK_APP_ID = "wxa45d87c271475c9e";
		public static final String ZK_APP_SECRET="dd5028d1a2576b0caa3a3f50645bf3b6";
		public static final String ZK_MERCHANT_ID = "1427262002";
		public static final String ZK_PARTNER = "1427262002";
		
		
		
		public static final String QQ_BARGAINOR_ID = "1381457301";
		public static final String QQ_SECRET = "8fed09f9a96d92ee32329bf4487694d0";
		public static final String QQ_ORDER_URL = "https://myun.tenpay.com/cgi-bin/wappayv2.0/wappay_init.cgi";
		public static final String QQ_APP_ID = "1104177532";
		public static final String QQ_APP_KEY = "Vxckad8JGKwjyczb";
		public static final String QQ_ORDER_QUERY_URL = "https://qpay.qq.com/cgi-bin/pay/qpay_order_query.cgi";
		
		

		//QQ中考支付参数
		public static final String QQ_ZK_BARGAINOR_ID = "1381457301";
		public static final String QQ_ZK_SECRET = "8fed09f9a96d92ee32329bf4487694d0";
		public static final String QQ_ZK_ORDER_URL = "https://myun.tenpay.com/cgi-bin/wappayv2.0/wappay_init.cgi";
		public static final String QQ_ZK_APP_ID = "1105796505";
		public static final String QQ_ZK_APP_KEY = "27pmZTRKkMbJYkv7";
		public static final String QQ_ZK_ORDER_QUERY_URL = "https://qpay.qq.com/cgi-bin/pay/qpay_order_query.cgi";
	}

}

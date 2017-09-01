package com.lexue.sso.web.controller;

import com.lexue.base.domain.Product;
import com.lexue.base.util.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * author lilong
 */
@Controller
@RequestMapping("/pay")
public class PayController extends BaseController{


    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "pay/index";
    }

    @RequestMapping(value = "/scanPay",method = RequestMethod.POST)
    public void createOrder(Product product, HttpServletRequest request){
        product.setOutTradeNo("LX"+DateUtils.getCurrentTimeToStr());
        if("ALIPAY".equals(product.getPayType())){
            request.setAttribute("WIDout_trade_no",product.getOutTradeNo());
            request.setAttribute("WIDtotal_fee",product.getOrderPrice());
            request.setAttribute("WIDsubject",product.getProductName());
            request.setAttribute("return_url","aliPay_return_url");
            request.setAttribute("notify_url","aliPay_notify_url");

        }
    }
}
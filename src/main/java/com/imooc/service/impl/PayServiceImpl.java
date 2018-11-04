package com.imooc.service.impl;

import com.imooc.dto.OrderDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.OrderService;
import com.imooc.service.PayService;
import com.imooc.util.JsonUtil;
import com.imooc.util.MathUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class PayServiceImpl implements PayService {

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private OrderService orderService;

    private static final String ORDER_NAME = "微信点单订餐";

    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getOrderId());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);

        // 租用的 暂时写死
        payRequest.setOpenid("oTgZpwV_b1tmtXrY4tGgT-uT041Y");

        log.info("[微信发起支付] request={}", JsonUtil.toJson(payRequest));

        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("[微信发起支付 response] ={}", JsonUtil.toJson(payResponse));

        return payResponse;

    }

    @Override
    public PayResponse notify(String notifyData) {
        // 1 验证签名
        // 2 支付状态
        // 3 支付金额 **
        // 4 支付人(下单人 == 支付人)

        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("[微信支付] 异步通知 payResponse={}", JsonUtil.toJson(payResponse));

        // 查询订单
        OrderDTO orderDTO = orderService.findOne(payResponse.getOrderId());
        if (orderDTO == null) {
            log.error("[微信发起支付, 异步通知，订单不存在，orderId={}, 微信通知金额={}，系统金额={}]",
                    payResponse.getOrderId(), payResponse.getOrderAmount(),
                    orderDTO.getOrderAmount());
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

       if(!MathUtil.equals(orderDTO.getOrderAmount().doubleValue(), payResponse.getOrderAmount().doubleValue())) {
           log.error("[微信发起支付, 异步通知，订单金额不一致，orderId={}, ]", payResponse.getOrderId());
           throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
       }

        orderService.paid(orderDTO);

        return payResponse;

    }

    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest request = new RefundRequest();
        request.setOrderId(orderDTO.getOrderId());
        request.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        request.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("[微信退款] request={}", JsonUtil.toJson(request));

        RefundResponse refundResponse = bestPayService.refund(request);
        log.info("[微信退款] response={}", JsonUtil.toJson(refundResponse));

        return refundResponse;

    }
}

package com.imooc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.imooc.dataobject.OrderDetail;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import com.imooc.util.EnumUtil;
import com.imooc.util.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    private String orderId;

    // 买家名字
    private String buyerName;

    // 买家电话
    private String  buyerPhone;

    // 买家地址
    private String  buyerAddress;

    // 买家微信openid
    private String  buyerOpenid;

    // 订单总金额
    private BigDecimal orderAmount;

    // 订单状态, 默认为新下单
    private Integer orderStatus;

    // 支付状态, 默认未支付 0
    private Integer payStatus;

    // 创建时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    // 更新时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    // 全局配置 如果为null不需要返回时，如果个别字段有约定必须要返回，
    // 那么如果是对象可以new，如果是String可以 = “”
//    List<OrderDetail> orderDetailList = new ArrayList<>();
    List<OrderDetail> orderDetailList;


    @JsonIgnore // 实体类转JSON时忽略
    public OrderStatusEnum getOrderStatusEnum() {
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum() {
        return EnumUtil.getByCode(payStatus, PayStatusEnum.class);
    }

}


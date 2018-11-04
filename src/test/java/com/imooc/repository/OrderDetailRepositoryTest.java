package com.imooc.repository;

import com.imooc.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void save() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("123457");
        orderDetail.setOrderId("1111111");
        orderDetail.setProductIcon("hhtp://xxxx.jpg");
        orderDetail.setProductId("12312321");
        orderDetail.setProductName("豆腐渣1");
        orderDetail.setProductPrice(new BigDecimal("3.12"));
        orderDetail.setProductQuantity(2);

        OrderDetail result = repository.save(orderDetail);
        Assert.assertNotNull(null, result);


    }

    @Test
    public void findByOrderId() {

        List<OrderDetail> orderDetailList = repository.findByOrOrderId("1111111");
        Assert.assertNotEquals(0, orderDetailList.size());

    }

}
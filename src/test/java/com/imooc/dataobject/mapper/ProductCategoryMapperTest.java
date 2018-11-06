package com.imooc.dataobject.mapper;

import com.imooc.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void insertByMap() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("category_name", "不爱");
        map.put("category_type", "101");
        int result = mapper.insertByMap(map);
        Assert.assertEquals(1, result);
    }

    @Test
    public void insertByObj() throws Exception {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("就是不爱");
        productCategory.setCategoryType(102);
        int result = mapper.insertByObj(productCategory);
        Assert.assertEquals(1, result);
    }

    @Test
    public void findByCategoryType() {
        ProductCategory productCategory = this.mapper.findByCategoryType(102);
        Assert.assertNotNull(productCategory);

    }

    @Test
    public void findByCategoryName() {
        List<ProductCategory> list = this.mapper.findByCategoryName("不爱");
        Assert.assertNotEquals(0, list.size());

    }

    @Test
    public void updateByCategoryType() {
        int result = mapper.updateByCategoryType("不爱1", 15);
        Assert.assertEquals(1, result);

    }

    @Test
    public void updateByObj() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("就是不爱");
        productCategory.setCategoryId(15);
        int result = mapper.updateByObj(productCategory);
        Assert.assertEquals(1, result);

    }

    @Test
    public void deleteById() {
        int result = mapper.deleteById(15);
        Assert.assertEquals(1, result);
    }

    @Test
    public void selectCategoryType() {
        ProductCategory productCategory = mapper.selectByCategoryType(101);
        Assert.assertNotNull(productCategory);
    }

}
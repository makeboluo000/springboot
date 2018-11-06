package com.imooc.dataobject.dao;

import com.imooc.dataobject.mapper.ProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class ProductCategoryDao {

    @Autowired
    private ProductCategoryMapper categoryMapper;

    public int insertByMap(Map<String, Object> map) {
        return categoryMapper.insertByMap(map);
    }


}

package com.imooc.enums;

import lombok.Getter;

@Getter
public enum ProductStatusEnum implements CodeEnum {

    UP(0, "上架"),
    DOWN(1, "下架")
    ;

    private Integer code;
    private String name;


    ProductStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

}

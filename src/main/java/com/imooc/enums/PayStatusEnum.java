package com.imooc.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum implements CodeEnum {
    WAIT(0, "未支付"),
    SUCCESS(1, "支付成功")
    ;

    private Integer code;
    private String name;


    PayStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

}

package com.imooc.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {

    @GetMapping("auth")
    public void auth(@RequestParam("code")String code) {
        log.info("进入auth方法");
        log.info("code={}", code);

    }


}

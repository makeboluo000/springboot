package com.imooc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

//@Component
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointConfig() {
        return new ServerEndpointExporter();
    }

}

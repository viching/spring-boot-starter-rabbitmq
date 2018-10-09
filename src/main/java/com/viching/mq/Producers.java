package com.viching.mq;

import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 生产者
 * 
 * @project spring-boot-starter-rabbitmq
 * @author Administrator
 * @date 2018年10月9日
 * Copyright (C) 2016-2020 www.viching.com Inc. All rights reserved.
 */
@Service
public class Producers {
	
    @Autowired
    RabbitMessagingTemplate rabbitSendTemplate;

    public void send(Object message) {
        rabbitSendTemplate.convertAndSend(Constant.EXCHANGE, Constant.ROUTINGKEY, message);
    }
}

package com.viching.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * 消费者
 * 
 * @project spring-boot-starter-rabbitmq
 * @author Administrator
 * @date 2018年10月9日
 * Copyright (C) 2016-2020 www.viching.com Inc. All rights reserved.
 */
@Service
public class Consumers {    
    
    private static final Logger logger = LoggerFactory.getLogger(Consumers.class);

    @RabbitListener(
    // 1.rabbitAdmin:RabbitAdmin名称
    admin = Constant.ADMIN, bindings = @QueueBinding(
    // 1.im.p2p:队列名,2.true:是否长期有效,3.false:是否自动删除
    value = @Queue(value = Constant.QUEUE, durable = "true", autoDelete = "false"),
    // 1.default.topic交换器名称(默认值),2.true:是否长期有效,3.topic:类型是topic
    exchange = @Exchange(value = Constant.EXCHANGE, durable = "true", type = "topic"),
    // user.exchange:路由的名称,ProducerConfig 里面
    // 绑定的路由名称(xxxx.to(exchange).with("user.exchange")))
    key = Constant.ROUTINGKEY))
    public void operate(Object message) {
        logger.info("operate message !");
        logger.info(message.toString());
    }
}

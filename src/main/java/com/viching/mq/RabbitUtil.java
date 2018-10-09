package com.viching.mq;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.converter.GenericMessageConverter;

/**
 * 工具类
 * 
 * @project spring-boot-starter-rabbitmq
 * @author Administrator
 * @date 2018年10月9日
 * Copyright (C) 2016-2020 www.viching.com Inc. All rights reserved.
 */
public class RabbitUtil {

    /**
     * 初始化 ConnectionFactory
     *
     * @param addresses
     * @param username
     * @param password
     * @param vHost
     * @return
     * @throws Exception
     */
    public static ConnectionFactory connectionFactory(String addresses, String username, String password, String vHost) throws Exception {
        CachingConnectionFactory factoryBean = new CachingConnectionFactory();
        factoryBean.setVirtualHost(vHost);
        factoryBean.setAddresses(addresses);
        factoryBean.setUsername(username);
        factoryBean.setPassword(password);
        return factoryBean;
    }

    /**
     * 初始化 RabbitMessagingTemplate
     *
     * @param connectionFactory
     * @return
     */
    public static RabbitMessagingTemplate simpleMessageTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        RabbitMessagingTemplate rabbitMessagingTemplate = new RabbitMessagingTemplate();
        rabbitMessagingTemplate.setMessageConverter(new GenericMessageConverter());
        rabbitMessagingTemplate.setRabbitTemplate(template);
        return rabbitMessagingTemplate;
    }
}

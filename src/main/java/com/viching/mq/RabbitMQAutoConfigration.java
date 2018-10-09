package com.viching.mq;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 自动化配置
 * 
 * @project spring-boot-starter-rabbitmq
 * @author Administrator
 * @date 2018年10月9日
 * Copyright (C) 2016-2020 www.viching.com Inc. All rights reserved.
 */
@Configuration
@Import({Producers.class, Consumers.class})
public class RabbitMQAutoConfigration {
    /**
     * 注入配置文件属性
     */
    @Value("${spring.rabbitmq.addresses}")
    String addresses;//MQ地址
    @Value("${spring.rabbitmq.username}")
    String username;//MQ登录名
    @Value("${spring.rabbitmq.password}")
    String password;//MQ登录密码
    @Value("${spring.rabbitmq.virtual-host}")
    String vHost;//MQ的虚拟主机名


    /**
     * 创建 ConnectionFactory
     *
     * @return
     * @throws Exception
     */
    @Bean
    public ConnectionFactory connectionFactory() throws Exception {
        return RabbitUtil.connectionFactory(addresses, username, password, vHost);
    }

    /**
     * 创建 RabbitAdmin
     *
     * @param connectionFactory
     * @return
     * @throws Exception
     */
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) throws Exception {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        return rabbitAdmin;
    }
    
    /**
     * 生产者配置
     * @param connectionFactory
     * @return
     * @throws Exception
     */
    @Bean
    public RabbitMessagingTemplate msgMessageTemplate(ConnectionFactory connectionFactory) throws Exception {
    	/*RabbitAdmin rabbitAdmin = rabbitAdmin(connectionFactory);
        //参数列表分别是：1.交换器名称(default.topic 为默认值),2.是否长期有效,3.如果服务器在不再使用时自动删除交换器
        TopicExchange exchange = new TopicExchange("default.topic", true, false);
        rabbitAdmin.declareExchange(exchange);
        //1.队列名称,2.声明一个持久队列,3.声明一个独立队列,4.如果服务器在不再使用时自动删除队列
        Queue queue = new Queue("im.p2p", true, false, false);
        rabbitAdmin.declareQueue(queue);
        //1.queue:绑定的队列,2.exchange:绑定到那个交换器,3.user.exchange:绑定的路由名称
        rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(exchange).with("user.exchange"));*/
        return RabbitUtil.simpleMessageTemplate(connectionFactory);
    }
}

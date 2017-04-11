package com.gta.edu.spring_boot_starter_rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 发送消息
 * @author wenwu.cai
 *
 */
@Component
public class RabbitSender {
	
	private static final Logger logger = LoggerFactory.getLogger(RabbitSender.class);

	@Autowired
	private AmqpTemplate rabbit;
	
	@Autowired
	private RabbitTemplate remplate;
	
	/**
	 * 发送消息
	 * @return
	 */
	public String sendMessage(String queueName,String message){
		try {
			//this.rabbit.convertAndSend(queueName, message);
			this.remplate.convertAndSend(queueName, message);
			logger.info("发送消息 = " + message);
			System.out.println("send message = "+ message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

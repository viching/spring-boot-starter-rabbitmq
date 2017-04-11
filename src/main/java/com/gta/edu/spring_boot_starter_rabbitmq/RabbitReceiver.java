package com.gta.edu.spring_boot_starter_rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * 接受消息
 * @author wenwu.cai
 *
 */
@Component
//@RabbitListener(queues = "${queueName}")
public class RabbitReceiver {

	private static final Logger logger = LoggerFactory.getLogger(RabbitReceiver.class);

	@Value("${queueName}")
	private String queueName;
	
	@Autowired
	private AmqpTemplate rabbit;
	
	@Autowired
	private RabbitTemplate template;
	
	/**
	 * 框架封装，自动监听改队列上是否有消息，如果有，则输出
	 * 使用AmqpTemplate发送信息
	 * @param name
	 */
	/*@RabbitHandler
	public void receiver(String message){
		System.out.println("Receiver = "+message);
		logger.debug("receiver = "+message);
	}*/
	
	/**
	 * 框架封装，自动监听改队列上是否有消息，如果有，则输出
	 * 使用RabbitTemplate发送信息
	 * @param name
	 */
	/*@RabbitHandler
	public void receiverRabbit(@Payload String message){
		System.out.println("Receiver = "+message);
		logger.debug("receiver = "+message);
	}*/
	
	/**
	 * 不管是AmqpTemplate还是RabbitTemplate发送信息，都是用这个手动封装
	 * 使用RabbitTemplate手动封装消费者接口
	 * @return
	 */
	public Object receiver(){
		template.setQueue(queueName);
		Object obj = template.receiveAndConvert();
		System.out.println("Receiver : "+obj);
		logger.debug("receiver = "+obj);
		return obj;
	}
	

}

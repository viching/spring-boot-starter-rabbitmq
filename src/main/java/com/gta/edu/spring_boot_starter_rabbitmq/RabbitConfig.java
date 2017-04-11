package com.gta.edu.spring_boot_starter_rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * 声明队列：动态创建交换机和队列，并将队列绑定到交换机下
 * @author wenwu.cai
 *
 */
@Configuration
public class RabbitConfig {
	
	@Value("${queueName}")
	private String QUEUE_NAME;
	
	@Value("${exchange}")
	private String EX_CHANGE;
	
	/**
	 * 声明队列
	 * @return
	 */
	@Bean
    public Queue helloQueue() {
        return new Queue(QUEUE_NAME);
    }
	
	/**
	 * 声明交换器
	 * 1、DirectExchange:如果是Direct类型，则会将消息中的RoutingKey与该Exchange关联的所有Binding中的BindingKey进行比较，如果相等，则发送到该Binding对应的Queue中
	 * 2、FanoutExchange:如果是  Fanout  类型，则会将消息发送给所有与该  Exchange  定义过  Binding  的所有  Queues  中去，其实是一种广播行为
	 * 3、TopicExchange:如果是Topic类型，则会按照正则表达式，对RoutingKey与BindingKey进行匹配，如果匹配成功，则发送到对应的Queue中
	 * @return
	 */
	@Bean
    public TopicExchange delayExchange() {
		TopicExchange exchange = new TopicExchange(EX_CHANGE);
        //DirectExchange exchange = new DirectExchange(EX_CHANGE);
        return exchange;
    }
	
	/**
	 * 为交换机绑定队列，如果传入了队列名称和交换机名称，则按照传入的名称生成绑定，如果没有传入，则安默认的生成绑定
	 * 1、如果是Direct类型，则会将消息中的RoutingKey与该Exchange关联的所有Binding中的BindingKey进行比较，如果相等，则发送到该Binding对应的Queue中
	 * 2、如果是  Fanout  类型，则会将消息发送给所有与该  Exchange  定义过  Binding  的所有  Queues  中去，其实是一种广播行为
	 * 3、如果是Topic类型，则会按照正则表达式，对RoutingKey与BindingKey进行匹配，如果匹配成功，则发送到对应的Queue中
	 * @param queueName
	 * @param exChange
	 * @return
	 */
	@Bean
	public Binding bindQueue(){
		return BindingBuilder.bind(helloQueue()).to(delayExchange()).with(QUEUE_NAME);
	}
}

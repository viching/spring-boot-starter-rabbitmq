# spring-boot-starter-rabbitmq

1，可用配置参数

spring:  
  rabbitmq:
    addresses: 127.0.0.1:5672 #MQ IP 和 端口
    username: mq #MQ登录名
    password: mq #MQ登录密码
    virtual-host: / #MQ的虚拟主机名称
    
 2,项目中提供了Pruducer(生产者)和Consumers(消费者)的服务类，提示一下，这个可以当作默认配置，也可以仅作参考。
 
    @Autowired
    RabbitMessagingTemplate rabbitSendTemplate;
    
    
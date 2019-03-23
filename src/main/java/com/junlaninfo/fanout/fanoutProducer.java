package com.junlaninfo.fanout;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.junlaninfo.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;


/**
 *  作者：xuexionghui
        邮箱：413669152@qq.com
        时间：2019年3月21日
        类作用：  发布订阅模式     生产者
 */
public class fanoutProducer {
      
	//交换机的名称
	public   static  String    EXCHANGE_NAME ="fanout_exchange";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		
		
		Connection connection = RabbitMqUtils.createNewConnection();
		Channel channel = connection.createChannel();
		//绑定交换机     参数1交互机名称 参数2 exchange类型
		channel.exchangeDeclare(EXCHANGE_NAME,  "fanout");
		//消息内容
	     String  msg="订阅发布模式，生产者传来的消息";
		//发布信息
		channel.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes());
		
		//关闭连接
		channel.close();
		connection.close();

	}

}

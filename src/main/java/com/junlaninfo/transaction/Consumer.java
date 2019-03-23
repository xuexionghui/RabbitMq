package com.junlaninfo.transaction;

import com.junlaninfo.utils.RabbitMqUtils;
import com.junlaninfo.utils.RabbitMqUtils;
import java.io.IOException;
import java.util.concurrent.TimeoutException;




import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import com.rabbitmq.client.AMQP.BasicProperties;

/**
 *  作者：xuexionghui
        邮箱：413669152@qq.com
        时间：2019年3月22日
        类作用：
 */
public class Consumer {
	private static final String QUEUE_NAME = "test_queue";
     private void mian() throws Exception {
    	// 1.获取连接
 		Connection connection = RabbitMqUtils.createNewConnection();
 		// 2.创建通道
 		Channel channel = connection.createChannel();
 		// 3.创建队列声明
 		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
 		// confirm机制
 		channel.confirmSelect();
 		String msg = "test_yushengjun110";
 		// 4.发送消息
 		channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
 		System.out.println("生产者发送消息:" + msg);
 		if (!channel.waitForConfirms()) {
 			System.out.println("消息发送失败!");
 		} else {
 			System.out.println("消息发送成功!");
 		}
 		channel.close();
 		connection.close();
	}
} 

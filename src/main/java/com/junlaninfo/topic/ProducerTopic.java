package com.junlaninfo.topic;

import com.junlaninfo.utils.RabbitMqUtils;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
/*
 * 主题模式
 */
public class ProducerTopic {
	private static final String EXCHANGE_NAME = "topic_exchange";

	public static void main(String[] args) throws IOException, TimeoutException {
		// 1.创建新的连接
		Connection connection = RabbitMqUtils.createNewConnection();
		// 2.创建通道
		Channel channel = connection.createChannel();
		// 3.绑定的交换机 参数1交互机名称 参数2 exchange类型
		channel.exchangeDeclare(EXCHANGE_NAME, "topic");
		String routingKey = "log.info.error";
		String msg = "topic_exchange_msg" + routingKey;
		// 4.发送消息
		channel.basicPublish(EXCHANGE_NAME, routingKey, null, msg.getBytes());
		System.out.println("生产者发送msg：" + msg);
		// // 5.关闭通道、连接
		channel.close();
		connection.close();
		// 注意：如果消费没有绑定交换机和队列，则消息会丢失

	}

}
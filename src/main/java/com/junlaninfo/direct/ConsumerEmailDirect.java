package com.junlaninfo.direct;

import java.io.IOException;
import java.util.concurrent.TimeoutException;




import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.junlaninfo.utils.RabbitMqUtils;
import com.rabbitmq.client.AMQP.BasicProperties;

/*
 * 路由模式   消费者
 */

public class ConsumerEmailDirect {
	private static final String QUEUE_NAME = "consumer_direct_email";
	private static final String EXCHANGE_NAME = "direct_exchange";

	public static void main(String[] args) throws IOException, TimeoutException {
		System.out.println("邮件消费者启动");
		// 1.创建新的连接
		Connection connection = RabbitMqUtils.createNewConnection();
		// 2.创建通道
		Channel channel = connection.createChannel();
		// 3.消费者关联队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		// 4.消费者绑定交换机 参数1 队列 参数2交换机 参数3 routingKey
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "error");
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "info");
		DefaultConsumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				String msg = new String(body, "UTF-8");
				System.out.println("消费者获取生产者消息:" + msg);
			}
		};
		// 5.消费者监听队列消息
		channel.basicConsume(QUEUE_NAME, true, consumer);
	}

}
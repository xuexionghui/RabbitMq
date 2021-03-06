package com.junlaninfo.fanout;

import java.io.IOException;
import java.util.concurrent.TimeoutException;




import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.junlaninfo.utils.RabbitMqUtils;
import com.rabbitmq.client.AMQP.BasicProperties;

/**
 *  作者：xuexionghui
        邮箱：413669152@qq.com
        时间：2019年3月21日
        类作用：发布订阅模式   邮件消费者
 */
public class emailFanoutConsumer {
	
	private static final String QUEUE_NAME = "consumerFanout_email";
	private static final String EXCHANGE_NAME = "fanout_exchange";

	public static void main(String[] args) throws IOException, TimeoutException {
		System.out.println("邮件消费者启动");
		// 1.创建新的连接
		Connection connection = RabbitMqUtils.createNewConnection();
		// 2.创建通道
		Channel channel = connection.createChannel();
		// 3.消费者关联队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		// 4.消费者绑定交换机 参数1 队列 参数2交换机 参数3 routingKey
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");
		DefaultConsumer consumer = new DefaultConsumer(channel) {
		
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

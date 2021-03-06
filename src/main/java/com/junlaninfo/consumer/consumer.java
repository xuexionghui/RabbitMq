package com.junlaninfo.consumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.junlaninfo.utils.RabbitMqUtils;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

/**
 *  作者：xuexionghui
        邮箱：413669152@qq.com
        时间：2019年3月21日
        类作用：点对点(简单)的队列   消费者  自动应答模式
 */
public class consumer {


		private static final String QUEUE_NAME = "test_queue";

		public static void main(String[] args) throws IOException, TimeoutException {
			System.out.println("002");
			// 1.获取连接
			Connection newConnection = RabbitMqUtils.createNewConnection();
			// 2.获取通道
			Channel channel = newConnection.createChannel();
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			
			DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
						throws IOException {
					
					String msgString = new String(body, "UTF-8");
					System.out.println("消费者获取消息:" + msgString);
					
				}
			}; 
			// 3.监听队列                                          true为自动应答 ，而false为手动应答
			channel.basicConsume(QUEUE_NAME, true, defaultConsumer);

		}

	}



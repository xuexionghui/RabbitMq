package com.junlaninfo.producer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.junlaninfo.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 *  作者：xuexionghui
        邮箱：413669152@qq.com
        时间：2019年3月21日
        类作用：点对点(简单)的队列   生产者
 */
public class producer {
    
	//队列的名称
	private static final String QUEUE_NAME = "test_queue";
	public static void main(String[] args) throws IOException, TimeoutException {
		       // 1.获取连接
				 Connection connection = RabbitMqUtils.createNewConnection();
				// 2.创建通道
				Channel channel = connection.createChannel();
				// 3.创建队列声明
				channel.queueDeclare(QUEUE_NAME, false, false, false, null);
				// 4.发送消息
				String  msg="666";
				channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
				System.out.print(msg);
				//5、关闭通道、连接
				channel.close();
				connection.close();
				

	}

}

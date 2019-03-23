package com.junlaninfo.transaction;

import com.junlaninfo.utils.RabbitMqUtils;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
/*
 * amqp事务提交机制，当执行逻辑发生错误的时候回滚   撤回消息
 */
public class Producer {
	private static final String QUEUE_NAME = "test_queue";

	public static void main(String[] args) throws IOException, TimeoutException {
		// 1.获取连接
		Connection newConnection =RabbitMqUtils.createNewConnection();
		// 2.创建通道
		Channel channel = newConnection.createChannel();
		// 3.创建队列声明
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		// 将当前管道设置为 txSelect 将当前channel设置为transaction模式 开启事务
		channel.txSelect();
		String msg = "test_yushengjun110";
		try {
			// 4.发送消息
			channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
			// int i = 1 / 0;
			channel.txCommit();// 提交事务
			System.out.println("生产者发送消息:" + msg);
		} catch (Exception e) {
			System.out.println("消息进行回滚操作");
			channel.txRollback();// 回滚事务
		} finally {
			channel.close();
			newConnection.close();
		}

	}

}
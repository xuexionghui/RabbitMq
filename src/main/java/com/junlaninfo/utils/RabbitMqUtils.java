package com.junlaninfo.utils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 *  作者：xuexionghui
        邮箱：413669152@qq.com
        时间：2019年3月21日
        类作用：获取RabbitMq的连接
 */
public class RabbitMqUtils {
   
   public static Connection createNewConnection() throws IOException, TimeoutException {
	   
	        // 1.定义连接工厂
			ConnectionFactory factory = new ConnectionFactory();
			// 2.设置服务器地址
			factory.setHost("192.168.81.130");
			// 3.设置AMQP协议端口号
			factory.setPort(5672);  
			// 4.设置vhost
			factory.setVirtualHost("/xuexionghui");
			// 5.设置用户名称
		   factory.setUsername("admin");
			// 6.设置用户密码
			factory.setPassword("admin");
			// 7.创建新的连接
			Connection connection = factory.newConnection();
			return connection;
	
}

}

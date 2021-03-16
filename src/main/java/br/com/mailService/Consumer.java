package br.com.mailService;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

public class Consumer {

	public void consumer() throws IOException, TimeoutException {

		ConnectionFactory factory = new ConnectionFactory();
		
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare("hello", false, false, false, null);
			
		channel.basicConsume("hello", true, new DeliverCallback( ) {
			@Override
			public void handle(String consumerTag, Delivery message) throws IOException {
				String m = new String(message.getBody(), "UTF-8");
				System.out.println("I just receive a mesasge " + m);
			}
		}, new CancelCallback() {
			@Override
			public void handle(String consumerTag) throws IOException {
			}
		});
	}

}

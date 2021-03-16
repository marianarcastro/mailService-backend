package br.com.mailService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeoutException;

import org.springframework.stereotype.Service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Service
public class Sender {

	public void sender() throws IOException, TimeoutException {

		ConnectionFactory factory = new ConnectionFactory();
		
		try(Connection connection = factory.newConnection()) {
			Channel channel = connection.createChannel();
			channel.queueDeclare("mail", false, false, false, null);
			
			String message = "Enviar email para usuarios adm na data - " + LocalDateTime.now();
			
			channel.basicPublish("", "mail", null, message.getBytes());
		}
	}

}

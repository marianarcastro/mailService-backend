package br.com.mailService.endpoint;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import br.com.mailService.Sender;
import br.com.mailService.kafka.MailProducer;


@RestController
@RequestMapping(value="/email")
public class EmailEndPoint {

	@Autowired
	private Sender sender;
	
	@Autowired
	private MailProducer mailProducer;
	
	@RequestMapping(method=RequestMethod.POST)
	public void sender() throws IOException, TimeoutException {
		sender.sender();
		String mail = "Enviando email para todos usuários";
		mailProducer.send(mail);
	}
}

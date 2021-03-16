package br.com.mailService.kafka;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
 
@Component
public class MailProducer {
 
    @Value("${mail.topic}")
    private String mailTopic;
 
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
 
    public void send(String mail) {
        kafkaTemplate.send(mailTopic, mail);
    }
}

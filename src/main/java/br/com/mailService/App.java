package br.com.mailService;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    public static void main(String[] args) throws IOException, TimeoutException {
    	Consumer c = new Consumer();
    	c.consumer();
        SpringApplication.run(App.class, args);
    }
}
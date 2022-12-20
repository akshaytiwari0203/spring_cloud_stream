package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.converter.MessageConverter;

import com.encypt.EncryptingConverter;

@SpringBootApplication
@ComponentScan("com")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public MessageConverter customMessageConverter() {
		return new EncryptingConverter();
	}

}

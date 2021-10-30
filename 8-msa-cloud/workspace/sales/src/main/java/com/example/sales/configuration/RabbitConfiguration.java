package com.example.sales.configuration;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 객체구조로 메시지큐 주고받을때 필요함
@Configuration
public class RabbitConfiguration {

	// 객체 -> JSON
	@Bean
	public MessageConverter rabbitMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

}
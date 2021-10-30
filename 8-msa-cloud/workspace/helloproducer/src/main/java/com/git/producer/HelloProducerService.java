package com.git.producer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloProducerService {

	// RabbitTemplate : yml 파일의 설정정보를 읽어서 의존주입???
	//
	private RabbitTemplate rabbit;

	@Autowired
	public HelloProducerService(RabbitTemplate rabbit) {
		this.rabbit = rabbit;
	}

	public void sendMessage(byte[] message) {
		System.out.println(message);
		// 받는 쪽 번호 (내번호 말고 다른거)
		rabbit.send("test.hello.1", new Message(message));
		rabbit.send("test.hello.2", new Message(message));
		rabbit.send("test.hello.3", new Message(message));
	}
}

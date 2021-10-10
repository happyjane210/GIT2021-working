package com.git.helloclient;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public class HelloClientService {

	private Map<String, SseEmitter> emitters = new HashMap<String, SseEmitter>();

	public void putSseClient(String clientId, SseEmitter emitter) {
		this.emitters.put(clientId, emitter);
		System.out.println("emitters: " + emitters.size());
	}

	public void removeSseClient(String clientId) {
		this.emitters.remove(clientId);
		System.out.println(emitters.size());
	}

	// 송신 내번호
	@RabbitListener(queues = "test.hello.2")
	public void receiveMessage2(String message) {
		System.out.println("— test.hello.2 —");
		System.out.println(message);
	}

	// 송신 재윤님 번호
//	@RabbitListener(queues = "test.hello.1")
//	public void receiveMessage1(String message) {
//		System.out.println("— test.hello.1 —");
//		System.out.println(message);
//	}
//
//	// 송신 준호님 번호
//	@RabbitListener(queues = "test.hello.3")
//	public void receiveMessage3(String message) {
//		System.out.println("— test.hello.3 —");
//		System.out.println(message);
//	}
}

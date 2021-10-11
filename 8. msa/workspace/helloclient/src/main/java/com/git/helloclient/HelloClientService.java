package com.git.helloclient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public class HelloClientService {

	private Map<String, SseEmitter> emitters = new HashMap<String, SseEmitter>();

	public void putEmitter(String clientId, SseEmitter emitter) {
		this.emitters.put(clientId, emitter);
		System.out.println("emitters: " + emitters.size());
	}

	public SseEmitter getEmitter(String clientId) {
		return this.emitters.get(clientId);
	}

	public void removeEmitter(String clientId) {
		this.emitters.remove(clientId);
		System.out.println(emitters.size());
	}

	// 1. 수신 내번호
	@RabbitListener(queues = "test.hello.2")
	public void receiveMessage2(String message) throws UnsupportedEncodingException {

		System.out.println("— test.hello.2 —");
		System.out.println(message);

		emitters.values().parallelStream().forEach(emitter -> {
			try {
				emitter.send(message);
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		});

	}

}

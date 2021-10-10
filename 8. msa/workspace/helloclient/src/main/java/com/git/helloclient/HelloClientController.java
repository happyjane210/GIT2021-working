package com.git.helloclient;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
public class HelloClientController {

	private HelloClientService service;

	public HelloClientController(HelloClientService service) {
		this.service = service;
	}

	@GetMapping("/event")
	public SseEmitter getEvent() {
		System.out.println("--connect--");
		SseEmitter emitter = new SseEmitter();

		service.setSseEmitter(emitter);

		return emitter;
	}
}

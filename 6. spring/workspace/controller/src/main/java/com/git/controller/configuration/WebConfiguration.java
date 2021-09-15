package com.git.controller.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	// CORS(cross origin resource sharing)�� ����
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
				// ������å�� ������ ���ҽ�
				.addMapping("/**") // ��ü���ҽ��� ��� /** , ������ ����(/todos,/ conteats ...)
				// ������å�� ����� ������(origin) ���
				// origin : html������ ������ ������ �ּ�
				// html������ ��� ������ �޾ƿԴ��� ����ϰ� ����
				// ���� ����� ������ -> ����
				.allowedOrigins("http://localhost:3000",
						"http://ec2-15-165-17-21.ap-northeast-2.compute.amazonaws.com ")
				// ������å���� ����� HTTP�޼���
				.allowedMethods("*");
	}
}

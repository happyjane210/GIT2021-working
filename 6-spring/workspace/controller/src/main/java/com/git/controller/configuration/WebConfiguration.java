package com.git.controller.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	// CORS(cross origin resource sharing)을 설정
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
				// 공유정책을 적용하 리소스
				.addMapping("/**") // 전체리소스를 허용 /** , 지정도 가능(/todos,/ conteats ...)
				// 공유정책을 허용할 오리진(origin) 목록
				// origin : html문서를 배포한 서버의 주소
				// html문서는 어디서 문서를 받아왔는지 기록하고 있음
				// 실제 통신은 브라우저 -> 서버
				.allowedOrigins("http://localhost:3000", "http://192.168.35.47:5501/",
						"http://ec2-15-165-17-21.ap-northeast-2.compute.amazonaws.com")
				// 공유정책으로 허용할 HTTP메서드
				.allowedMethods("*");
	}
}
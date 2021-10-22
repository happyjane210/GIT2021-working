package com.git.file;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class FileConfiguration {

    // S3에 접속하는 클라이언트를 싱글턴으로 생성
    // spring 에서 의존 주입해주는 객체로 사용하겠다.
    @Bean
    public AmazonS3 getS3Client() {


        return AmazonS3ClientBuilder.standard()

                .withRegion(Regions.AP_NORTHEAST_2)
                .build();
    }
}

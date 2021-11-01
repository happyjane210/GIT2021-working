package com.git.filesend;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration

public class FilesendConfiguration {

    // S3�� �����ϴ� Ŭ���̾�Ʈ�� �̱������� ����
    // spring ���� ���� �������ִ� ��ü�� ����ϰڴ�.
    @Bean
    public AmazonS3 getS3Client() throws IOException {
        ClassPathResource resource = new ClassPathResource("aws-access-info.txt");

        String str = new String(resource.getInputStream().readAllBytes());

        System.out.println("aws access info: " + str);

        String accessId = str.split(" ")[0];
        String secretKey = str.split(" ")[1];

        AWSCredentials credentials = new BasicAWSCredentials(accessId, secretKey);

        // getS3Client() ��ü�� builder()�� ���� ��ü�� ��������
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.AP_NORTHEAST_2)
                .build();
    }
}

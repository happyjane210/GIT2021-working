package com.git.filesend;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

@RestController
public class FilesendController {

    private final String BUCKET_NAME = "subscribe2021-bucket";
    private final String DISTRIBUTION_URL = "https://d18eq7msepmqk1.cloudfront.net/";
    private AmazonS3 client;

    @Autowired
    public FilesendController(AmazonS3 client) {
        this.client = client;
    }

    @PostMapping("/files")
    public String uploadFile(@RequestPart("file") MultipartFile file) throws IOException {
        System.out.println(file.getOriginalFilename());

        // 1. ���� ��Ÿ ������ ����
        // S3�� �ö󰡴� ��ü ��Ÿ�����͸� ��������
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());  // image/jpeg
        metadata.setContentLength(file.getSize());  // 50kb

        // 2. ��ü key ����
        // S3������ ���� ��� key
        // ��) git2021/images/penguin.jpg
        String objectKey = getObjectKey(file.getOriginalFilename());

        // 3. put ��û ��ü ����, public-read
        PutObjectRequest req = new PutObjectRequest(
                BUCKET_NAME,
                objectKey,
                file.getInputStream(),
                metadata
        ).withCannedAcl(CannedAccessControlList.PublicRead);


        // 4. ��ü ���ε�
        PutObjectResult result = client.putObject(req);
        System.out.println(result.getETag());

        return DISTRIBUTION_URL + objectKey;
    }


    @DeleteMapping("/files/{objectKey}")
    public void deleteFile(@PathVariable String objectKey, HttpServletResponse res) {
        // ��Ŷ�� ��ü�� �ִ��� Ȯ��
        if (!client.doesObjectExist(BUCKET_NAME, objectKey)) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        client.deleteObject(BUCKET_NAME, objectKey);
        System.out.println("Delete Complete: " + objectKey);
    }


    // OTP (one time password) : secret + unique + time - ��ȸ�� ������ȣ
    // ��) lsdjflskjflskj + jane + 17899819

    // object key �ؽ� ����
    private String getObjectKey(String filename) {
        String secret = "git2021";
        long timestamp = new Date().getTime();  // unix epoch time

        return sha256Hex(secret + filename + timestamp);
    }

}
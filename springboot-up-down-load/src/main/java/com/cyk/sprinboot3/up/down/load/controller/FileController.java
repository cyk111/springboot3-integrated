package com.cyk.sprinboot3.up.down.load.controller;

import io.minio.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @author cyk
 * @date 2023/10/29 15:09
 */
@RestController
public class FileController {

    // 参考 https://cloud.tencent.com/developer/article/2299468

    @Value("${minio.url}")
    private String minioUrl;

    @Value("${minio.accessKey}")
    private String minioAccessKey;

    @Value("${minio.secretKey}")
    private String minioSecretKey;

    // 文件上传接口
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // 创建MinIO客户端
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(minioUrl)
                    .credentials(minioAccessKey, minioSecretKey)
                    .build();

            // 生成随机文件名
            String filename = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();

            // 使用putObject方法上传文件
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket("my-bucket") // 替换为你实际的存储桶名称
                    .object(filename)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build());

            return "文件上传成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "文件上传失败：" + e.getMessage();
        }
    }

    @GetMapping("/read/{filename}")
    public ResponseEntity<InputStreamResource> readFile(@PathVariable String filename) {
        try {
            // 创建MinIO客户端
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(minioUrl)
                    .credentials(minioAccessKey, minioSecretKey)
                    .build();

            // 获取文件流
            GetObjectResponse objectResponse = minioClient.getObject(GetObjectArgs.builder()
                    .bucket("my-bucket") // 替换为你实际的存储桶名称
                    .object(filename)
                    .build());

            InputStream inputStream = objectResponse;

            // 设置响应头
            HttpHeaders headers = new HttpHeaders();
            //headers.add(HttpHeaders.CONTENT_TYPE, objectResponse.contentType());

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(new InputStreamResource(inputStream));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/download/{filename}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String filename) {
        try {
            // 创建MinIO客户端
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(minioUrl)
                    .credentials(minioAccessKey, minioSecretKey)
                    .build();

            // 获取文件流
            GetObjectResponse objectResponse = minioClient.getObject(GetObjectArgs.builder()
                    .bucket("my-bucket") // 替换为你实际的存储桶名称
                    .object(filename)
                    .build());

            InputStream inputStream = objectResponse;

            // 设置响应头
            HttpHeaders headers = new HttpHeaders();
            //headers.add(HttpHeaders.CONTENT_TYPE, objectResponse.contentType());
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(new InputStreamResource(inputStream));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{filename}")
    public String deleteFile(@PathVariable String filename) {
        try {
            // 创建MinIO客户端
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(minioUrl)
                    .credentials(minioAccessKey, minioSecretKey)
                    .build();

            // 删除文件
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket("my-bucket") // 替换为你实际的存储桶名称
                    .object(filename)
                    .build());

            return "文件删除成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "文件删除失败：" + e.getMessage();
        }
    }
}

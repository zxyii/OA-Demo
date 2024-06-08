package org.xunyin.officeautomationdemo.service.impl;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xunyin.officeautomationdemo.properties.MinioProperties;
import org.xunyin.officeautomationdemo.service.FileUploadService;
import java.io.InputStream;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    MinioProperties minioProperties;
    @SneakyThrows
    @Override
    public String fileUpload(MultipartFile multipartFile){
        MinioClient minioClient = MinioClient.builder()
                .endpoint(minioProperties.getEndpointUrl())
                .credentials(minioProperties.getAccessKey(),minioProperties.getSecretKey())
                .build();
        InputStream inputStream = multipartFile.getInputStream();
        String originalFileName = multipartFile.getOriginalFilename();
        String fileName =UUID.randomUUID().toString().replace("-","") + originalFileName;
        PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                .bucket(minioProperties.getBucketName())
                .stream(inputStream,inputStream.available(),-1)
                .object(fileName)
                .build();
        minioClient.putObject(putObjectArgs);
        String fileUrl = minioProperties.getEndpointUrl() + "/" + minioProperties.getBucketName()
                + "/" + fileName;
        return fileUrl;
    }
}

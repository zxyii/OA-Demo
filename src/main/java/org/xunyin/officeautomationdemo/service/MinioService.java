package org.xunyin.officeautomationdemo.service;

import io.minio.MinioClient;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface MinioService {
    public String uploadFile(MultipartFile file) throws Exception;

}

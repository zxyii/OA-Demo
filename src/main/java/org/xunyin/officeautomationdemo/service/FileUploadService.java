package org.xunyin.officeautomationdemo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {
    String fileUpload(MultipartFile multipartFile) throws IOException;
}

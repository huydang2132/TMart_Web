package com.project.tmartweb.services.file;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
    String uploadFile(MultipartFile multipartFile);

    byte[] downloadFile(String fileName);

    String deleteFile(String fileName);
}

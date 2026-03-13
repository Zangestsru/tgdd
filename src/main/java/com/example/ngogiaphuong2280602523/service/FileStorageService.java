package com.example.ngogiaphuong2280602523.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String storeFile(MultipartFile file) throws IOException {
        // Táº¡o thÆ° má»¥c náº¿u chÆ°a tá»“n táº¡i
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Táº¡o tĂªn file unique
        String originalFilename = file.getOriginalFilename();
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String newFilename = UUID.randomUUID().toString() + extension;

        // LÆ°u file
        Path filePath = uploadPath.resolve(newFilename);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Tráº£ vá» Ä‘Æ°á»ng dáº«n tÆ°Æ¡ng Ä‘á»‘i
        return "/" + uploadDir + "/" + newFilename;
    }

    public void deleteFile(String filePath) {
        try {
            if (filePath != null && !filePath.isEmpty()) {
                // Bá» dáº¥u "/" Ä‘áº§u tiĂªn náº¿u cĂ³
                String cleanPath = filePath.startsWith("/") ? filePath.substring(1) : filePath;
                Path path = Paths.get(cleanPath);
                Files.deleteIfExists(path);
            }
        } catch (IOException e) {
            // Log error nhÆ°ng khĂ´ng throw exception
            System.err.println("Could not delete file: " + filePath);
        }
    }

    public boolean isImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("image/");
    }
}


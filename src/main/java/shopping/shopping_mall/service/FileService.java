package shopping.shopping_mall.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {
    
    @Value("${uploadPath}")
    private String uploadDir;

    public String uploadFile(String oriName, byte[] fileData) throws IOException {
        UUID uuid = UUID.randomUUID();
        String saveFileName = uuid + "_" + oriName;

        Path path = Paths.get(uploadDir,saveFileName);
        Files.write(path, fileData);

        return saveFileName;
    }

    public void deleteFile(String filename) throws IOException {
        Path path = Paths.get(uploadDir, filename);
        Files.deleteIfExists(path);
    }
}




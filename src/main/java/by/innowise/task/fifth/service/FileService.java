package by.innowise.task.fifth.service;

import by.innowise.task.fifth.http.HttpResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileService {
    public static HttpResponse getFile(String path){
        try{
            Path realPath = Path.of("src/main/resources" + path);
            if(Files.exists(realPath)) {
                byte[] content = Files.readAllBytes(realPath);
                return new HttpResponse(200, content);
            } else {
                return new HttpResponse(404, "File not found");
            }
        } catch (IOException e) {
            return new HttpResponse(400, "Bad request");
        }
    }
}

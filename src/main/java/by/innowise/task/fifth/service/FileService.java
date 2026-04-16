package by.innowise.task.fifth.service;

import by.innowise.task.fifth.http.HttpResponse;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileService {
    public static HttpResponse getFile(String path){
        try{
            byte[] content = Files.readAllBytes(Path.of("src/main/resources" + path));
            return new HttpResponse(200, content);
        } catch(FileNotFoundException ex){
            return new HttpResponse(404, "File not found");
        } catch (IOException e) {
            return new HttpResponse(400, "Bad request");
        }
    }
}

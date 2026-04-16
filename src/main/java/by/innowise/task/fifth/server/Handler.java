package by.innowise.task.fifth.server;

import by.innowise.task.fifth.http.HttpRequest;
import by.innowise.task.fifth.http.HttpResponse;
import by.innowise.task.fifth.service.FileService;
import by.innowise.task.fifth.util.RequestParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Handler implements Runnable{
    private final Socket client;

    public Handler(Socket client){
        this.client = client;
    }

    @Override
    public void run() {
        try(InputStream in = client.getInputStream(); OutputStream out = client.getOutputStream()){
            HttpRequest request = RequestParser.parse(in);
            HttpResponse response = handle(request);
            out.write(response.toByte());
        } catch(Exception ex){
            try {
                OutputStream out = client.getOutputStream();
                HttpResponse response = new HttpResponse(500, "Internal server error");
                out.write(response.toByte());
            } catch (IOException ignored) {}
        }
    }

    private HttpResponse handle(HttpRequest request){
        if("/".equals(request.getPath())){
            return new HttpResponse(200, "Server's main route saying hi!");
        }

        return FileService.getFile(request.getPath());
    }
}

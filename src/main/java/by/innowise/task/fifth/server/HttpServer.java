package by.innowise.task.fifth.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8080);
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        while(true){
            Socket client = server.accept();
            executorService.submit(new Handler(client));
        }
    }
}

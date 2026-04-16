package by.innowise.task.fifth;

import by.innowise.task.fifth.http.HttpRequest;
import by.innowise.task.fifth.http.HttpResponse;
import by.innowise.task.fifth.service.FileService;
import by.innowise.task.fifth.util.RequestParser;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

public class HttpServerAndItsComponentsTest {
    @Test
    void shouldParseRequestMethod() throws IOException {
        String line = "GET /index.html HTTP/1.1\r\nHost: localhost\r\n\r\n";

        InputStream input = new ByteArrayInputStream(line.getBytes());
        HttpRequest result = RequestParser.parse(input);

        assertEquals("GET", result.getMethod());
    }

    @Test
    void shouldParseRequestPath() throws IOException {
        String line = "GET /index.html HTTP/1.1\r\nHost: localhost\r\n\r\n";

        InputStream input = new ByteArrayInputStream(line.getBytes());
        HttpRequest result = RequestParser.parse(input);

        assertEquals("/index.html", result.getPath());
    }

    @Test
    void shouldThrowRequest404OnUnExistFile(){
        String path = "/indexx.html";

        HttpResponse result = FileService.getFile(path);

        assertEquals(404, result.getStatus());
    }

    @Test
    void shouldThrowRequest20OnExistFile(){
        String path = "/index.html";

        HttpResponse result = FileService.getFile(path);

        assertEquals(200, result.getStatus());
    }

    //серв запустить
    @Test
    void shouldHandleMultipleRequests() {
        CompletableFuture<?>[] futures = IntStream.range(0, 50)
                .mapToObj(i -> CompletableFuture.runAsync(() -> {
                    try (Socket socket = new Socket("localhost", 8080)) {
                        OutputStream out = socket.getOutputStream();
                        InputStream in = socket.getInputStream();

                        out.write("GET / HTTP/1.1\r\n\r\n".getBytes());
                        out.flush();

                        byte[] buffer = new byte[1024];
                        int read = in.read(buffer);

                        assertTrue(read > 0);

                    } catch (Exception e) {
                        fail(e);
                    }
                }))
                .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(futures).join();
    }
}

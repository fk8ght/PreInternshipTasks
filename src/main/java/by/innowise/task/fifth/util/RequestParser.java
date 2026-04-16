package by.innowise.task.fifth.util;


import by.innowise.task.fifth.http.HttpRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RequestParser {
    public static HttpRequest parse(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String requestLine = reader.readLine();
        String[] parts = requestLine.split(" ");

        String method = parts[0];
        String path = parts[1];

        return new HttpRequest(method, path);
    }
}

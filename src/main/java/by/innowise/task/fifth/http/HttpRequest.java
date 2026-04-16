package by.innowise.task.fifth.http;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class HttpRequest {
    private String method;
    private String path;
}

package by.innowise.task.fifth.http;

public class HttpResponse {
    private int status;
    private byte[] body;

    public HttpResponse(int status, String body){
        this.status = status;
        this.body = body.getBytes();
    }

    public HttpResponse(int status, byte[] body){
        this.status = status;
        this.body = body;
    }

    public byte[] toByte() {
        String statusText;

        switch (status) {
            case 400 -> statusText = "Bad Request";
            case 404 -> statusText = "Not Found";
            case 500 -> statusText = "Internal Server Error";
            default -> statusText = "OK";
        }

        String headers = "HTTP/1.1 " + status + " " + statusText + "\r\n" +
                        "Content-Length: " + body.length + "\r\n" +
                        "Content-Type: text/html\r\n" +
                        "\r\n";

        byte[] headerBytes = headers.getBytes();

        byte[] result = new byte[headerBytes.length + body.length];

        System.arraycopy(headerBytes, 0, result, 0, headerBytes.length);
        System.arraycopy(body, 0, result, headerBytes.length, body.length);

        return result;
    }
}

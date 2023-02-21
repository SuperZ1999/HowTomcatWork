package com.zmy.tomcatstudy.demo01;

import java.io.*;

public class Response {
    private static final int BUFFER_SIZE = 1024;
    private OutputStream outputStream;
    private Request request;

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void sendStaticResponse() {
        FileInputStream fis = null;
        try {
            File file = new File(HttpServer.WEB_ROOT, request.getUri());
            if (file.exists()) {
                fis = new FileInputStream(file);
                byte[] buffer = new byte[BUFFER_SIZE];
                String successMessage = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type:text/html\r\n"
                        + "\r\n";
                outputStream.write(successMessage.getBytes());
                int ch = fis.read(buffer, 0, BUFFER_SIZE);
                while (ch != -1) {
                    outputStream.write(buffer, 0, ch);
                    ch = fis.read(buffer, 0, BUFFER_SIZE);
                }
            } else {
                String errorMessage = "HTTP/1.1 404 File Not Found\r\n"
                        + "Content-Type:text/html\r\n"
                        + "Content-Length:23\r\n"
                        + "\r\n"
                        + "<h1>File Not Found</h1>";
                outputStream.write(errorMessage.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}

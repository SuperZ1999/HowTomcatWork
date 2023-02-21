package com.zmy.tomcatstudy.demo01;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class Request {
    private InputStream inputStream;
    private String uri;

    public Request(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void parse() {
        StringBuilder requestSb = new StringBuilder();
        byte[] buffer = new byte[2048];
        int i = 0;
        try {
            i = inputStream.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        requestSb.append(new String(buffer, 0, i));
        System.out.println(requestSb.toString());
        uri = parseUri(requestSb.toString());
    }

    private String parseUri(String requestString) {
        int i = requestString.indexOf(' ');
        if (i != -1) {
            int j = requestString.indexOf(' ', i + 1);
            if (j != -1) {
                return requestString.substring(i + 1, j);
            }
        }
        return null;
    }

    public String getUri() {
        return this.uri;
    }
}

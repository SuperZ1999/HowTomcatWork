package com.zmy.tomcatstudy.demo02;

public class StaticResponseProcessor {
    public void process(Request request, Response response) {
        response.sendStaticResponse();
    }
}

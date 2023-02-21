package com.zmy.tomcatstudy.demo02;

import javax.servlet.Servlet;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

public class ServletProcessor {
    public void process(Request request, Response response) {
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf('/') + 1);
        try {
            File classPath = new File(Constants.WEB_ROOT);
            String repository = new URL("file", null, classPath.getCanonicalFile() + File.separator).toString();
            URL[] urls = new URL[1];
            urls[0] = new URL(null, repository, (URLStreamHandler) null);
            URLClassLoader classLoader = new URLClassLoader(urls);
            String successMessage = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type:text/html\r\n"
                    + "\r\n";
            response.getWriter().println(successMessage);
            Class<?> myClass = classLoader.loadClass(servletName);
            Servlet servlet = (Servlet) myClass.newInstance();
            RequestFacade requestFacade = new RequestFacade(request);
            ResponseFacade responseFacade = new ResponseFacade(response);
            servlet.service(requestFacade, responseFacade);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

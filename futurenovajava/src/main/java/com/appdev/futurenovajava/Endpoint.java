package com.appdev.futurenovajava;

import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.Map;
import java.util.Optional;

public class Endpoint {

    public static Config config = new Endpoint.Config();

    private String path;
    private Map<String, String> queryItems;
    private Map<String, String> headers;
    private Optional<RequestBody> body = Optional.empty();
    private Method method;

    public enum Method {
        GET, POST, DELETE
    }

    static public class Config {
        public Optional<String> host = Optional.empty();
        public Optional<String> scheme = Optional.empty();
        public Optional<Integer> port = Optional.empty();
        public Optional<String> commonPath = Optional.empty();
        public Optional<Map<String, String>> commonHeaders = Optional.empty();
    }

    public Endpoint() { }

    public Endpoint path(String path) {
        this.path = path;
        return this;
    }

    public Endpoint queryItems(Map<String, String> queryItems) {
        this.queryItems = queryItems;
        return this;
    }

    public Endpoint headers(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public Endpoint body(Optional<RequestBody> body) {
        this.body = body;
        return this;
    }

    public Endpoint method(Method method) {
        this.method = method;
        return this;
    }

    public Request request() {

        Request.Builder requestBuilder = new Request.Builder();
        HttpUrl.Builder urlBuilder = new HttpUrl.Builder();

        if (!config.scheme.isPresent()) throw new AssertionError();
        urlBuilder.scheme(config.scheme.get());

        if (!config.host.isPresent()) throw new AssertionError();
        urlBuilder.host(config.host.get());

        if (config.commonPath.isPresent()) {
            String[] commonPaths = config.commonPath.get().split("/");
            for (String commonPath : commonPaths) {
                urlBuilder.addPathSegment(commonPath);
            }
        }

        urlBuilder.addPathSegment(this.path);

        config.port.ifPresent(urlBuilder::port);

        requestBuilder.url(urlBuilder.build());

        config.commonHeaders.ifPresent(stringStringMap -> stringStringMap.forEach(requestBuilder::addHeader));

        Optional<RequestBody> requestBody = this.body;
        if(requestBody.isPresent()) {
            requestBuilder.method(Method.POST.name(), requestBody.get());
        } else {
            requestBuilder.method(this.method.name(), null);
        }

        return requestBuilder.build();
    }

}
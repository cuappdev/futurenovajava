package com.appdev.futurenovajava;

import com.google.gson.Gson;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import java.util.concurrent.CompletableFuture;

public class FutureNovaRequest {

    private static OkHttpClient client = new OkHttpClient();
    private static Gson gson = new Gson();

    public static <T> CompletableFuture<APIResponse<T>> make(Class<T> type, Endpoint endpoint) {
        Call call = client.newCall(endpoint.request());
        OkHttpResponseFuture result = new OkHttpResponseFuture();
        call.enqueue(result);
        return result.future.thenApply((Response response) -> {
            try {
                String body = response.body().string();
                System.out.println(body);
                APIResponse<T> apiResponse = gson.fromJson(body, getType(APIResponse.class, type));
                return apiResponse;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    private static Type getType(Class<?> rawClass, Class<?> parameter) {
        return new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return new Type[] {parameter};
            }
            @Override
            public Type getRawType() {
                return rawClass;
            }
            @Override
            public Type getOwnerType() {
                return null;
            }
        };
    }
}

package com.appdev.futurenovajava;

public class APIResponse<T> {

    private boolean success;
    private T data;

    public boolean getSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }
}

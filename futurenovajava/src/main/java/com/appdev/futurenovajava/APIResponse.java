package com.appdev.futurenovajava;

public class APIResponse<T> {

    private boolean success;
    private T data;

    public boolean getSuccess() {
        return this.success;
    }

    public T getData() {
        return this.data;
    }
}

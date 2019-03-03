package com.appdev.futurenovajava;
import com.google.gson.annotations.SerializedName;

public class APIResponse<T> {

    boolean success;
    T data;
}

package com.appdev.futurenovajava;
import com.google.gson.annotations.SerializedName;

class APIResponse<T> {

    boolean success;
    T data;
}

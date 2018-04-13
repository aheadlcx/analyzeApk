package com.spriteapp.booklibrary.base;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Base<T> implements Serializable {
    private int code;
    @SerializedName(alternate = {"lists"}, value = "data")
    private T data;
    private String message;
    private String status;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T t) {
        this.data = t;
    }
}

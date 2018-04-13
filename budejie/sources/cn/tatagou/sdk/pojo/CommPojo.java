package cn.tatagou.sdk.pojo;

import java.io.Serializable;

public class CommPojo<T extends CommonResponseResult> extends CommonResponseResult implements Serializable {
    private String code;
    private T data;
    private String message;
    private String timestamp;

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String str) {
        this.timestamp = str;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T t) {
        this.data = t;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }
}

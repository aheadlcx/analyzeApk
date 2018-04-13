package cn.tatagou.sdk.pojo;

import java.util.List;

public class CommListPojo<T extends CommonResponseResult> {
    private String code;
    private List<T> data;
    private String message;
    private String timestamp;

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String str) {
        this.timestamp = str;
    }

    public List<T> getData() {
        return this.data;
    }

    public void setData(List<T> list) {
        this.data = list;
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

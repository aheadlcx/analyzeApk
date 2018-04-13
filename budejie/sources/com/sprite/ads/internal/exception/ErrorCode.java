package com.sprite.ads.internal.exception;

public class ErrorCode {
    public static final int EMPTY_DATA_ERROR = 15;
    public static final int IMAGE_LOAD_ERROR = 14;
    public static final int INIT_FAILED = 10;
    public static final int JSON_ERROR = 13;
    public static final int LOAD_FAIL = 11;
    public static final int PARAM_ERROR = 12;
    private int errorCode;

    public ErrorCode(int i) {
        this.errorCode = i;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String toString() {
        String str = "";
        switch (this.errorCode) {
            case 10:
                str = "初始化失败";
                break;
            case 11:
                str = "数据加载失败";
                break;
            case 12:
                str = "初始化失败";
                break;
            case 13:
                str = "json解析失败";
                break;
            case 14:
                str = "图片加载失败";
                break;
            case 15:
                str = "服务端返回数据为";
                break;
        }
        return "error code:" + this.errorCode + "," + str;
    }
}

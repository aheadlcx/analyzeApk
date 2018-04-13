package com.alipay.sdk.app;

import cn.v6.sdk.sixrooms.coop.V6Coop;
import com.ali.auth.third.core.rpc.protocol.RpcException.ErrorCode;

public enum i {
    SUCCEEDED(9000, "处理成功"),
    FAILED(4000, "系统繁忙，请稍后再试"),
    CANCELED(ErrorCode.SERVER_METHODNOTFOUND, "用户取消"),
    NETWORK_ERROR(ErrorCode.SERVER_PARAMMISSING, V6Coop.NET_ERROR),
    PARAMS_ERROR(ErrorCode.SERVER_REQUESTTIMEOUT, "参数错误"),
    DOUBLE_REQUEST(5000, "重复请求"),
    PAY_WAITTING(8000, "支付结果确认中");
    
    public int h;
    public String i;

    private i(int i, String str) {
        this.h = i;
        this.i = str;
    }

    private void b(int i) {
        this.h = i;
    }

    private int a() {
        return this.h;
    }

    private void a(String str) {
        this.i = str;
    }

    private String b() {
        return this.i;
    }

    public static i a(int i) {
        switch (i) {
            case ErrorCode.SERVER_REQUESTTIMEOUT /*4001*/:
                return PARAMS_ERROR;
            case 5000:
                return DOUBLE_REQUEST;
            case ErrorCode.SERVER_METHODNOTFOUND /*6001*/:
                return CANCELED;
            case ErrorCode.SERVER_PARAMMISSING /*6002*/:
                return NETWORK_ERROR;
            case 8000:
                return PAY_WAITTING;
            case 9000:
                return SUCCEEDED;
            default:
                return FAILED;
        }
    }
}

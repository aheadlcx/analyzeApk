package com.tencent.mm.opensdk.diffdev.a;

import com.qq.e.comm.constants.ErrorCode$NetWorkError;

public enum g {
    UUID_EXPIRED(402),
    UUID_CANCELED(403),
    UUID_SCANED(404),
    UUID_CONFIRM(ErrorCode$NetWorkError.RESOURCE_LOAD_FAIL_ERROR),
    UUID_KEEP_CONNECT(408),
    UUID_ERROR(500);
    
    private int code;

    private g(int i) {
        this.code = i;
    }

    public final int getCode() {
        return this.code;
    }

    public final String toString() {
        return "UUIDStatusCode:" + this.code;
    }
}

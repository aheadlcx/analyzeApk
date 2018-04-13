package com.tencent.mm.opensdk.diffdev.a;

public enum g {
    UUID_EXPIRED(402),
    UUID_CANCELED(403),
    UUID_SCANED(404),
    UUID_CONFIRM(405),
    UUID_KEEP_CONNECT(408),
    UUID_ERROR(500);
    
    private int a;

    private g(int i) {
        this.a = i;
    }

    public final int getCode() {
        return this.a;
    }

    public final String toString() {
        return "UUIDStatusCode:" + this.a;
    }
}

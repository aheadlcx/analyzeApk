package com.zhihu.matisse.internal.entity;

public class CaptureStrategy {
    public final String authority;
    public final boolean isPublic;

    public CaptureStrategy(boolean z, String str) {
        this.isPublic = z;
        this.authority = str;
    }
}

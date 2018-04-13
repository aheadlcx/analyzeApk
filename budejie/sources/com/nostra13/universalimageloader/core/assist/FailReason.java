package com.nostra13.universalimageloader.core.assist;

public class FailReason {
    private final FailReason$FailType a;
    private final Throwable b;

    public FailReason(FailReason$FailType failReason$FailType, Throwable th) {
        this.a = failReason$FailType;
        this.b = th;
    }
}

package com.tencent.tinker.android.dex;

public class DexException extends RuntimeException {
    static final long serialVersionUID = 1;

    public DexException(String str) {
        super(str);
    }

    public DexException(Throwable th) {
        super(th);
    }
}

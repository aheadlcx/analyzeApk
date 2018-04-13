package com.tencent.tinker.loader;

public class TinkerRuntimeException extends RuntimeException {
    private static final String TINKER_RUNTIME_EXCEPTION_PREFIX = "Tinker Exception:";
    private static final long serialVersionUID = 1;

    public TinkerRuntimeException(String str) {
        super(TINKER_RUNTIME_EXCEPTION_PREFIX + str);
    }

    public TinkerRuntimeException(String str, Throwable th) {
        super(TINKER_RUNTIME_EXCEPTION_PREFIX + str, th);
    }
}

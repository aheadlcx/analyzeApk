package com.tencent.tinker.loader.hotplug;

public class UnsupportedEnvironmentException extends UnsupportedOperationException {
    public UnsupportedEnvironmentException(String str) {
        super(str);
    }

    public UnsupportedEnvironmentException(Throwable th) {
        super(th);
    }
}

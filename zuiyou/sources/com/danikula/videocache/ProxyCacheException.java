package com.danikula.videocache;

public class ProxyCacheException extends Exception {
    public ProxyCacheException(String str) {
        super(str + ". Version: 2.7.0");
    }

    public ProxyCacheException(String str, Throwable th) {
        super(str + ". Version: 2.7.0", th);
    }

    public ProxyCacheException(Throwable th) {
        super("No explanation error. Version: 2.7.0", th);
    }
}

package com.facebook.imagepipeline.decoder;

public class DecodeException extends RuntimeException {
    public DecodeException(String str) {
        super(str);
    }

    public DecodeException(String str, Throwable th) {
        super(str, th);
    }
}

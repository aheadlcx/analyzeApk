package com.facebook.stetho.dumpapp;

class DumpappOutputBrokenException extends RuntimeException {
    public DumpappOutputBrokenException(String str) {
        super(str);
    }

    public DumpappOutputBrokenException(String str, Throwable th) {
        super(str, th);
    }

    public DumpappOutputBrokenException(Throwable th) {
        super(th);
    }
}

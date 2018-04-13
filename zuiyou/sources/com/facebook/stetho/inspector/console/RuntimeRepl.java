package com.facebook.stetho.inspector.console;

public interface RuntimeRepl {
    Object evaluate(String str) throws Throwable;
}

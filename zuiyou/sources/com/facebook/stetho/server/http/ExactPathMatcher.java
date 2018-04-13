package com.facebook.stetho.server.http;

public class ExactPathMatcher implements PathMatcher {
    private final String mPath;

    public ExactPathMatcher(String str) {
        this.mPath = str;
    }

    public boolean match(String str) {
        return this.mPath.equals(str);
    }
}

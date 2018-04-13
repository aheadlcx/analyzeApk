package com.facebook.stetho.inspector.network;

import android.annotation.SuppressLint;

@SuppressLint({"BadMethodUse-java.lang.String.length"})
class MimeMatcher$MimeMatcherRule {
    private final boolean mHasWildcard;
    private final String mMatchPrefix;
    private final T mResultIfMatched;
    final /* synthetic */ MimeMatcher this$0;

    public MimeMatcher$MimeMatcherRule(MimeMatcher mimeMatcher, String str, T t) {
        this.this$0 = mimeMatcher;
        if (str.endsWith("*")) {
            this.mHasWildcard = true;
            this.mMatchPrefix = str.substring(0, str.length() - 1);
        } else {
            this.mHasWildcard = false;
            this.mMatchPrefix = str;
        }
        if (this.mMatchPrefix.contains("*")) {
            throw new IllegalArgumentException("Multiple wildcards present in rule expression " + str);
        }
        this.mResultIfMatched = t;
    }

    public boolean match(String str) {
        if (!str.startsWith(this.mMatchPrefix)) {
            return false;
        }
        if (this.mHasWildcard || str.length() == this.mMatchPrefix.length()) {
            return true;
        }
        return false;
    }

    public T getResultIfMatched() {
        return this.mResultIfMatched;
    }
}

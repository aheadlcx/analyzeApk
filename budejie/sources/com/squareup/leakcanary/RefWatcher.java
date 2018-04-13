package com.squareup.leakcanary;

public final class RefWatcher {
    public static final RefWatcher DISABLED = new RefWatcher();

    private RefWatcher() {
    }

    public void watch(Object obj) {
    }

    public void watch(Object obj, String str) {
    }
}

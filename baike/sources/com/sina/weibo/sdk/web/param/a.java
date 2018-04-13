package com.sina.weibo.sdk.web.param;

import com.sina.weibo.sdk.web.WebRequestType;

/* synthetic */ class a {
    static final /* synthetic */ int[] a = new int[WebRequestType.values().length];

    static {
        try {
            a[WebRequestType.DEFAULT.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[WebRequestType.SHARE.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[WebRequestType.AUTH.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
    }
}

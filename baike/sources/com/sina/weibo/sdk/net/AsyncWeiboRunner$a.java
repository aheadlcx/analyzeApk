package com.sina.weibo.sdk.net;

import com.sina.weibo.sdk.exception.WeiboException;

class AsyncWeiboRunner$a<T> {
    private T a;
    private WeiboException b;

    public T getResult() {
        return this.a;
    }

    public WeiboException getError() {
        return this.b;
    }

    public AsyncWeiboRunner$a(T t) {
        this.a = t;
    }

    public AsyncWeiboRunner$a(WeiboException weiboException) {
        this.b = weiboException;
    }
}

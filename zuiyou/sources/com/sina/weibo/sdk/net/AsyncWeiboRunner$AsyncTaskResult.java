package com.sina.weibo.sdk.net;

import com.sina.weibo.sdk.exception.WeiboException;

class AsyncWeiboRunner$AsyncTaskResult<T> {
    private WeiboException error;
    private T result;

    public T getResult() {
        return this.result;
    }

    public WeiboException getError() {
        return this.error;
    }

    public AsyncWeiboRunner$AsyncTaskResult(T t) {
        this.result = t;
    }

    public AsyncWeiboRunner$AsyncTaskResult(WeiboException weiboException) {
        this.error = weiboException;
    }
}

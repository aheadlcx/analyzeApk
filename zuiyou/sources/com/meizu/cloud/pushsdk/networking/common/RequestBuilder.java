package com.meizu.cloud.pushsdk.networking.common;

import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public interface RequestBuilder {
    RequestBuilder addHeaders(String str, String str2);

    RequestBuilder addHeaders(HashMap<String, String> hashMap);

    RequestBuilder addPathParameter(String str, String str2);

    RequestBuilder addQueryParameter(String str, String str2);

    RequestBuilder addQueryParameter(HashMap<String, String> hashMap);

    RequestBuilder doNotCacheResponse();

    RequestBuilder getResponseOnlyFromNetwork();

    RequestBuilder getResponseOnlyIfCached();

    RequestBuilder setExecutor(Executor executor);

    RequestBuilder setMaxAgeCacheControl(int i, TimeUnit timeUnit);

    RequestBuilder setMaxStaleCacheControl(int i, TimeUnit timeUnit);

    RequestBuilder setPriority(Priority priority);

    RequestBuilder setTag(Object obj);

    RequestBuilder setUserAgent(String str);
}

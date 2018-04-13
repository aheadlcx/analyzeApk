package com.meizu.cloud.pushsdk.networking.interfaces;

import com.meizu.cloud.pushsdk.networking.error.ANError;
import com.meizu.cloud.pushsdk.networking.http.Response;

public interface OkHttpResponseAndParsedRequestListener<T> {
    void onError(ANError aNError);

    void onResponse(Response response, T t);
}

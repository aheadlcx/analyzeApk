package com.meizu.cloud.pushsdk.networking.interfaces;

import com.meizu.cloud.pushsdk.networking.error.ANError;

public interface ParsedRequestListener<T> {
    void onError(ANError aNError);

    void onResponse(T t);
}

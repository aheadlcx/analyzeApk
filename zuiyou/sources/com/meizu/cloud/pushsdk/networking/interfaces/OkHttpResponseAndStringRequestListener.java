package com.meizu.cloud.pushsdk.networking.interfaces;

import com.meizu.cloud.pushsdk.networking.error.ANError;
import com.meizu.cloud.pushsdk.networking.http.Response;

public interface OkHttpResponseAndStringRequestListener {
    void onError(ANError aNError);

    void onResponse(Response response, String str);
}

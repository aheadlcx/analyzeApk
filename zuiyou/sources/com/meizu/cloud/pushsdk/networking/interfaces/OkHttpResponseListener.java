package com.meizu.cloud.pushsdk.networking.interfaces;

import com.meizu.cloud.pushsdk.networking.error.ANError;
import com.meizu.cloud.pushsdk.networking.http.Response;

public interface OkHttpResponseListener {
    void onError(ANError aNError);

    void onResponse(Response response);
}

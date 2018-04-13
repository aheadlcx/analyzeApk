package com.meizu.cloud.pushsdk.networking.interfaces;

import com.meizu.cloud.pushsdk.networking.error.ANError;

public interface StringRequestListener {
    void onError(ANError aNError);

    void onResponse(String str);
}

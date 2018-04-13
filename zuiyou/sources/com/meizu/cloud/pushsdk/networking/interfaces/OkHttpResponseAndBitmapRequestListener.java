package com.meizu.cloud.pushsdk.networking.interfaces;

import android.graphics.Bitmap;
import com.meizu.cloud.pushsdk.networking.error.ANError;
import com.meizu.cloud.pushsdk.networking.http.Response;

public interface OkHttpResponseAndBitmapRequestListener {
    void onError(ANError aNError);

    void onResponse(Response response, Bitmap bitmap);
}

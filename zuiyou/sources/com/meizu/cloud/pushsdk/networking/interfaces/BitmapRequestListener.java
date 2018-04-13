package com.meizu.cloud.pushsdk.networking.interfaces;

import android.graphics.Bitmap;
import com.meizu.cloud.pushsdk.networking.error.ANError;

public interface BitmapRequestListener {
    void onError(ANError aNError);

    void onResponse(Bitmap bitmap);
}

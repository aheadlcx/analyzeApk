package com.yalantis.ucrop.callback;

import android.net.Uri;
import android.support.annotation.NonNull;

public interface BitmapCropCallback {
    void onBitmapCropped(@NonNull Uri uri, int i, int i2, int i3, int i4);

    void onCropFailure(@NonNull Throwable th);
}

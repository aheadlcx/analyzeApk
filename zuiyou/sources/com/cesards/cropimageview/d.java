package com.cesards.cropimageview;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;

public class d {
    public c a(@NonNull CropImageView cropImageView) {
        return (VERSION.SDK_INT < 18 ? 1 : null) != null ? new e(cropImageView) : new a(cropImageView);
    }
}

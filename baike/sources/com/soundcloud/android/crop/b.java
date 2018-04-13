package com.soundcloud.android.crop;

import android.view.View;
import android.view.View.OnClickListener;

class b implements OnClickListener {
    final /* synthetic */ CropImageActivity a;

    b(CropImageActivity cropImageActivity) {
        this.a = cropImageActivity;
    }

    public void onClick(View view) {
        this.a.setResult(0);
        this.a.finish();
    }
}

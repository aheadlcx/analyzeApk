package com.androidexlib.widget.asyncimage;

import android.view.View;
import com.nostra13.universalimageloader.core.d.d;

class AsyncImageView$2 implements d {
    final /* synthetic */ AsyncImageView a;

    AsyncImageView$2(AsyncImageView asyncImageView) {
        this.a = asyncImageView;
    }

    public void onProgressUpdate(String str, View view, int i, int i2) {
        AsyncImageView.a(this.a).b(str, (int) ((((float) i) / ((float) i2)) * 100.0f));
    }
}

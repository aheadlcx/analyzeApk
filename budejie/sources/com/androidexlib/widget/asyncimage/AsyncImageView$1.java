package com.androidexlib.widget.asyncimage;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import com.bdj.picture.edit.util.g;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.d.a;
import pl.droidsonroids.gif.GifDrawable;

class AsyncImageView$1 implements a {
    final /* synthetic */ AsyncImageView a;

    AsyncImageView$1(AsyncImageView asyncImageView) {
        this.a = asyncImageView;
    }

    public void onLoadingStarted(String str, View view) {
        AsyncImageView.a(this.a).a(str, 0);
    }

    public void onLoadingFailed(String str, View view, FailReason failReason) {
        AsyncImageView.a(this.a).a(str, false);
    }

    public void onLoadingComplete(String str, View view, Bitmap bitmap) {
    }

    public void onLoadingComplete(String str, View view, Bitmap bitmap, GifDrawable gifDrawable) {
        Bitmap a = AsyncImageView.a(this.a).a(str, bitmap);
        AsyncImageView.a(this.a).b(str, true);
        g.a().onLoadingComplete(str, view, bitmap, gifDrawable);
        if (a != null && a != bitmap) {
            Log.d("PostImageRow", "AsyncImageView.this.setImageBitmap(bitMap)");
            this.a.setImageBitmap(a);
        }
    }

    public void onLoadingCancelled(String str, View view) {
    }
}

package com.ak.android.other.news;

import android.app.Activity;
import android.view.View;
import com.ak.android.engine.navvideo.NativeVideoAd;

public interface Nv extends NativeVideoAd {
    void onNvAdClick(Activity activity, View view, int i, ActCallBack actCallBack);

    void onNvAdShowed(View view, int i);
}

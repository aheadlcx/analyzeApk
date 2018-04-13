package com.ak.android.other.news;

import android.app.Activity;
import android.view.View;
import com.ak.android.engine.nav.NativeAd;

public interface Na extends NativeAd {
    void onNaAdClick(Activity activity, View view, int i, ActCallBack actCallBack);

    void onNaAdShowed(View view, int i);
}

package com.ak.android.base.landingpage;

import android.graphics.Bitmap;

public interface ILandingPageListener {
    void onDownloadStart(String str);

    void onPageExit();

    void onPageFinished(String str);

    void onPageStarted(String str, Bitmap bitmap);

    void onReceivedError(int i, String str, String str2);

    boolean shouldOverrideUrlLoading(String str);
}

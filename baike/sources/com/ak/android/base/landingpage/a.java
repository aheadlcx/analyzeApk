package com.ak.android.base.landingpage;

import android.graphics.Bitmap;
import com.ak.android.bridge.DynamicObject;
import com.ak.android.bridge.d;

public final class a implements ILandingPageListener {
    private final DynamicObject a;

    public a(DynamicObject dynamicObject) {
        this.a = dynamicObject;
    }

    public final void onDownloadStart(String str) {
        if (this.a != null) {
            this.a.invoke(d.u, str);
        }
    }

    public final boolean shouldOverrideUrlLoading(String str) {
        if (this.a == null) {
            return false;
        }
        return ((Boolean) this.a.invoke(d.p, str)).booleanValue();
    }

    public final void onPageFinished(String str) {
        if (this.a != null) {
            this.a.invoke(d.q, str);
        }
    }

    public final void onPageStarted(String str, Bitmap bitmap) {
        if (this.a != null) {
            this.a.invoke(d.r, str, bitmap);
        }
    }

    public final void onReceivedError(int i, String str, String str2) {
        if (this.a != null) {
            this.a.invoke(d.s, Integer.valueOf(i), str, str2);
        }
    }

    public final void onPageExit() {
        if (this.a != null) {
            this.a.invoke(d.t, new Object[0]);
        }
    }
}

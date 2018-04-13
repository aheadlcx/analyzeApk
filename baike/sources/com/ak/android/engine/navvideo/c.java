package com.ak.android.engine.navvideo;

import android.app.Activity;
import android.view.View;
import com.ak.android.bridge.DynamicObject;
import com.ak.android.engine.navbase.d;
import com.ak.android.other.news.ActCallBack;
import com.ak.android.other.news.Nv;
import com.ak.android.other.news.a;

public final class c extends d implements Nv {
    public c(DynamicObject dynamicObject) {
        super(dynamicObject);
    }

    public final boolean hasVideo() {
        if (this.a != null) {
            return ((Boolean) this.a.invoke(com.ak.android.bridge.d.T, new Object[0])).booleanValue();
        }
        return false;
    }

    public final void onVideoChanged(int i, int i2) {
        if (this.a != null) {
            this.a.invoke(com.ak.android.bridge.d.U, Integer.valueOf(i), Integer.valueOf(i2));
        }
    }

    public final void onNvAdClick(Activity activity, View view, int i, ActCallBack actCallBack) {
        if (this.a != null) {
            this.a.invoke(com.ak.android.bridge.d.N, activity, view, Integer.valueOf(i), new a(actCallBack));
        }
    }

    public final void onNvAdShowed(View view, int i) {
        if (this.a != null) {
            this.a.invoke(com.ak.android.bridge.d.P, view, Integer.valueOf(i));
        }
    }
}

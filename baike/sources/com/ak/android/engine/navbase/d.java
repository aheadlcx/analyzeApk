package com.ak.android.engine.navbase;

import android.app.Activity;
import android.view.View;
import com.ak.android.bridge.DynamicObject;
import com.ak.android.other.news.ActCallBack;
import com.ak.android.other.news.Na;
import com.ak.android.other.news.a;
import org.json.JSONObject;

public class d implements Na {
    protected final DynamicObject a;

    public d(DynamicObject dynamicObject) {
        this.a = dynamicObject;
    }

    public void setAdListener(NativeAdListener nativeAdListener) {
        if (this.a != null) {
            this.a.invoke(com.ak.android.bridge.d.L, new b(nativeAdListener));
        }
    }

    public JSONObject getContent() {
        if (this.a != null) {
            return (JSONObject) this.a.invoke(com.ak.android.bridge.d.E, new Object[0]);
        }
        return new JSONObject();
    }

    public void onAdClick(Activity activity, View view) {
        if (this.a != null) {
            this.a.invoke(com.ak.android.bridge.d.F, activity, view);
        }
    }

    public void onAdShowed(View view) {
        if (this.a != null) {
            this.a.invoke(com.ak.android.bridge.d.G, view);
        }
    }

    public void onAdClosed() {
        if (this.a != null) {
            this.a.invoke(com.ak.android.bridge.d.H, new Object[0]);
        }
    }

    public int getActionType() {
        if (this.a != null) {
            return ((Integer) this.a.invoke(com.ak.android.bridge.d.I, new Object[0])).intValue();
        }
        return -1;
    }

    public int getAPPStatus() {
        if (this.a != null) {
            return ((Integer) this.a.invoke(com.ak.android.bridge.d.J, new Object[0])).intValue();
        }
        return -1;
    }

    public int getProgress() {
        if (this.a != null) {
            return ((Integer) this.a.invoke(com.ak.android.bridge.d.K, new Object[0])).intValue();
        }
        return 0;
    }

    public void onNaAdClick(Activity activity, View view, int i, ActCallBack actCallBack) {
        if (this.a != null) {
            this.a.invoke(com.ak.android.bridge.d.M, activity, view, Integer.valueOf(i), new a(actCallBack));
        }
    }

    public void onNaAdShowed(View view, int i) {
        if (this.a != null) {
            this.a.invoke(com.ak.android.bridge.d.O, view, Integer.valueOf(i));
        }
    }

    public String getAdSpaceId() {
        if (this.a != null) {
            return (String) this.a.invoke(com.ak.android.bridge.d.Q, new Object[0]);
        }
        return "";
    }

    public JSONObject getAPPInfo() {
        if (this.a != null) {
            return (JSONObject) this.a.invoke(com.ak.android.bridge.d.R, new Object[0]);
        }
        return new JSONObject();
    }
}

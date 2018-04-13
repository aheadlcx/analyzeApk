package com.sprite.ads.popup;

import android.content.Context;
import android.content.SharedPreferences;

public class PopupAdCache {
    private static final String PROMPTED = "prompted";
    Context mContext;
    SharedPreferences preferences;

    public PopupAdCache(Context context) {
        this.mContext = context;
        this.preferences = context.getSharedPreferences("popup_cache", 0);
    }

    public boolean getPrompted(String str) {
        return this.preferences.getBoolean(str, false);
    }

    public void setPrompted(String str, boolean z) {
        this.preferences.edit().putBoolean(str, z).commit();
    }
}

package com.sprite.ads.internal.a;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.MapBuilder;
import com.sprite.ads.SpriteAD;

public class d {
    public static void a(String str, String str2) {
        a(str, str2, null);
    }

    public static void a(String str, String str2, String str3) {
        EasyTracker.getInstance(SpriteAD.getApplicationContext()).send(MapBuilder.createEvent(str, str2, str3, null).build());
    }
}

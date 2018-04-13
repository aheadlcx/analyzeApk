package com.ak.android.player;

import android.app.Activity;
import com.ak.android.bridge.IBridge;
import com.ak.android.bridge.c;
import com.ak.android.engine.navvideo.NativeVideoAd;
import java.util.ArrayList;
import java.util.Iterator;

public final class AKPlayer {
    private static IBridge bridge;

    public static VideoAdPlayer getVideoAdPlayer(Activity activity, NativeVideoAd nativeVideoAd, boolean z) {
        if (bridge == null) {
            bridge = c.a(activity.getApplication());
        }
        if (bridge != null) {
            return (VideoAdPlayer) bridge.getVideoAdPlayer(activity, nativeVideoAd, z);
        }
        return null;
    }

    public static ArrayList<VideoAdPlayer> getVideoAdPlayer(Activity activity, ArrayList<NativeVideoAd> arrayList, boolean z) {
        ArrayList<VideoAdPlayer> arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(getVideoAdPlayer(activity, (NativeVideoAd) it.next(), z));
        }
        return arrayList2;
    }
}

package com.ak.android.engine.navvideo;

import com.ak.android.bridge.DynamicObject;
import java.util.ArrayList;
import java.util.Iterator;

public class NavV {
    public static ArrayList<NativeVideoAd> getNativeVideoAds(Object obj) {
        ArrayList<NativeVideoAd> arrayList = new ArrayList();
        Iterator it = ((ArrayList) obj).iterator();
        while (it.hasNext()) {
            arrayList.add(new c((DynamicObject) it.next()));
        }
        return arrayList;
    }
}

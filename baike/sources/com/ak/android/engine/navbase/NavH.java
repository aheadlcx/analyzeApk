package com.ak.android.engine.navbase;

import com.ak.android.bridge.DynamicObject;
import com.ak.android.engine.nav.NativeAd;
import java.util.ArrayList;
import java.util.Iterator;

public class NavH {
    public static ArrayList<NativeAd> getNativeAds(Object obj) {
        ArrayList<NativeAd> arrayList = new ArrayList();
        Iterator it = ((ArrayList) obj).iterator();
        while (it.hasNext()) {
            arrayList.add(new d((DynamicObject) it.next()));
        }
        return arrayList;
    }
}

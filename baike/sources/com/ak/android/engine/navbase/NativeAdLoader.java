package com.ak.android.engine.navbase;

import java.util.HashMap;
import java.util.HashSet;

public interface NativeAdLoader {
    void destroy();

    void loadAds();

    void setExtras(HashMap<String, String> hashMap);

    void setKeyWords(HashSet<String> hashSet);
}

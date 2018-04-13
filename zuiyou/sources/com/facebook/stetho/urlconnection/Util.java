package com.facebook.stetho.urlconnection;

import android.util.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class Util {
    Util() {
    }

    public static ArrayList<Pair<String, String>> convertHeaders(Map<String, List<String>> map) {
        ArrayList<Pair<String, String>> arrayList = new ArrayList();
        for (Entry entry : map.entrySet()) {
            for (String str : (List) entry.getValue()) {
                if (entry.getKey() != null) {
                    arrayList.add(Pair.create(entry.getKey(), str));
                }
            }
        }
        return arrayList;
    }
}

package com.alibaba.baichuan.android.trade.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class j {

    public static class a implements Serializable {
        public String a;
        public Boolean b;
        public Boolean c;
        public String[] d;
        public String[] e;
    }

    private static void a(String str, String str2, Set set, Map map) {
        Set<String> set2 = (Set) map.get(str2);
        if (set2 != null && !set2.contains(str)) {
            for (String str3 : set2) {
                if (set.add(str3)) {
                    a(str, str3, set, map);
                }
            }
        }
    }

    public static void a(a[] aVarArr) {
        Entry entry;
        Map hashMap = new HashMap();
        Map hashMap2 = new HashMap();
        for (a aVar : aVarArr) {
            if (aVar.d != null) {
                for (Object obj : aVar.d) {
                    Set set = (Set) hashMap.get(obj);
                    if (set == null) {
                        set = new HashSet();
                        hashMap.put(obj, set);
                    }
                    set.add(aVar.a);
                }
            }
            Collection collection = (Set) hashMap.get(aVar.a);
            if (collection == null) {
                collection = new HashSet();
                hashMap.put(aVar.a, collection);
            }
            if (aVar.e != null) {
                Collections.addAll(collection, aVar.e);
            }
            hashMap2.put(aVar.a, aVar);
        }
        Object hashSet = new HashSet();
        for (a aVar2 : aVarArr) {
            a aVar22;
            a(aVar22.a, aVar22.a, hashSet, hashMap);
            ((Set) hashMap.get(aVar22.a)).addAll(hashSet);
            hashSet.clear();
        }
        Collection hashSet2 = new HashSet();
        Object<String> hashSet3 = new HashSet();
        for (a aVar222 : aVarArr) {
            String str = aVar222.a;
            if (aVar222.b != null && aVar222.b.booleanValue()) {
                hashSet2.add(str);
                hashSet2.addAll((Set) hashMap.get(str));
            } else if (aVar222.c != null && aVar222.c.booleanValue()) {
                hashSet3.add(str);
                for (Entry entry2 : hashMap.entrySet()) {
                    if (((Set) entry2.getValue()).contains(str)) {
                        hashSet3.add(entry2.getKey());
                    }
                }
            }
        }
        for (Entry entry22 : hashMap.entrySet()) {
            if (!hashSet2.contains(entry22.getKey())) {
                ((Set) entry22.getValue()).addAll(hashSet2);
            }
        }
        Collection hashSet4 = new HashSet(hashMap.keySet());
        hashSet4.removeAll(hashSet3);
        for (String str2 : hashSet3) {
            ((Set) hashMap.get(str2)).addAll(hashSet4);
        }
        hashSet = new HashSet();
        Set hashSet5 = new HashSet();
        List<String> arrayList = new ArrayList(aVarArr.length);
        while (hashMap.size() > 0) {
            Iterator it = hashMap.entrySet().iterator();
            while (it.hasNext()) {
                entry22 = (Entry) it.next();
                ((Set) entry22.getValue()).removeAll(hashSet5);
                ((Set) entry22.getValue()).retainAll(hashMap.keySet());
                if (((Set) entry22.getValue()).size() == 0) {
                    hashSet.add(entry22.getKey());
                    arrayList.add(entry22.getKey());
                    it.remove();
                }
            }
            if (hashSet.size() == 0) {
                throw new IllegalStateException("Circular found for " + hashMap);
            }
            hashSet5.clear();
            hashSet5.addAll(hashSet);
            hashSet.clear();
        }
        int i = 0;
        for (String str22 : arrayList) {
            int i2;
            aVar222 = (a) hashMap2.get(str22);
            if (aVar222 != null) {
                int i3 = i + 1;
                aVarArr[i] = aVar222;
                i2 = i3;
            } else {
                i2 = i;
            }
            i = i2;
        }
    }
}

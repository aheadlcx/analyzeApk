package com.qiniu.android.dns.local;

import com.qiniu.android.dns.Domain;
import com.qiniu.android.dns.NetworkInfo;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

public final class Hosts {
    private final Hashtable<String, ArrayList<Value>> a = new Hashtable();

    public static class Value {
        public final String ip;
        public final int provider;

        public Value(String str, int i) {
            this.ip = str;
            this.provider = i;
        }

        public Value(String str) {
            this(str, 0);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !(obj instanceof Value)) {
                return false;
            }
            Value value = (Value) obj;
            if (this.ip.equals(value.ip) && this.provider == value.provider) {
                return true;
            }
            return false;
        }
    }

    public String[] query(Domain domain, NetworkInfo networkInfo) {
        ArrayList arrayList = (ArrayList) this.a.get(domain.domain);
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        if (arrayList.size() > 1) {
            Value value = (Value) arrayList.get(0);
            arrayList.remove(0);
            arrayList.add(value);
        }
        return toIps(a(arrayList, networkInfo));
    }

    private ArrayList<Value> a(ArrayList<Value> arrayList, NetworkInfo networkInfo) {
        ArrayList<Value> arrayList2 = new ArrayList();
        ArrayList<Value> arrayList3 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Value value = (Value) it.next();
            if (value.provider == 0) {
                arrayList2.add(value);
            }
            if (networkInfo.provider != 0 && value.provider == networkInfo.provider) {
                arrayList3.add(value);
            }
        }
        if (arrayList3.size() != 0) {
            return arrayList3;
        }
        return arrayList2;
    }

    public String[] toIps(ArrayList<Value> arrayList) {
        int size = arrayList.size();
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = ((Value) arrayList.get(i)).ip;
        }
        return strArr;
    }

    public Hosts put(String str, Value value) {
        ArrayList arrayList = (ArrayList) this.a.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        arrayList.add(value);
        this.a.put(str, arrayList);
        return this;
    }

    public Hosts put(String str, String str2) {
        put(str, new Value(str2));
        return this;
    }
}

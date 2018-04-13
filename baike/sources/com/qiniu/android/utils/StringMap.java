package com.qiniu.android.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class StringMap {
    private Map<String, Object> a;

    public interface Consumer {
        void accept(String str, Object obj);
    }

    public StringMap() {
        this(new HashMap());
    }

    public StringMap(Map<String, Object> map) {
        this.a = map;
    }

    public StringMap put(String str, Object obj) {
        this.a.put(str, obj);
        return this;
    }

    public StringMap putNotEmpty(String str, String str2) {
        if (!StringUtils.isNullOrEmpty(str2)) {
            this.a.put(str, str2);
        }
        return this;
    }

    public StringMap putNotNull(String str, Object obj) {
        if (obj != null) {
            this.a.put(str, obj);
        }
        return this;
    }

    public StringMap putWhen(String str, Object obj, boolean z) {
        if (z) {
            this.a.put(str, obj);
        }
        return this;
    }

    public StringMap putAll(Map<String, Object> map) {
        this.a.putAll(map);
        return this;
    }

    public StringMap putFileds(Map<String, String> map) {
        this.a.putAll(map);
        return this;
    }

    public StringMap putAll(StringMap stringMap) {
        this.a.putAll(stringMap.a);
        return this;
    }

    public void forEach(Consumer consumer) {
        for (Entry entry : this.a.entrySet()) {
            consumer.accept((String) entry.getKey(), entry.getValue());
        }
    }

    public int size() {
        return this.a.size();
    }

    public Map<String, Object> map() {
        return this.a;
    }

    public Object get(String str) {
        return this.a.get(str);
    }

    public String formString() {
        StringBuilder stringBuilder = new StringBuilder();
        forEach(new a(this, stringBuilder));
        return stringBuilder.toString();
    }
}

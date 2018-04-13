package com.squareup.wire.internal;

import com.squareup.wire.ProtoAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class Internal {
    private Internal() {
    }

    public static <T> List<T> newMutableList() {
        return new b(Collections.emptyList());
    }

    public static <K, V> Map<K, V> newMutableMap() {
        return new LinkedHashMap();
    }

    public static <T> List<T> copyOf(String str, List<T> list) {
        if (list == null) {
            throw new NullPointerException(str + " == null");
        } else if (list == Collections.emptyList() || (list instanceof a)) {
            return new b(list);
        } else {
            return new ArrayList(list);
        }
    }

    public static <K, V> Map<K, V> copyOf(String str, Map<K, V> map) {
        if (map != null) {
            return new LinkedHashMap(map);
        }
        throw new NullPointerException(str + " == null");
    }

    public static <T> List<T> immutableCopyOf(String str, List<T> list) {
        if (list == null) {
            throw new NullPointerException(str + " == null");
        }
        if (list instanceof b) {
            list = ((b) list).a;
        }
        if (list == Collections.emptyList() || (list instanceof a)) {
            return list;
        }
        a aVar = new a(list);
        if (!aVar.contains(null)) {
            return aVar;
        }
        throw new IllegalArgumentException(str + ".contains(null)");
    }

    public static <K, V> Map<K, V> immutableCopyOf(String str, Map<K, V> map) {
        if (map == null) {
            throw new NullPointerException(str + " == null");
        } else if (map.isEmpty()) {
            return Collections.emptyMap();
        } else {
            Map linkedHashMap = new LinkedHashMap(map);
            if (linkedHashMap.containsKey(null)) {
                throw new IllegalArgumentException(str + ".containsKey(null)");
            } else if (!linkedHashMap.containsValue(null)) {
                return Collections.unmodifiableMap(linkedHashMap);
            } else {
                throw new IllegalArgumentException(str + ".containsValue(null)");
            }
        }
    }

    public static <T> void redactElements(List<T> list, ProtoAdapter<T> protoAdapter) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            list.set(i, protoAdapter.redact(list.get(i)));
        }
    }

    public static <T> void redactElements(Map<?, T> map, ProtoAdapter<T> protoAdapter) {
        for (Entry entry : map.entrySet()) {
            entry.setValue(protoAdapter.redact(entry.getValue()));
        }
    }

    public static boolean equals(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static IllegalStateException missingRequiredFields(Object... objArr) {
        StringBuilder stringBuilder = new StringBuilder();
        int length = objArr.length;
        String str = "";
        for (int i = 0; i < length; i += 2) {
            if (objArr[i] == null) {
                if (stringBuilder.length() > 0) {
                    str = "s";
                }
                stringBuilder.append("\n  ");
                stringBuilder.append(objArr[i + 1]);
            }
        }
        throw new IllegalStateException("Required field" + str + " not set:" + stringBuilder);
    }

    public static void checkElementsNotNull(List<?> list) {
        if (list == null) {
            throw new NullPointerException("list == null");
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i) == null) {
                throw new NullPointerException("Element at index " + i + " is null");
            }
        }
    }

    public static void checkElementsNotNull(Map<?, ?> map) {
        if (map == null) {
            throw new NullPointerException("map == null");
        }
        for (Entry entry : map.entrySet()) {
            if (entry.getKey() == null) {
                throw new NullPointerException("map.containsKey(null)");
            } else if (entry.getValue() == null) {
                throw new NullPointerException("Value for key " + entry.getKey() + " is null");
            }
        }
    }

    public static int countNonNull(Object obj, Object obj2) {
        int i = 1;
        int i2 = obj != null ? 1 : 0;
        if (obj2 == null) {
            i = 0;
        }
        return i + i2;
    }

    public static int countNonNull(Object obj, Object obj2, Object obj3) {
        int i = 1;
        int i2 = (obj2 != null ? 1 : 0) + (obj != null ? 1 : 0);
        if (obj3 == null) {
            i = 0;
        }
        return i + i2;
    }

    public static int countNonNull(Object obj, Object obj2, Object obj3, Object obj4, Object... objArr) {
        int i;
        int i2 = 0;
        if (obj != null) {
            i = 1;
        } else {
            i = 0;
        }
        if (obj2 != null) {
            i++;
        }
        if (obj3 != null) {
            i++;
        }
        if (obj4 != null) {
            i++;
        }
        int length = objArr.length;
        while (i2 < length) {
            if (objArr[i2] != null) {
                i++;
            }
            i2++;
        }
        return i;
    }
}

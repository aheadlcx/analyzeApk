package com.google.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataLayer {
    static final String LIFETIME_KEY = "gtm.lifetime";
    static final String[] LIFETIME_KEY_COMPONENTS = LIFETIME_KEY.toString().split("\\.");
    private static final Pattern LIFETIME_PATTERN = Pattern.compile("(\\d+)\\s*([smhd]?)");
    static final int MAX_QUEUE_DEPTH = 500;
    public static final Object OBJECT_NOT_PRESENT = new Object();
    private final ConcurrentHashMap<Listener, Integer> mListeners;
    private final Map<Object, Object> mModel;
    private final PersistentStore mPersistentStore;
    private final CountDownLatch mPersistentStoreLoaded;
    private final ReentrantLock mPushLock;
    private final LinkedList<Map<Object, Object>> mUpdateQueue;

    interface Listener {
        void changed(Map<Object, Object> map);
    }

    interface PersistentStore {

        public interface Callback {
            void onKeyValuesLoaded(List<KeyValue> list);
        }

        void clearKeysWithPrefix(String str);

        void loadSaved(Callback callback);

        void saveKeyValues(List<KeyValue> list, long j);
    }

    static final class KeyValue {
        public final String mKey;
        public final Object mValue;

        KeyValue(String str, Object obj) {
            this.mKey = str;
            this.mValue = obj;
        }

        public String toString() {
            return "Key: " + this.mKey + " value: " + this.mValue.toString();
        }

        public int hashCode() {
            return Arrays.hashCode(new Integer[]{Integer.valueOf(this.mKey.hashCode()), Integer.valueOf(this.mValue.hashCode())});
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof KeyValue)) {
                return false;
            }
            KeyValue keyValue = (KeyValue) obj;
            if (this.mKey.equals(keyValue.mKey) && this.mValue.equals(keyValue.mValue)) {
                return true;
            }
            return false;
        }
    }

    @VisibleForTesting
    DataLayer() {
        this(new PersistentStore() {
            public void saveKeyValues(List<KeyValue> list, long j) {
            }

            public void loadSaved(Callback callback) {
                callback.onKeyValuesLoaded(new ArrayList());
            }

            public void clearKeysWithPrefix(String str) {
            }
        });
    }

    DataLayer(PersistentStore persistentStore) {
        this.mPersistentStore = persistentStore;
        this.mListeners = new ConcurrentHashMap();
        this.mModel = new HashMap();
        this.mPushLock = new ReentrantLock();
        this.mUpdateQueue = new LinkedList();
        this.mPersistentStoreLoaded = new CountDownLatch(1);
        loadSavedMaps();
    }

    public void push(Object obj, Object obj2) {
        push(expandKeyValue(obj, obj2));
    }

    public void push(Map<Object, Object> map) {
        try {
            this.mPersistentStoreLoaded.await();
        } catch (InterruptedException e) {
            Log.w("DataLayer.push: unexpected InterruptedException");
        }
        pushWithoutWaitingForSaved(map);
    }

    private void pushWithoutWaitingForSaved(Map<Object, Object> map) {
        this.mPushLock.lock();
        try {
            this.mUpdateQueue.offer(map);
            if (this.mPushLock.getHoldCount() == 1) {
                processQueuedUpdates();
            }
            savePersistentlyIfNeeded(map);
        } finally {
            this.mPushLock.unlock();
        }
    }

    private void loadSavedMaps() {
        this.mPersistentStore.loadSaved(new Callback() {
            public void onKeyValuesLoaded(List<KeyValue> list) {
                for (KeyValue keyValue : list) {
                    DataLayer.this.pushWithoutWaitingForSaved(DataLayer.this.expandKeyValue(keyValue.mKey, keyValue.mValue));
                }
                DataLayer.this.mPersistentStoreLoaded.countDown();
            }
        });
    }

    private void savePersistentlyIfNeeded(Map<Object, Object> map) {
        Long lifetimeValue = getLifetimeValue(map);
        if (lifetimeValue != null) {
            List flattenMap = flattenMap(map);
            flattenMap.remove(LIFETIME_KEY);
            this.mPersistentStore.saveKeyValues(flattenMap, lifetimeValue.longValue());
        }
    }

    private Long getLifetimeValue(Map<Object, Object> map) {
        Object lifetimeObject = getLifetimeObject(map);
        if (lifetimeObject == null) {
            return null;
        }
        return parseLifetime(lifetimeObject.toString());
    }

    private Object getLifetimeObject(Map<Object, Object> map) {
        String[] strArr = LIFETIME_KEY_COMPONENTS;
        int length = strArr.length;
        int i = 0;
        Object obj = map;
        while (i < length) {
            Object obj2 = strArr[i];
            if (!(obj instanceof Map)) {
                return null;
            }
            i++;
            Map<Object, Object> map2 = ((Map) obj).get(obj2);
        }
        return obj;
    }

    void clearPersistentKeysWithPrefix(String str) {
        push(str, null);
        this.mPersistentStore.clearKeysWithPrefix(str);
    }

    private List<KeyValue> flattenMap(Map<Object, Object> map) {
        Object arrayList = new ArrayList();
        flattenMapHelper(map, "", arrayList);
        return arrayList;
    }

    private void flattenMapHelper(Map<Object, Object> map, String str, Collection<KeyValue> collection) {
        for (Entry entry : map.entrySet()) {
            String str2 = str + (str.length() == 0 ? "" : ".") + entry.getKey();
            if (entry.getValue() instanceof Map) {
                flattenMapHelper((Map) entry.getValue(), str2, collection);
            } else if (!str2.equals(LIFETIME_KEY)) {
                collection.add(new KeyValue(str2, entry.getValue()));
            }
        }
    }

    @VisibleForTesting
    static Long parseLifetime(String str) {
        Matcher matcher = LIFETIME_PATTERN.matcher(str);
        if (matcher.matches()) {
            long parseLong;
            try {
                parseLong = Long.parseLong(matcher.group(1));
            } catch (NumberFormatException e) {
                Log.w("illegal number in _lifetime value: " + str);
                parseLong = 0;
            }
            if (parseLong <= 0) {
                Log.i("non-positive _lifetime: " + str);
                return null;
            }
            String group = matcher.group(2);
            if (group.length() == 0) {
                return Long.valueOf(parseLong);
            }
            switch (group.charAt(0)) {
                case 'd':
                    return Long.valueOf((((parseLong * 1000) * 60) * 60) * 24);
                case 'h':
                    return Long.valueOf(((parseLong * 1000) * 60) * 60);
                case 'm':
                    return Long.valueOf((parseLong * 1000) * 60);
                case 's':
                    return Long.valueOf(parseLong * 1000);
                default:
                    Log.w("unknown units in _lifetime: " + str);
                    return null;
            }
        }
        Log.i("unknown _lifetime: " + str);
        return null;
    }

    private void processQueuedUpdates() {
        int i = 0;
        while (true) {
            Map map = (Map) this.mUpdateQueue.poll();
            if (map != null) {
                processUpdate(map);
                int i2 = i + 1;
                if (i2 > 500) {
                    this.mUpdateQueue.clear();
                    throw new RuntimeException("Seems like an infinite loop of pushing to the data layer");
                }
                i = i2;
            } else {
                return;
            }
        }
    }

    private void processUpdate(Map<Object, Object> map) {
        synchronized (this.mModel) {
            for (Object next : map.keySet()) {
                mergeMap(expandKeyValue(next, map.get(next)), this.mModel);
            }
        }
        notifyListeners(map);
    }

    public Object get(String str) {
        synchronized (this.mModel) {
            Map map = this.mModel;
            String[] split = str.split("\\.");
            int length = split.length;
            Object obj = map;
            int i = 0;
            while (i < length) {
                Object obj2 = split[i];
                if (obj instanceof Map) {
                    obj2 = ((Map) obj).get(obj2);
                    if (obj2 == null) {
                        return null;
                    }
                    i++;
                    obj = obj2;
                } else {
                    return null;
                }
            }
            return obj;
        }
    }

    public static Map<Object, Object> mapOf(Object... objArr) {
        if (objArr.length % 2 != 0) {
            throw new IllegalArgumentException("expected even number of key-value pairs");
        }
        Map<Object, Object> hashMap = new HashMap();
        for (int i = 0; i < objArr.length; i += 2) {
            hashMap.put(objArr[i], objArr[i + 1]);
        }
        return hashMap;
    }

    public static List<Object> listOf(Object... objArr) {
        List<Object> arrayList = new ArrayList();
        for (Object add : objArr) {
            arrayList.add(add);
        }
        return arrayList;
    }

    void registerListener(Listener listener) {
        this.mListeners.put(listener, Integer.valueOf(0));
    }

    void unregisterListener(Listener listener) {
        this.mListeners.remove(listener);
    }

    private void notifyListeners(Map<Object, Object> map) {
        for (Listener changed : this.mListeners.keySet()) {
            changed.changed(map);
        }
    }

    Map<Object, Object> expandKeyValue(Object obj, Object obj2) {
        Map hashMap = new HashMap();
        String[] split = obj.toString().split("\\.");
        int i = 0;
        Map map = hashMap;
        while (i < split.length - 1) {
            HashMap hashMap2 = new HashMap();
            map.put(split[i], hashMap2);
            i++;
            Object obj3 = hashMap2;
        }
        map.put(split[split.length - 1], obj2);
        return hashMap;
    }

    @VisibleForTesting
    void mergeMap(Map<Object, Object> map, Map<Object, Object> map2) {
        for (Object next : map.keySet()) {
            Object obj = map.get(next);
            if (obj instanceof List) {
                if (!(map2.get(next) instanceof List)) {
                    map2.put(next, new ArrayList());
                }
                mergeList((List) obj, (List) map2.get(next));
            } else if (obj instanceof Map) {
                if (!(map2.get(next) instanceof Map)) {
                    map2.put(next, new HashMap());
                }
                mergeMap((Map) obj, (Map) map2.get(next));
            } else {
                map2.put(next, obj);
            }
        }
    }

    @VisibleForTesting
    void mergeList(List<Object> list, List<Object> list2) {
        while (list2.size() < list.size()) {
            list2.add(null);
        }
        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            if (obj instanceof List) {
                if (!(list2.get(i) instanceof List)) {
                    list2.set(i, new ArrayList());
                }
                mergeList((List) obj, (List) list2.get(i));
            } else if (obj instanceof Map) {
                if (!(list2.get(i) instanceof Map)) {
                    list2.set(i, new HashMap());
                }
                mergeMap((Map) obj, (Map) list2.get(i));
            } else if (obj != OBJECT_NOT_PRESENT) {
                list2.set(i, obj);
            }
        }
    }
}

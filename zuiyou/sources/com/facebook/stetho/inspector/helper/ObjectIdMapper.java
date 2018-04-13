package com.facebook.stetho.inspector.helper;

import android.util.SparseArray;
import java.util.IdentityHashMap;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public class ObjectIdMapper {
    @GuardedBy
    private SparseArray<Object> mIdToObjectMap = new SparseArray();
    @GuardedBy
    private int mNextId = 1;
    @GuardedBy
    private final Map<Object, Integer> mObjectToIdMap = new IdentityHashMap();
    protected final Object mSync = new Object();

    public void clear() {
        SparseArray sparseArray;
        synchronized (this.mSync) {
            sparseArray = this.mIdToObjectMap;
            this.mObjectToIdMap.clear();
            this.mIdToObjectMap = new SparseArray();
        }
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            onUnmapped(sparseArray.valueAt(i), sparseArray.keyAt(i));
        }
    }

    public boolean containsId(int i) {
        boolean z;
        synchronized (this.mSync) {
            z = this.mIdToObjectMap.get(i) != null;
        }
        return z;
    }

    public boolean containsObject(Object obj) {
        boolean containsKey;
        synchronized (this.mSync) {
            containsKey = this.mObjectToIdMap.containsKey(obj);
        }
        return containsKey;
    }

    @Nullable
    public Object getObjectForId(int i) {
        Object obj;
        synchronized (this.mSync) {
            obj = this.mIdToObjectMap.get(i);
        }
        return obj;
    }

    @Nullable
    public Integer getIdForObject(Object obj) {
        Integer num;
        synchronized (this.mSync) {
            num = (Integer) this.mObjectToIdMap.get(obj);
        }
        return num;
    }

    public int putObject(Object obj) {
        synchronized (this.mSync) {
            Integer num = (Integer) this.mObjectToIdMap.get(obj);
            if (num != null) {
                int intValue = num.intValue();
                return intValue;
            }
            intValue = this.mNextId;
            this.mNextId = intValue + 1;
            num = Integer.valueOf(intValue);
            this.mObjectToIdMap.put(obj, num);
            this.mIdToObjectMap.put(num.intValue(), obj);
            onMapped(obj, num.intValue());
            return num.intValue();
        }
    }

    @Nullable
    public Object removeObjectById(int i) {
        Object obj;
        synchronized (this.mSync) {
            obj = this.mIdToObjectMap.get(i);
            if (obj == null) {
                obj = null;
            } else {
                this.mIdToObjectMap.remove(i);
                this.mObjectToIdMap.remove(obj);
                onUnmapped(obj, i);
            }
        }
        return obj;
    }

    @Nullable
    public Integer removeObject(Object obj) {
        Integer num;
        synchronized (this.mSync) {
            num = (Integer) this.mObjectToIdMap.remove(obj);
            if (num == null) {
                num = null;
            } else {
                this.mIdToObjectMap.remove(num.intValue());
                onUnmapped(obj, num.intValue());
            }
        }
        return num;
    }

    public int size() {
        int size;
        synchronized (this.mSync) {
            size = this.mObjectToIdMap.size();
        }
        return size;
    }

    protected void onMapped(Object obj, int i) {
    }

    protected void onUnmapped(Object obj, int i) {
    }
}

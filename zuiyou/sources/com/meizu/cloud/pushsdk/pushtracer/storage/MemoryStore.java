package com.meizu.cloud.pushsdk.pushtracer.storage;

import com.meizu.cloud.pushsdk.pushtracer.dataload.DataLoad;
import com.meizu.cloud.pushsdk.pushtracer.dataload.TrackerDataload;
import com.meizu.cloud.pushsdk.pushtracer.emitter.EmittableEvents;
import com.meizu.cloud.pushsdk.pushtracer.utils.Logger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class MemoryStore implements Store {
    private static final String TAG = "MemoryStore";
    private AtomicLong atomicLong = new AtomicLong(0);
    private List<Long> dataKeyList = new CopyOnWriteArrayList();
    private int sendLimit;
    private Map<Long, byte[]> storeMap = new ConcurrentHashMap();

    public MemoryStore(int i) {
        this.sendLimit = i;
    }

    public void add(DataLoad dataLoad) {
        insertEvent(dataLoad);
    }

    public boolean isOpen() {
        return true;
    }

    public void close() {
        this.storeMap.clear();
        this.atomicLong.set(0);
        this.dataKeyList.clear();
    }

    public boolean removeEvent(long j) {
        return this.dataKeyList.remove(Long.valueOf(j)) && this.storeMap.remove(Long.valueOf(j)) != null;
    }

    public boolean removeAllEvents() {
        this.storeMap.clear();
        this.dataKeyList.clear();
        return true;
    }

    public long getSize() {
        return (long) this.dataKeyList.size();
    }

    public EmittableEvents getEmittableEvents() {
        int i;
        LinkedList linkedList = new LinkedList();
        ArrayList arrayList = new ArrayList();
        int size = (int) getSize();
        if (size > this.sendLimit) {
            i = this.sendLimit;
        } else {
            i = size;
        }
        for (int i2 = 0; i2 < i; i2++) {
            Object obj = this.dataKeyList.get(i2);
            if (obj != null) {
                TrackerDataload trackerDataload = new TrackerDataload();
                trackerDataload.addMap(EventStore.deserializer((byte[]) this.storeMap.get(obj)));
                Logger.i(TAG, " current key " + obj + " payload " + trackerDataload, new Object[0]);
                linkedList.add((Long) obj);
                arrayList.add(trackerDataload);
            }
        }
        return new EmittableEvents(arrayList, linkedList);
    }

    public long insertEvent(DataLoad dataLoad) {
        Object serialize = EventStore.serialize(dataLoad.getMap());
        long andIncrement = this.atomicLong.getAndIncrement();
        this.dataKeyList.add(Long.valueOf(andIncrement));
        this.storeMap.put(Long.valueOf(andIncrement), serialize);
        return andIncrement;
    }

    public Map<String, Object> getEvent(long j) {
        byte[] bArr = (byte[]) this.storeMap.get(Long.valueOf(j));
        if (bArr != null) {
            return EventStore.deserializer(bArr);
        }
        return null;
    }
}

package com.meizu.cloud.pushsdk.pushtracer.storage;

import com.meizu.cloud.pushsdk.pushtracer.dataload.DataLoad;
import com.meizu.cloud.pushsdk.pushtracer.emitter.EmittableEvents;

public interface Store {
    void add(DataLoad dataLoad);

    void close();

    EmittableEvents getEmittableEvents();

    long getSize();

    boolean isOpen();

    boolean removeAllEvents();

    boolean removeEvent(long j);
}

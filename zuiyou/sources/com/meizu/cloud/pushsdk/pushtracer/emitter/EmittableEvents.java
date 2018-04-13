package com.meizu.cloud.pushsdk.pushtracer.emitter;

import com.meizu.cloud.pushsdk.pushtracer.dataload.DataLoad;
import java.util.ArrayList;
import java.util.LinkedList;

public class EmittableEvents {
    private final LinkedList<Long> eventIds;
    private final ArrayList<DataLoad> events;

    public EmittableEvents(ArrayList<DataLoad> arrayList, LinkedList<Long> linkedList) {
        this.events = arrayList;
        this.eventIds = linkedList;
    }

    public ArrayList<DataLoad> getEvents() {
        return this.events;
    }

    public LinkedList<Long> getEventIds() {
        return this.eventIds;
    }
}

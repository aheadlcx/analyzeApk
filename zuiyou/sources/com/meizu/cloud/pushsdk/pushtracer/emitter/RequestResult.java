package com.meizu.cloud.pushsdk.pushtracer.emitter;

import java.util.LinkedList;

public class RequestResult {
    private final LinkedList<Long> eventIds;
    private final boolean success;

    public RequestResult(boolean z, LinkedList<Long> linkedList) {
        this.success = z;
        this.eventIds = linkedList;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public LinkedList<Long> getEventIds() {
        return this.eventIds;
    }
}

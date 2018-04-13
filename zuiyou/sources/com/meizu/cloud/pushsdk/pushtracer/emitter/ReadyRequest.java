package com.meizu.cloud.pushsdk.pushtracer.emitter;

import com.meizu.cloud.pushsdk.networking.http.Request;
import java.util.LinkedList;

public class ReadyRequest {
    private final LinkedList<Long> ids;
    private final boolean oversize;
    private final Request request;

    public ReadyRequest(boolean z, Request request, LinkedList<Long> linkedList) {
        this.oversize = z;
        this.request = request;
        this.ids = linkedList;
    }

    public Request getRequest() {
        return this.request;
    }

    public LinkedList<Long> getEventIds() {
        return this.ids;
    }

    public boolean isOversize() {
        return this.oversize;
    }
}

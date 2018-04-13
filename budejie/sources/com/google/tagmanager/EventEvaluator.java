package com.google.tagmanager;

import com.google.tagmanager.ResourceUtil.ExpandedResource;

class EventEvaluator {
    private final ExpandedResource mResource;
    private final Runtime mRuntime;

    public EventEvaluator(Runtime runtime, ExpandedResource expandedResource) {
        if (runtime == null) {
            throw new NullPointerException("runtime cannot be null");
        }
        this.mRuntime = runtime;
        if (expandedResource != runtime.getResource()) {
            throw new IllegalArgumentException("resource must be the same as the resource in runtime");
        }
        this.mResource = runtime.getResource();
    }

    void evaluateEvent(String str) {
        throw new UnsupportedOperationException("this code not yet written");
    }
}

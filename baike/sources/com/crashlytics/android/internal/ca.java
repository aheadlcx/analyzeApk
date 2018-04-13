package com.crashlytics.android.internal;

import java.util.concurrent.ConcurrentLinkedQueue;

final class ca extends ThreadLocal<ConcurrentLinkedQueue<cc>> {
    ca(b bVar) {
    }

    protected final /* synthetic */ Object initialValue() {
        return new ConcurrentLinkedQueue();
    }
}

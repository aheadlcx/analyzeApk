package com.facebook.stetho.inspector.network;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class AsyncPrettyPrinterRegistry {
    private final Map<String, AsyncPrettyPrinterFactory> mRegistry = new HashMap();

    public synchronized void register(String str, AsyncPrettyPrinterFactory asyncPrettyPrinterFactory) {
        this.mRegistry.put(str, asyncPrettyPrinterFactory);
    }

    @Nullable
    public synchronized AsyncPrettyPrinterFactory lookup(String str) {
        return (AsyncPrettyPrinterFactory) this.mRegistry.get(str);
    }

    public synchronized boolean unregister(String str) {
        return this.mRegistry.remove(str) != null;
    }
}

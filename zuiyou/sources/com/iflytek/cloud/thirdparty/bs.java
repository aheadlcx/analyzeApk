package com.iflytek.cloud.thirdparty;

import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class bs {
    private HashMap<String, ConcurrentLinkedQueue<byte[]>> a = new HashMap();

    public void a() {
        for (String str : this.a.keySet()) {
            bv.a((ConcurrentLinkedQueue) this.a.get(str), str);
        }
    }

    public void a(String str, byte[] bArr) {
        if (this.a.containsKey(str)) {
            ((ConcurrentLinkedQueue) this.a.get(str)).add(bArr);
            return;
        }
        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
        concurrentLinkedQueue.add(bArr);
        this.a.put(str, concurrentLinkedQueue);
    }
}

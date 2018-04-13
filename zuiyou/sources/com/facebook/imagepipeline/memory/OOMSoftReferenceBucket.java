package com.facebook.imagepipeline.memory;

import com.facebook.common.references.b;
import java.util.LinkedList;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
class OOMSoftReferenceBucket<V> extends Bucket<V> {
    private LinkedList<b<V>> mSpareReferences = new LinkedList();

    public OOMSoftReferenceBucket(int i, int i2, int i3) {
        super(i, i2, i3);
    }

    public V pop() {
        b bVar = (b) this.mFreeList.poll();
        V a = bVar.a();
        bVar.b();
        this.mSpareReferences.add(bVar);
        return a;
    }

    void addToFreeList(V v) {
        b bVar = (b) this.mSpareReferences.poll();
        if (bVar == null) {
            bVar = new b();
        }
        bVar.a(v);
        this.mFreeList.add(bVar);
    }
}

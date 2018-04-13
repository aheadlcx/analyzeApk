package com.facebook.imagepipeline.memory;

import com.facebook.common.references.OOMSoftReference;
import java.util.LinkedList;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
class OOMSoftReferenceBucket<V> extends Bucket<V> {
    private LinkedList<OOMSoftReference<V>> mSpareReferences = new LinkedList();

    public OOMSoftReferenceBucket(int i, int i2, int i3) {
        super(i, i2, i3);
    }

    public V pop() {
        OOMSoftReference oOMSoftReference = (OOMSoftReference) this.mFreeList.poll();
        V v = oOMSoftReference.get();
        oOMSoftReference.clear();
        this.mSpareReferences.add(oOMSoftReference);
        return v;
    }

    void addToFreeList(V v) {
        OOMSoftReference oOMSoftReference = (OOMSoftReference) this.mSpareReferences.poll();
        if (oOMSoftReference == null) {
            oOMSoftReference = new OOMSoftReference();
        }
        oOMSoftReference.set(v);
        this.mFreeList.add(oOMSoftReference);
    }
}

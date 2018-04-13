package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.Accumulator;

public interface Document$UpdateListener {
    void onAttributeModified(Object obj, String str, String str2);

    void onAttributeRemoved(Object obj, String str);

    void onChildNodeInserted(DocumentView documentView, Object obj, int i, int i2, Accumulator<Object> accumulator);

    void onChildNodeRemoved(int i, int i2);

    void onInspectRequested(Object obj);
}

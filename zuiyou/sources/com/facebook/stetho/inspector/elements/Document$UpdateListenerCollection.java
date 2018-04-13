package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.Accumulator;
import java.util.ArrayList;
import java.util.List;

class Document$UpdateListenerCollection implements Document$UpdateListener {
    private final List<Document$UpdateListener> mListeners = new ArrayList();
    private volatile Document$UpdateListener[] mListenersSnapshot;
    final /* synthetic */ Document this$0;

    public Document$UpdateListenerCollection(Document document) {
        this.this$0 = document;
    }

    public synchronized void add(Document$UpdateListener document$UpdateListener) {
        this.mListeners.add(document$UpdateListener);
        this.mListenersSnapshot = null;
    }

    public synchronized void remove(Document$UpdateListener document$UpdateListener) {
        this.mListeners.remove(document$UpdateListener);
        this.mListenersSnapshot = null;
    }

    public synchronized void clear() {
        this.mListeners.clear();
        this.mListenersSnapshot = null;
    }

    private Document$UpdateListener[] getListenersSnapshot() {
        Document$UpdateListener[] document$UpdateListenerArr;
        while (true) {
            document$UpdateListenerArr = this.mListenersSnapshot;
            if (document$UpdateListenerArr != null) {
                break;
            }
            synchronized (this) {
                if (this.mListenersSnapshot == null) {
                    break;
                }
            }
        }
        this.mListenersSnapshot = (Document$UpdateListener[]) this.mListeners.toArray(new Document$UpdateListener[this.mListeners.size()]);
        document$UpdateListenerArr = this.mListenersSnapshot;
        return document$UpdateListenerArr;
    }

    public void onAttributeModified(Object obj, String str, String str2) {
        for (Document$UpdateListener onAttributeModified : getListenersSnapshot()) {
            onAttributeModified.onAttributeModified(obj, str, str2);
        }
    }

    public void onAttributeRemoved(Object obj, String str) {
        for (Document$UpdateListener onAttributeRemoved : getListenersSnapshot()) {
            onAttributeRemoved.onAttributeRemoved(obj, str);
        }
    }

    public void onInspectRequested(Object obj) {
        for (Document$UpdateListener onInspectRequested : getListenersSnapshot()) {
            onInspectRequested.onInspectRequested(obj);
        }
    }

    public void onChildNodeRemoved(int i, int i2) {
        for (Document$UpdateListener onChildNodeRemoved : getListenersSnapshot()) {
            onChildNodeRemoved.onChildNodeRemoved(i, i2);
        }
    }

    public void onChildNodeInserted(DocumentView documentView, Object obj, int i, int i2, Accumulator<Object> accumulator) {
        for (Document$UpdateListener onChildNodeInserted : getListenersSnapshot()) {
            onChildNodeInserted.onChildNodeInserted(documentView, obj, i, i2, accumulator);
        }
    }
}

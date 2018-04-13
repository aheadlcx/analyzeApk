package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.Accumulator;
import java.util.ArrayList;

final class Document$ChildEventingList extends ArrayList<Object> {
    private DocumentView mDocumentView;
    private Object mParentElement;
    private int mParentNodeId;
    final /* synthetic */ Document this$0;

    private Document$ChildEventingList(Document document) {
        this.this$0 = document;
        this.mParentElement = null;
        this.mParentNodeId = -1;
    }

    public void acquire(Object obj, DocumentView documentView) {
        int i;
        this.mParentElement = obj;
        if (this.mParentElement == null) {
            i = -1;
        } else {
            i = Document.access$500(this.this$0).getIdForObject(this.mParentElement).intValue();
        }
        this.mParentNodeId = i;
        this.mDocumentView = documentView;
    }

    public void release() {
        clear();
        this.mParentElement = null;
        this.mParentNodeId = -1;
        this.mDocumentView = null;
    }

    public void addWithEvent(int i, Object obj, Accumulator<Object> accumulator) {
        int i2;
        Object obj2 = i == 0 ? null : get(i - 1);
        if (obj2 == null) {
            i2 = -1;
        } else {
            i2 = Document.access$500(this.this$0).getIdForObject(obj2).intValue();
        }
        add(i, obj);
        Document.access$700(this.this$0).onChildNodeInserted(this.mDocumentView, obj, this.mParentNodeId, i2, accumulator);
    }

    public void removeWithEvent(int i) {
        Document.access$700(this.this$0).onChildNodeRemoved(this.mParentNodeId, Document.access$500(this.this$0).getIdForObject(remove(i)).intValue());
    }
}

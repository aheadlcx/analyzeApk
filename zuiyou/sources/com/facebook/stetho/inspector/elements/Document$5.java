package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.inspector.elements.ShadowDocument.Update;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

class Document$5 implements Accumulator<Object> {
    private Accumulator<Object> insertedElements = new Accumulator<Object>() {
        public void store(Object obj) {
            if (Document$5.this.val$docUpdate.isElementChanged(obj)) {
                Document$5.this.listenerInsertedElements.add(obj);
            }
        }
    };
    private final HashSet<Object> listenerInsertedElements = new HashSet();
    final /* synthetic */ Document this$0;
    final /* synthetic */ Update val$docUpdate;

    Document$5(Document document, Update update) {
        this.this$0 = document;
        this.val$docUpdate = update;
    }

    public void store(Object obj) {
        if (Document.access$500(this.this$0).containsObject(obj) && !this.listenerInsertedElements.contains(obj)) {
            List list;
            ElementInfo elementInfo = Document.access$100(this.this$0).getElementInfo(obj);
            ElementInfo elementInfo2 = this.val$docUpdate.getElementInfo(obj);
            if (elementInfo != null) {
                list = elementInfo.children;
            } else {
                list = Collections.emptyList();
            }
            List list2 = elementInfo2.children;
            Document$ChildEventingList access$900 = Document.access$900(this.this$0, obj, this.val$docUpdate);
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Object obj2 = list.get(i);
                if (Document.access$500(this.this$0).containsObject(obj2)) {
                    ElementInfo elementInfo3 = this.val$docUpdate.getElementInfo(obj2);
                    if (elementInfo3 == null || elementInfo3.parentElement == obj) {
                        access$900.add(obj2);
                    }
                }
            }
            Document.access$1000(access$900, list2, this.insertedElements);
            Document.access$1100(this.this$0, access$900);
        }
    }
}

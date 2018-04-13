package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.inspector.elements.ShadowDocument.Update;
import java.util.ArrayList;
import java.util.Collections;

class Document$4 implements Accumulator<Object> {
    final /* synthetic */ Document this$0;
    final /* synthetic */ Update val$docUpdate;
    final /* synthetic */ ArrayList val$garbageElementIds;

    Document$4(Document document, ArrayList arrayList, Update update) {
        this.this$0 = document;
        this.val$garbageElementIds = arrayList;
        this.val$docUpdate = update;
    }

    public void store(Object obj) {
        Integer num = (Integer) Util.throwIfNull(Document.access$500(this.this$0).getIdForObject(obj));
        if (Collections.binarySearch(this.val$garbageElementIds, num) < 0) {
            ElementInfo elementInfo = Document.access$100(this.this$0).getElementInfo(obj);
            if (elementInfo != null && this.val$docUpdate.getElementInfo(obj).parentElement != elementInfo.parentElement) {
                Document.access$700(this.this$0).onChildNodeRemoved(Document.access$500(this.this$0).getIdForObject(elementInfo.parentElement).intValue(), num.intValue());
            }
        }
    }
}

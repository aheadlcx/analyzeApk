package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.inspector.elements.ShadowDocument.Update;
import java.util.ArrayList;

class Document$3 implements Accumulator<Object> {
    final /* synthetic */ Document this$0;
    final /* synthetic */ Update val$docUpdate;
    final /* synthetic */ ArrayList val$garbageElementIds;

    Document$3(Document document, Update update, ArrayList arrayList) {
        this.this$0 = document;
        this.val$docUpdate = update;
        this.val$garbageElementIds = arrayList;
    }

    public void store(Object obj) {
        Integer num = (Integer) Util.throwIfNull(Document.access$500(this.this$0).getIdForObject(obj));
        if (this.val$docUpdate.getElementInfo(obj).parentElement == null) {
            Document.access$700(this.this$0).onChildNodeRemoved(Document.access$500(this.this$0).getIdForObject(Document.access$100(this.this$0).getElementInfo(obj).parentElement).intValue(), num.intValue());
        }
        this.val$garbageElementIds.add(num);
    }
}

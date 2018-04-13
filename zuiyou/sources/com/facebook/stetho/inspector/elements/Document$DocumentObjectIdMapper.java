package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.inspector.helper.ObjectIdMapper;

final class Document$DocumentObjectIdMapper extends ObjectIdMapper {
    final /* synthetic */ Document this$0;

    private Document$DocumentObjectIdMapper(Document document) {
        this.this$0 = document;
    }

    protected void onMapped(Object obj, int i) {
        this.this$0.verifyThreadAccess();
        Document.access$200(this.this$0).getNodeDescriptor(obj).hook(obj);
    }

    protected void onUnmapped(Object obj, int i) {
        this.this$0.verifyThreadAccess();
        Document.access$200(this.this$0).getNodeDescriptor(obj).unhook(obj);
    }
}

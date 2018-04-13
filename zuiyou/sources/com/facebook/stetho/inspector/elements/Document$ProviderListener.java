package com.facebook.stetho.inspector.elements;

final class Document$ProviderListener implements DocumentProviderListener {
    final /* synthetic */ Document this$0;

    private Document$ProviderListener(Document document) {
        this.this$0 = document;
    }

    public void onPossiblyChanged() {
        Document.access$1200(this.this$0);
    }

    public void onAttributeModified(Object obj, String str, String str2) {
        this.this$0.verifyThreadAccess();
        Document.access$700(this.this$0).onAttributeModified(obj, str, str2);
    }

    public void onAttributeRemoved(Object obj, String str) {
        this.this$0.verifyThreadAccess();
        Document.access$700(this.this$0).onAttributeRemoved(obj, str);
    }

    public void onInspectRequested(Object obj) {
        this.this$0.verifyThreadAccess();
        Document.access$700(this.this$0).onInspectRequested(obj);
    }
}

package com.facebook.stetho.inspector.elements;

class Document$1 implements Runnable {
    final /* synthetic */ Document this$0;

    Document$1(Document document) {
        this.this$0 = document;
    }

    public void run() {
        Document.access$102(this.this$0, new ShadowDocument(Document.access$200(this.this$0).getRootElement()));
        Document.access$300(this.this$0).commit();
        Document.access$200(this.this$0).setListener(new Document$ProviderListener(this.this$0, null));
    }
}

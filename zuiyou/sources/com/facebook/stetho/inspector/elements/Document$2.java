package com.facebook.stetho.inspector.elements;

class Document$2 implements Runnable {
    final /* synthetic */ Document this$0;

    Document$2(Document document) {
        this.this$0 = document;
    }

    public void run() {
        Document.access$200(this.this$0).setListener(null);
        Document.access$102(this.this$0, null);
        Document.access$500(this.this$0).clear();
        Document.access$200(this.this$0).dispose();
        Document.access$202(this.this$0, null);
    }
}

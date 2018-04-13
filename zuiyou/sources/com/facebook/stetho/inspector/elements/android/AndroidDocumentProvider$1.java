package com.facebook.stetho.inspector.elements.android;

class AndroidDocumentProvider$1 implements Runnable {
    final /* synthetic */ AndroidDocumentProvider this$0;

    AndroidDocumentProvider$1(AndroidDocumentProvider androidDocumentProvider) {
        this.this$0 = androidDocumentProvider;
    }

    public void run() {
        AndroidDocumentProvider.access$002(this.this$0, false);
        if (AndroidDocumentProvider.access$100(this.this$0) != null) {
            AndroidDocumentProvider.access$100(this.this$0).onPossiblyChanged();
            AndroidDocumentProvider.access$002(this.this$0, true);
            this.this$0.postDelayed(this, 1000);
        }
    }
}

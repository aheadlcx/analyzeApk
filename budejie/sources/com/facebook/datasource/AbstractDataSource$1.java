package com.facebook.datasource;

class AbstractDataSource$1 implements Runnable {
    final /* synthetic */ AbstractDataSource this$0;
    final /* synthetic */ DataSubscriber val$dataSubscriber;
    final /* synthetic */ boolean val$isCancellation;
    final /* synthetic */ boolean val$isFailure;

    AbstractDataSource$1(AbstractDataSource abstractDataSource, boolean z, DataSubscriber dataSubscriber, boolean z2) {
        this.this$0 = abstractDataSource;
        this.val$isFailure = z;
        this.val$dataSubscriber = dataSubscriber;
        this.val$isCancellation = z2;
    }

    public void run() {
        if (this.val$isFailure) {
            this.val$dataSubscriber.onFailure(this.this$0);
        } else if (this.val$isCancellation) {
            this.val$dataSubscriber.onCancellation(this.this$0);
        } else {
            this.val$dataSubscriber.onNewResult(this.this$0);
        }
    }
}

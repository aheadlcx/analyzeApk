package com.facebook.imagepipeline.producers;

import java.io.IOException;
import java.io.InputStream;

class NetworkFetchProducer$1 implements NetworkFetcher$Callback {
    final /* synthetic */ NetworkFetchProducer this$0;
    final /* synthetic */ FetchState val$fetchState;

    NetworkFetchProducer$1(NetworkFetchProducer networkFetchProducer, FetchState fetchState) {
        this.this$0 = networkFetchProducer;
        this.val$fetchState = fetchState;
    }

    public void onResponse(InputStream inputStream, int i) throws IOException {
        NetworkFetchProducer.access$000(this.this$0, this.val$fetchState, inputStream, i);
    }

    public void onFailure(Throwable th) {
        NetworkFetchProducer.access$100(this.this$0, this.val$fetchState, th);
    }

    public void onCancellation() {
        NetworkFetchProducer.access$200(this.this$0, this.val$fetchState);
    }
}

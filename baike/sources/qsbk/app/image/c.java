package qsbk.app.image;

import com.facebook.imagepipeline.producers.BaseProducerContextCallbacks;
import com.facebook.imagepipeline.producers.NetworkFetcher.Callback;
import java.util.concurrent.Future;

class c extends BaseProducerContextCallbacks {
    final /* synthetic */ Future a;
    final /* synthetic */ Callback b;
    final /* synthetic */ HttpUrlConnectionNetworkFetcherWithHttpDNS c;

    c(HttpUrlConnectionNetworkFetcherWithHttpDNS httpUrlConnectionNetworkFetcherWithHttpDNS, Future future, Callback callback) {
        this.c = httpUrlConnectionNetworkFetcherWithHttpDNS;
        this.a = future;
        this.b = callback;
    }

    public void onCancellationRequested() {
        if (this.a.cancel(false)) {
            this.b.onCancellation();
        }
    }
}

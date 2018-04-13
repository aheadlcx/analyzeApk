package qsbk.app.image;

import com.facebook.imagepipeline.producers.FetchState;
import com.facebook.imagepipeline.producers.NetworkFetcher.Callback;

class b implements Runnable {
    final /* synthetic */ FetchState a;
    final /* synthetic */ Callback b;
    final /* synthetic */ HttpUrlConnectionNetworkFetcherWithHttpDNS c;

    b(HttpUrlConnectionNetworkFetcherWithHttpDNS httpUrlConnectionNetworkFetcherWithHttpDNS, FetchState fetchState, Callback callback) {
        this.c = httpUrlConnectionNetworkFetcherWithHttpDNS;
        this.a = fetchState;
        this.b = callback;
    }

    public void run() {
        this.c.a(this.a, this.b);
    }
}

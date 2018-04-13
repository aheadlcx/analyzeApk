package qsbk.app.image;

import android.os.SystemClock;
import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;
import qsbk.app.image.OkHttpNetworkFetcher.OkHttpNetworkFetchState;
import qsbk.app.utils.image.issue.IOExceptionWrapper;

class j implements Callback {
    final /* synthetic */ OkHttpNetworkFetchState a;
    final /* synthetic */ NetworkFetcher.Callback b;
    final /* synthetic */ OkHttpNetworkFetcher c;

    j(OkHttpNetworkFetcher okHttpNetworkFetcher, OkHttpNetworkFetchState okHttpNetworkFetchState, NetworkFetcher.Callback callback) {
        this.c = okHttpNetworkFetcher;
        this.a = okHttpNetworkFetchState;
        this.b = callback;
    }

    public void onResponse(Call call, Response response) throws IOException {
        long j = 0;
        this.a.responseTime = SystemClock.elapsedRealtime();
        ResponseBody body = response.body();
        try {
            if (response.isSuccessful()) {
                long contentLength = body.contentLength();
                if (contentLength >= 0) {
                    j = contentLength;
                }
                this.b.onResponse(body.byteStream(), (int) j);
                try {
                    body.close();
                    return;
                } catch (Throwable e) {
                    FLog.w("OkHttpNetworkFetchProducer", "Exception when closing response body", e);
                    return;
                }
            }
            this.c.a(call, new IOExceptionWrapper(response.code(), new IOException("Unexpected HTTP code " + response)), this.b);
            try {
                body.close();
            } catch (Throwable e2) {
                FLog.w("OkHttpNetworkFetchProducer", "Exception when closing response body", e2);
            }
        } catch (Exception e3) {
            this.c.a(call, e3, this.b);
            try {
                body.close();
            } catch (Throwable e22) {
                FLog.w("OkHttpNetworkFetchProducer", "Exception when closing response body", e22);
            }
        } catch (Throwable e222) {
            try {
                body.close();
            } catch (Throwable e4) {
                FLog.w("OkHttpNetworkFetchProducer", "Exception when closing response body", e4);
            }
            throw e222;
        }
    }

    public void onFailure(Call call, IOException iOException) {
        this.c.a(call, (Exception) iOException, this.b);
    }
}

package qsbk.app.image;

import android.os.Looper;
import com.facebook.imagepipeline.producers.BaseProducerContextCallbacks;
import okhttp3.Call;

class h extends BaseProducerContextCallbacks {
    final /* synthetic */ Call a;
    final /* synthetic */ OkHttpNetworkFetcher b;

    h(OkHttpNetworkFetcher okHttpNetworkFetcher, Call call) {
        this.b = okHttpNetworkFetcher;
        this.a = call;
    }

    public void onCancellationRequested() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            this.a.cancel();
        } else {
            this.b.b.execute(new i(this));
        }
    }
}

package qsbk.app.utils.audit;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import qsbk.app.utils.audit.SimpleImageLoader.onCallback;

class d implements RequestListener {
    final /* synthetic */ ImageView a;
    final /* synthetic */ ProgressBar b;
    final /* synthetic */ int c;
    final /* synthetic */ int d;
    final /* synthetic */ Drawable e;
    final /* synthetic */ int[] f;
    final /* synthetic */ onCallback g;
    final /* synthetic */ String h;
    final /* synthetic */ Drawable i;
    final /* synthetic */ SimpleImageLoader j;

    d(SimpleImageLoader simpleImageLoader, ImageView imageView, ProgressBar progressBar, int i, int i2, Drawable drawable, int[] iArr, onCallback oncallback, String str, Drawable drawable2) {
        this.j = simpleImageLoader;
        this.a = imageView;
        this.b = progressBar;
        this.c = i;
        this.d = i2;
        this.e = drawable;
        this.f = iArr;
        this.g = oncallback;
        this.h = str;
        this.i = drawable2;
    }

    public void onSuccess(Request request, int i, byte[] bArr) {
        if (SimpleImageLoader.c) {
            Log.e(SimpleImageLoader.a, " Succuss invoke===========" + bArr.length);
        }
        request.markSuccess();
        this.j.f.remove(request.getUrl());
        this.j.e.put(request.getUrl(), bArr);
        this.j.a(this.a, this.b, bArr, this.c, this.d, this.e, this.f);
        if (this.g != null) {
            this.g.onSuccess(this.a, this.h);
        }
    }

    public void onPrepare(Request request, int i) {
        if (SimpleImageLoader.c) {
            Log.e(SimpleImageLoader.a, " Prepare invoke, totalSize = " + i + ", request url == " + request.getUrl());
        }
        if (!this.j.f.contains(this.h)) {
            StringBuffer stringBuffer = new StringBuffer(" Not contain url ");
            byte[] bArr = (byte[]) this.j.e.get(this.h);
            if (bArr != null) {
                stringBuffer.append(String.format(" And get it from memory cache [%1s]", new Object[]{this.h}));
                request.markSuccess();
                this.j.a(this.a, this.b, bArr, this.c, this.d, this.e, this.f);
                if (this.g != null) {
                    this.g.onSuccess(this.a, this.h);
                    return;
                }
                return;
            } else if (SimpleImageLoader.c) {
                Log.e(SimpleImageLoader.a, stringBuffer.toString());
            }
        } else if (SimpleImageLoader.c) {
            Log.e(SimpleImageLoader.a, String.format("Contains url %1s , means loading it.", new Object[]{this.h}));
        }
        this.j.f.add(this.h);
        if (!request.isFinished()) {
            this.j.a(this.a, this.b, this.i, i);
        }
    }

    public void onError(Request request, LoaderException loaderException) {
        if (SimpleImageLoader.c) {
            Log.e(SimpleImageLoader.a, " Error invoke===========" + loaderException.toString());
        }
        request.markFinished();
        this.j.f.remove(request.getUrl());
        this.j.a(this.a, this.b, this.e);
        if (this.g != null) {
            this.g.onFailed(this.a, this.h, loaderException);
        }
    }

    public void onDownloading(Request request, int i, int i2) {
        if (SimpleImageLoader.c) {
            Log.e(SimpleImageLoader.a, " Downloading invoke===========" + i2 + MqttTopic.TOPIC_LEVEL_SEPARATOR + i);
        }
        if (!request.isFinished()) {
            this.j.a(this.b, i, i2);
        }
    }

    public void onCanceled(Request request) {
        if (SimpleImageLoader.c) {
            Log.e(SimpleImageLoader.a, " Cancel invoke===========");
        }
        request.markFinished();
        this.j.f.remove(request.getUrl());
        this.j.a(this.a, this.b, this.e);
        if (this.g != null) {
            this.g.onFailed(this.a, this.h, new LoaderException("Download canceled."));
        }
    }
}

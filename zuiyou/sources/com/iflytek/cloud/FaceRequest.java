package com.iflytek.cloud;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.thirdparty.bh;
import com.iflytek.cloud.thirdparty.cb;
import com.iflytek.cloud.thirdparty.ci;

public class FaceRequest extends bh {
    private ci a;
    private Context d;

    protected class a implements RequestListener {
        final /* synthetic */ FaceRequest a;
        private RequestListener b = null;
        private Handler c = new Handler(this, Looper.getMainLooper()) {
            final /* synthetic */ a a;

            public void handleMessage(Message message) {
                if (this.a.b != null) {
                    cb.a("SpeechListener onMsg = " + message.what);
                    switch (message.what) {
                        case 0:
                            this.a.b.onEvent(message.arg1, (Bundle) message.obj);
                            break;
                        case 1:
                            this.a.b.onBufferReceived((byte[]) message.obj);
                            break;
                        case 2:
                            this.a.b.onCompleted((SpeechError) message.obj);
                            break;
                    }
                    super.handleMessage(message);
                }
            }
        };

        public a(FaceRequest faceRequest, RequestListener requestListener) {
            this.a = faceRequest;
            this.b = requestListener;
        }

        public void onBufferReceived(byte[] bArr) {
            this.c.sendMessage(this.c.obtainMessage(1, bArr));
        }

        public void onCompleted(SpeechError speechError) {
            this.c.sendMessage(this.c.obtainMessage(2, speechError));
        }

        public void onEvent(int i, Bundle bundle) {
            this.c.sendMessage(this.c.obtainMessage(0, i, 0, bundle));
        }
    }

    public FaceRequest(Context context) {
        this.d = context;
    }

    public void cancel() {
        synchronized (this) {
            if (this.a != null) {
                this.a.a();
                this.a = null;
            }
        }
    }

    public boolean destroy() {
        boolean destroy = this.a.destroy();
        return destroy ? super.destroy() : destroy;
    }

    public String getParameter(String str) {
        return super.getParameter(str);
    }

    public int sendRequest(byte[] bArr, RequestListener requestListener) {
        int a;
        synchronized (this) {
            if (this.a != null) {
                this.a.a();
                this.a = null;
            }
            this.a = new ci(this.d, this.c);
            a = this.a.a(bArr, new a(this, requestListener));
        }
        return a;
    }

    public boolean setParameter(String str, String str2) {
        return super.setParameter(str, str2);
    }
}

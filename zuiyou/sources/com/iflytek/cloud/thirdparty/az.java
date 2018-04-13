package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechListener;

public class az extends be {
    private bb a = new bb();
    private SpeechListener b;

    public static final class a implements SpeechListener {
        private SpeechListener a = null;
        private Handler b = new Handler(this, Looper.getMainLooper()) {
            final /* synthetic */ a a;

            public void handleMessage(Message message) {
                if (this.a.a != null) {
                    switch (message.what) {
                        case 0:
                            this.a.a.onEvent(message.arg1, (Bundle) message.obj);
                            return;
                        case 1:
                            this.a.a.onBufferReceived((byte[]) message.obj);
                            return;
                        case 2:
                            this.a.a.onCompleted((SpeechError) message.obj);
                            return;
                        default:
                            return;
                    }
                }
            }
        };

        public a(SpeechListener speechListener) {
            this.a = speechListener;
        }

        public void onBufferReceived(byte[] bArr) {
            this.b.sendMessage(this.b.obtainMessage(1, bArr));
        }

        public void onCompleted(SpeechError speechError) {
            this.b.sendMessage(this.b.obtainMessage(2, speechError));
        }

        public void onEvent(int i, Bundle bundle) {
            this.b.sendMessage(this.b.obtainMessage(0, i, 0, bundle));
        }
    }

    public az(Context context, HandlerThread handlerThread) {
        super(context, handlerThread);
    }

    protected void a(Message message) throws Throwable, SpeechError {
        switch (message.what) {
            case 13:
                synchronized (az.class) {
                    int a = this.a.a(this.t, (be) this);
                    String format = String.format("{'ret':%d,'cmd':%s}", new Object[]{Integer.valueOf(a), x().e(SpeechConstant.ISV_CMD)});
                    if (this.b != null) {
                        this.b.onBufferReceived(format.getBytes(q()));
                    }
                    a(21);
                }
                return;
            default:
                return;
        }
    }

    protected void a(SpeechError speechError) {
        super.a(speechError);
        if (this.b != null) {
            this.b.onCompleted(speechError);
        }
    }

    public void a(ce ceVar, a aVar) {
        this.b = aVar;
        a(ceVar);
        Message obtain = Message.obtain();
        obtain.what = 13;
        d(obtain);
    }

    public String e() {
        return this.a.g();
    }

    public String f() {
        return this.a.f();
    }

    public String g() {
        return SpeechConstant.ENG_IVP;
    }
}

package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechListener;

public abstract class bi extends bh {
    protected Context a = null;
    protected Object d = new Object();
    protected volatile be e = null;
    protected volatile HandlerThread f = null;

    protected class a implements SpeechListener {
        final /* synthetic */ bi a;
        private SpeechListener b = null;
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

        public a(bi biVar, SpeechListener speechListener) {
            this.a = biVar;
            this.b = speechListener;
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

    protected bi(Context context) {
        if (context != null) {
            br.a(context.getApplicationContext());
            this.a = context.getApplicationContext();
            try {
                b();
                return;
            } catch (Throwable e) {
                cb.a(e);
                return;
            }
        }
        this.a = null;
    }

    protected HandlerThread a(String str) throws Throwable {
        this.f = new HandlerThread(str);
        this.f.start();
        return this.f;
    }

    protected void b() throws Exception {
    }

    protected boolean b_() {
        if (this.f != null && this.f.isAlive()) {
            HandlerThread handlerThread = this.f;
            this.f = null;
            handlerThread.interrupt();
        }
        return true;
    }

    protected String c() {
        return getClass().toString();
    }

    public void cancel(boolean z) {
        if (this.e != null) {
            this.e.b(z);
        }
    }

    protected boolean d() {
        return this.e != null && this.e.v();
    }

    public boolean destroy() {
        boolean z = false;
        synchronized (this.d) {
            if (d()) {
                this.e.b(false);
            } else {
                z = b_();
                cb.d(c() + "destory =" + z);
            }
        }
        return z ? super.destroy() : z;
    }

    protected void finalize() throws Throwable {
        cb.a(c() + " finalize called");
        super.finalize();
    }

    public int getSampleRate() {
        return this.c.a("sample_rate", 16000);
    }
}

package com.iflytek.cloud;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.thirdparty.bh;
import com.iflytek.cloud.thirdparty.cb;
import com.iflytek.cloud.thirdparty.co;
import com.iflytek.speech.SpeechUnderstanderAidl;
import com.iflytek.speech.SpeechUnderstanderListener;

public class SpeechUnderstander extends bh {
    protected static SpeechUnderstander a = null;
    private co d = null;
    private SpeechUnderstanderAidl e = null;
    private a f = null;
    private InitListener g = null;
    private Handler h = new Handler(this, Looper.getMainLooper()) {
        final /* synthetic */ SpeechUnderstander a;

        public void handleMessage(Message message) {
            if (this.a.g != null) {
                this.a.g.onInit(0);
            }
        }
    };

    private final class a implements SpeechUnderstanderListener {
        private SpeechUnderstanderListener a;
        private Handler b;

        public void onBeginOfSpeech() {
            this.b.sendMessage(this.b.obtainMessage(2, 0, 0, null));
        }

        public void onEndOfSpeech() {
            this.b.sendMessage(this.b.obtainMessage(3, 0, 0, null));
        }

        public void onError(SpeechError speechError) {
            this.b.sendMessage(this.b.obtainMessage(0, speechError));
        }

        public void onEvent(int i, int i2, int i3, Bundle bundle) {
            Message message = new Message();
            message.what = i;
            message.arg1 = i2;
            message.arg2 = i3;
            message.obj = bundle;
            this.b.sendMessage(this.b.obtainMessage(6, 0, 0, message));
        }

        public void onResult(UnderstanderResult understanderResult) {
            this.b.sendMessage(this.b.obtainMessage(4, understanderResult));
        }

        public void onVolumeChanged(int i, byte[] bArr) {
            this.b.sendMessage(this.b.obtainMessage(1, i, 0, bArr));
        }
    }

    protected SpeechUnderstander(Context context, InitListener initListener) {
        this.g = initListener;
        this.d = new co(context);
        SpeechUtility utility = SpeechUtility.getUtility();
        if (utility != null && utility.a() && utility.getEngineMode() != com.iflytek.cloud.thirdparty.bh.a.MSC) {
            this.e = new SpeechUnderstanderAidl(context.getApplicationContext(), initListener);
        } else if (initListener != null) {
            Message.obtain(this.h, 0, 0, 0, null).sendToTarget();
        }
    }

    public static synchronized SpeechUnderstander createUnderstander(Context context, InitListener initListener) {
        SpeechUnderstander speechUnderstander;
        synchronized (SpeechUnderstander.class) {
            synchronized (b) {
                if (a == null && SpeechUtility.getUtility() != null) {
                    a = new SpeechUnderstander(context, initListener);
                }
            }
            speechUnderstander = a;
        }
        return speechUnderstander;
    }

    public static SpeechUnderstander getUnderstander() {
        return a;
    }

    protected void a(Context context) {
        SpeechUtility utility = SpeechUtility.getUtility();
        if (utility != null && utility.a() && utility.getEngineMode() != com.iflytek.cloud.thirdparty.bh.a.MSC) {
            if (!(this.e == null || this.e.isAvailable())) {
                this.e.destory();
                this.e = null;
            }
            this.e = new SpeechUnderstanderAidl(context.getApplicationContext(), this.g);
        } else if (this.g != null && this.e != null) {
            this.e.destory();
            this.e = null;
        }
    }

    public void cancel() {
        if (this.d != null && this.d.a()) {
            this.d.a(false);
        } else if (this.e == null || !this.e.isUnderstanding()) {
            cb.c("SpeechUnderstander cancel failed, is not running");
        } else {
            this.e.cancel(this.f.a);
        }
    }

    public boolean destroy() {
        boolean z = true;
        SpeechUnderstanderAidl speechUnderstanderAidl = this.e;
        if (speechUnderstanderAidl != null) {
            speechUnderstanderAidl.destory();
        }
        synchronized (this) {
            this.e = null;
        }
        co coVar = this.d;
        if (coVar != null) {
            z = coVar.c();
        }
        if (z) {
            z = super.destroy();
            if (z) {
                synchronized (b) {
                    a = null;
                }
            }
        }
        return z;
    }

    public String getParameter(String str) {
        return super.getParameter(str);
    }

    public boolean isUnderstanding() {
        return (this.d == null || !this.d.a()) ? this.e != null && this.e.isUnderstanding() : true;
    }

    public boolean setParameter(String str, String str2) {
        return super.setParameter(str, str2);
    }

    public int startUnderstanding(SpeechUnderstanderListener speechUnderstanderListener) {
        cb.a("start engine mode = " + a(SpeechConstant.ENG_NLU, this.e).toString());
        if (this.d == null) {
            return 21001;
        }
        this.d.a(this.c);
        return this.d.a(speechUnderstanderListener);
    }

    public void stopUnderstanding() {
        if (this.d != null && this.d.a()) {
            this.d.b();
        } else if (this.e == null || !this.e.isUnderstanding()) {
            cb.a("SpeechUnderstander stopUnderstanding, is not understanding");
        } else {
            this.e.stopUnderstanding(this.f.a);
        }
    }

    public int writeAudio(byte[] bArr, int i, int i2) {
        if (this.d != null && this.d.a()) {
            return this.d.a(bArr, i, i2);
        }
        if (this.e != null && this.e.isUnderstanding()) {
            return this.e.writeAudio(bArr, i, i2);
        }
        cb.a("SpeechUnderstander writeAudio, is not understanding");
        return 21004;
    }
}

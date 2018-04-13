package com.iflytek.cloud;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.thirdparty.bh;
import com.iflytek.cloud.thirdparty.cb;
import com.iflytek.cloud.thirdparty.cm;
import com.iflytek.cloud.util.ResourceUtil;
import com.iflytek.speech.RecognizerListener;
import com.iflytek.speech.SpeechRecognizerAidl;

public final class SpeechRecognizer extends bh {
    private static SpeechRecognizer a = null;
    private cm d = null;
    private SpeechRecognizerAidl e = null;
    private a f = null;
    private InitListener g = null;
    private Handler h = new Handler(this, Looper.getMainLooper()) {
        final /* synthetic */ SpeechRecognizer a;

        public void handleMessage(Message message) {
            if (this.a.g != null) {
                this.a.g.onInit(0);
            }
        }
    };

    private final class a implements RecognizerListener {
        private RecognizerListener a;
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

        public void onResult(RecognizerResult recognizerResult, boolean z) {
            int i = 1;
            Handler handler = this.b;
            if (!z) {
                i = 0;
            }
            this.b.sendMessage(handler.obtainMessage(4, i, 0, recognizerResult));
        }

        public void onVolumeChanged(int i, byte[] bArr) {
            this.b.sendMessage(this.b.obtainMessage(1, i, 0, bArr));
        }
    }

    protected SpeechRecognizer(Context context, InitListener initListener) {
        this.g = initListener;
        this.d = new cm(context);
        SpeechUtility utility = SpeechUtility.getUtility();
        if (utility != null && utility.a() && utility.getEngineMode() != com.iflytek.cloud.thirdparty.bh.a.MSC) {
            this.e = new SpeechRecognizerAidl(context.getApplicationContext(), initListener);
        } else if (initListener != null) {
            Message.obtain(this.h, 0, 0, 0, null).sendToTarget();
        }
    }

    public static synchronized SpeechRecognizer createRecognizer(Context context, InitListener initListener) {
        SpeechRecognizer speechRecognizer;
        synchronized (SpeechRecognizer.class) {
            synchronized (b) {
                if (a == null && SpeechUtility.getUtility() != null) {
                    a = new SpeechRecognizer(context, initListener);
                }
            }
            speechRecognizer = a;
        }
        return speechRecognizer;
    }

    public static SpeechRecognizer getRecognizer() {
        return a;
    }

    protected void a(Context context) {
        SpeechUtility utility = SpeechUtility.getUtility();
        if (utility != null && utility.a() && utility.getEngineMode() != com.iflytek.cloud.thirdparty.bh.a.MSC) {
            if (!(this.e == null || this.e.isAvailable())) {
                this.e.destory();
                this.e = null;
            }
            this.e = new SpeechRecognizerAidl(context.getApplicationContext(), this.g);
        } else if (this.g != null && this.e != null) {
            this.e.destory();
            this.e = null;
        }
    }

    public int buildGrammar(String str, String str2, GrammarListener grammarListener) {
        cb.a("start engine mode = " + a("asr", this.e).toString());
        if (this.d == null) {
            return 21001;
        }
        this.d.setParameter(this.c);
        return this.d.a(str, str2, grammarListener);
    }

    public void cancel() {
        if (this.d != null && this.d.g()) {
            this.d.cancel(false);
        } else if (this.e == null || !this.e.isListening()) {
            cb.c("SpeechRecognizer cancel failed, is not running");
        } else if (this.f != null) {
            this.e.cancel(this.f.a);
        }
    }

    public boolean destroy() {
        boolean z = true;
        SpeechRecognizerAidl speechRecognizerAidl = this.e;
        if (speechRecognizerAidl != null) {
            speechRecognizerAidl.destory();
        }
        synchronized (this) {
            this.e = null;
        }
        cm cmVar = this.d;
        if (cmVar != null) {
            z = cmVar.destroy();
        }
        if (z) {
            z = super.destroy();
            if (z) {
                synchronized (b) {
                    a = null;
                }
                SpeechUtility utility = SpeechUtility.getUtility();
                if (utility != null) {
                    cb.a("Destory asr engine.");
                    utility.setParameter(ResourceUtil.ENGINE_DESTROY, "engine_destroy=asr");
                }
            }
        }
        return z;
    }

    public String getParameter(String str) {
        return super.getParameter(str);
    }

    public boolean isListening() {
        return (this.d == null || !this.d.g()) ? this.e != null && this.e.isListening() : true;
    }

    public boolean setParameter(String str, String str2) {
        return super.setParameter(str, str2);
    }

    public int startListening(RecognizerListener recognizerListener) {
        cb.a("start engine mode = " + a("asr", this.e).toString());
        if (this.d == null) {
            return 21001;
        }
        this.d.setParameter(this.c);
        return this.d.a(recognizerListener);
    }

    public void stopListening() {
        if (this.d != null && this.d.g()) {
            this.d.e();
        } else if (this.e == null || !this.e.isListening()) {
            cb.c("SpeechRecognizer stopListening failed, is not running");
        } else if (this.f != null) {
            this.e.stopListening(this.f.a);
        }
    }

    public int updateLexicon(String str, String str2, LexiconListener lexiconListener) {
        cb.a("start engine mode = " + a("asr", this.e).toString());
        if (this.d == null) {
            return 21001;
        }
        this.d.setParameter(this.c);
        return this.d.a(str, str2, lexiconListener);
    }

    public int writeAudio(byte[] bArr, int i, int i2) {
        if (this.d != null && this.d.g()) {
            return this.d.a(bArr, i, i2);
        }
        if (this.e != null && this.e.isListening()) {
            return this.e.writeAudio(bArr, i, i2);
        }
        cb.c("SpeechRecognizer writeAudio failed, is not running");
        return 21004;
    }
}

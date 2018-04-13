package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SynthesizerListener;
import com.iflytek.cloud.thirdparty.bn.a;

public class cn extends bi implements a {
    private Context g = null;
    private bn h = null;
    private bn i = null;

    public cn(Context context) {
        super(context);
        this.g = context.getApplicationContext();
    }

    private int a(String str, SynthesizerListener synthesizerListener, String str2) {
        cb.a("new Session Start");
        this.h = new bn(this.g);
        this.h.a((a) this);
        String str3 = str;
        int a = this.h.a(str3, this.c, synthesizerListener, true, this.c.e(SpeechConstant.TTS_AUDIO_PATH));
        if (!TextUtils.isEmpty(str2)) {
            this.i = new bn(this.g);
            this.i.a((a) this);
            this.i.a(str2, this.c);
        }
        return a;
    }

    public int a(String str, SynthesizerListener synthesizerListener) {
        int i = 0;
        cb.a("startSpeaking enter");
        synchronized (this) {
            String d = this.c.d(SpeechConstant.NEXT_TEXT);
            if (this.h != null && this.h.h()) {
                this.h.cancel(this.c.a(SpeechConstant.TTS_INTERRUPT_ERROR, false));
            }
            if (this.i == null) {
                i = a(str, synthesizerListener, d);
            } else if (!str.equals(this.i.i)) {
                this.i.cancel(false);
                this.i = null;
                i = a(str, synthesizerListener, d);
            } else if (this.i.j == null && this.i.g) {
                bn bnVar = this.i;
                this.i = null;
                if (!TextUtils.isEmpty(d)) {
                    this.i = new bn(this.g);
                    this.i.a((a) this);
                    this.i.a(d, this.c);
                }
                this.h = bnVar;
                this.h.a(synthesizerListener);
                this.h.i();
                if (this.h.h) {
                    a();
                    cb.a("startSpeaking NextSession pause");
                }
            } else {
                this.i.cancel(false);
                this.i = null;
                i = a(str, synthesizerListener, d);
            }
        }
        cb.a("startSpeaking leave");
        return i;
    }

    public int a(String str, String str2, SynthesizerListener synthesizerListener) {
        int a;
        cb.a("synthesizeToUri enter");
        synchronized (this) {
            if (this.h != null && this.h.h()) {
                this.h.cancel(this.c.a(SpeechConstant.TTS_INTERRUPT_ERROR, false));
            }
            this.h = new bn(this.g);
            a = this.h.a(str, str2, this.c, synthesizerListener);
        }
        cb.a("synthesizeToUri leave");
        return a;
    }

    public void a() {
        synchronized (this) {
            if (this.i != null) {
                this.i.e();
            }
        }
    }

    public void a(boolean z) {
        cb.a("stopSpeaking enter:" + z);
        synchronized (this) {
            if (this.h != null) {
                cb.a("-->stopSpeaking cur");
                this.h.cancel(z);
                this.h = null;
            }
            if (this.i != null) {
                cb.a("-->stopSpeaking cur next");
                this.i.cancel(false);
                this.i = null;
            }
        }
        cb.a("stopSpeaking leave");
    }

    public boolean destroy() {
        a(false);
        super.destroy();
        return true;
    }

    public void e() {
        cb.a("pauseSpeaking enter");
        synchronized (this) {
            if (this.h != null) {
                this.h.g();
            }
        }
        cb.a("pauseSpeaking leave");
    }

    public void f() {
        cb.a("resumeSpeaking enter");
        synchronized (this) {
            if (this.h != null) {
                this.h.i();
            }
        }
        cb.a("resumeSpeaking leave");
    }

    public boolean g() {
        boolean z = false;
        cb.a("isSpeaking enter");
        synchronized (this) {
            if (this.h != null) {
                z = this.h.h();
            }
        }
        cb.a("isSpeaking leave");
        return z;
    }

    public int h() {
        int i = 4;
        cb.a("getState enter");
        synchronized (this) {
            if (this.h != null) {
                i = this.h.f();
            }
        }
        cb.a("getState leave");
        return i;
    }
}

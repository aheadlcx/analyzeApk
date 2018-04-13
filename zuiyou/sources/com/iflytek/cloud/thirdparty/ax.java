package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechEvent;
import com.iflytek.cloud.record.PcmRecorder;
import com.iflytek.cloud.record.PcmRecorder.PcmRecordListener;
import com.iflytek.cloud.record.a;
import com.iflytek.msc.MSC;
import com.meizu.cloud.pushsdk.networking.common.ANConstants;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ax extends be implements PcmRecordListener {
    public static int j = 0;
    public static int k = 0;
    private boolean A;
    private int B;
    protected volatile RecognizerListener a;
    protected boolean b;
    protected boolean c;
    protected boolean d;
    protected boolean e;
    protected int f;
    protected boolean g;
    protected aw h;
    protected PcmRecorder i;
    protected String l;
    protected ConcurrentLinkedQueue<byte[]> m;
    protected ArrayList<String> n;
    protected bg o;
    protected int p;
    private boolean q;
    private String z;

    public ax(Context context, ce ceVar, HandlerThread handlerThread) {
        super(context, handlerThread);
        this.a = null;
        this.b = false;
        this.c = false;
        this.d = false;
        this.e = false;
        this.f = 1;
        this.g = true;
        this.h = new aw();
        this.i = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = new bg();
        this.p = 0;
        this.q = false;
        this.z = null;
        this.A = false;
        this.B = 0;
        this.m = new ConcurrentLinkedQueue();
        this.n = new ArrayList();
        this.e = false;
        a(ceVar);
    }

    private void a(boolean z, byte[] bArr) throws SpeechError, UnsupportedEncodingException {
        String str;
        this.v = SystemClock.elapsedRealtime();
        if (bArr != null && bArr.length > 0) {
            str = new String(bArr, "utf-8");
        } else if (this.n.size() <= 0) {
            CharSequence e = x().e(SpeechConstant.LOCAL_GRAMMAR);
            if (!TextUtils.isEmpty(e) && !"sms.irf".equals(e)) {
                throw new SpeechError(20005);
            } else if (x().a(SpeechConstant.ASR_NOMATCH_ERROR, true)) {
                throw new SpeechError(10118);
            } else {
                str = "";
            }
        } else {
            str = "";
        }
        try {
            this.x.a(str, z);
        } catch (Throwable th) {
            cb.c("DC exception:");
            cb.a(th);
        }
        this.n.add(str);
        if (this.a != null && v()) {
            Bundle bundle = new Bundle();
            bundle.putString(SpeechEvent.KEY_EVENT_SESSION_ID, f());
            this.a.onEvent(20001, 0, 0, bundle);
            if (z && x().a("request_audio_url", false)) {
                bundle = new Bundle();
                bundle.putString("audio_url", this.h.d());
                this.a.onEvent(23001, 0, 0, bundle);
            }
            RecognizerResult recognizerResult = new RecognizerResult(str);
            cc.a("GetNotifyResult", null);
            this.a.onResult(recognizerResult, z);
        }
        cb.a("msc result time:" + System.currentTimeMillis());
        if (z) {
            b(null);
        }
    }

    public int a() {
        return this.f;
    }

    public int a(byte[] bArr, int i, int i2) {
        onRecordBuffer(bArr, i, i2);
        return 0;
    }

    protected void a(Message message) throws Throwable, SpeechError {
        super.a(message);
        switch (message.what) {
            case 0:
                h();
                return;
            case 1:
                i();
                return;
            case 2:
                b(message);
                return;
            case 3:
                j();
                return;
            case 4:
                c(message);
                return;
            case 7:
                k();
                return;
            case 9:
                l();
                return;
            default:
                return;
        }
    }

    public synchronized void a(RecognizerListener recognizerListener) {
        this.a = recognizerListener;
        cb.a("startListening called");
        a_();
    }

    protected void a(SpeechError speechError) {
        cb.a("onSessionEnd");
        n();
        j = this.h.b("upflow");
        k = this.h.b("downflow");
        f();
        if (this.n.size() <= 0 && speechError == null && x().a(SpeechConstant.ASR_NOMATCH_ERROR, true)) {
            speechError = new SpeechError(10118);
        }
        if (speechError != null) {
            this.o.a("app_ret", (long) speechError.getErrorCode(), false);
        } else {
            this.o.a("app_ret", 0, false);
        }
        this.o.a("rec_ustop", this.e ? "1" : "0", false);
        this.h.a("sessinfo", this.o.a());
        cc.a("SessionEndBegin", null);
        if (this.u) {
            this.h.a("user abort");
        } else if (speechError != null) {
            this.h.a("error" + speechError.getErrorCode());
        } else {
            this.h.a(ANConstants.SUCCESS);
        }
        cc.a("SessionEndEnd", null);
        super.a(speechError);
        if (this.a != null) {
            if (this.u) {
                cb.a("RecognizerListener#onCancel");
            } else {
                cb.a("RecognizerListener#onEnd");
                if (speechError != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString(SpeechEvent.KEY_EVENT_SESSION_ID, f());
                    this.a.onEvent(20001, 0, 0, bundle);
                    this.a.onError(speechError);
                }
            }
        }
        this.a = null;
    }

    public void a(byte[] bArr, int i) {
        if (this.a != null && v()) {
            this.a.onVolumeChanged(i, bArr);
            if (this.q) {
                Bundle bundle = new Bundle();
                bundle.putByteArray("data", bArr);
                this.a.onEvent(21003, i, 0, bundle);
            }
        }
    }

    protected void a(byte[] bArr, boolean z) throws SpeechError {
        if (!this.c) {
            this.c = true;
            this.o.a("app_fau");
            if (this.a != null) {
                this.a.onEvent(22002, 0, 0, null);
            }
        }
        this.h.a(bArr, bArr.length);
        if (z) {
            int b = this.h.b();
            cb.b("QISRAudioWrite volume:" + b);
            a(bArr, b);
        }
    }

    public synchronized boolean a(boolean z) {
        cb.a("stopRecognize, current status is :" + w() + " usercancel : " + z);
        this.o.a("app_stop");
        n();
        this.e = z;
        a(3);
        return true;
    }

    protected void a_() {
        this.o.a(x());
        super.a_();
    }

    public ConcurrentLinkedQueue<byte[]> b() {
        return this.m;
    }

    protected void b(Message message) throws Exception {
        byte[] bArr = (byte[]) message.obj;
        if (bArr != null && bArr.length != 0) {
            this.m.add(bArr);
            a(bArr, true);
        }
    }

    public void b(boolean z) {
        if (z && v() && this.a != null) {
            this.a.onError(new SpeechError(20017));
        }
        n();
        if (w() == b.recording) {
            this.e = true;
        }
        super.b(z);
    }

    protected void c() {
        this.l = x().e(SpeechConstant.CLOUD_GRAMMAR);
        this.f = x().a(SpeechConstant.AUDIO_SOURCE, 1);
        this.g = cg.a(x().e(SpeechConstant.DOMAIN));
        this.p = x().a(SpeechConstant.FILTER_AUDIO_TIME, 0) * (((x().a("sample_rate", this.s) / 1000) * 16) / 8);
        this.r = x().a(SpeechConstant.KEY_SPEECH_TIMEOUT, this.r);
        this.q = x().a(SpeechConstant.NOTIFY_RECORD_DATA, false);
        cb.a("mSpeechTimeOut=" + this.r);
        super.c();
    }

    void c(Message message) throws SpeechError, InterruptedException, UnsupportedEncodingException {
        byte[] bArr = (byte[]) message.obj;
        switch (message.arg1) {
            case 0:
                if (!this.d) {
                    this.d = true;
                    this.o.a("app_frs");
                }
                a(false, bArr);
                return;
            case 5:
                if (!this.d) {
                    this.d = true;
                    this.o.a("app_frs");
                }
                this.o.a("app_lrs");
                a(true, bArr);
                return;
            default:
                return;
        }
    }

    public String e() {
        return this.h.g();
    }

    void errCb(char[] cArr, int i, byte[] bArr) {
        onError(new SpeechError(i));
    }

    public String f() {
        if (TextUtils.isEmpty(this.z)) {
            this.z = this.h.c();
        }
        return this.z;
    }

    public String g() {
        Throwable e;
        String str;
        try {
            ce x = x();
            str = (TextUtils.isEmpty(this.l) && TextUtils.isEmpty(x != null ? x.e(SpeechConstant.LOCAL_GRAMMAR) : null)) ? "iat" : "asr";
            if (x == null) {
                return str;
            }
            try {
                return (x.a("sch", false) || x.a(SpeechConstant.ASR_SCH, false)) ? "iat_sch" : str;
            } catch (Exception e2) {
                e = e2;
                cb.c("DC get sub type exception:");
                cb.a(e);
                return str;
            }
        } catch (Throwable e3) {
            Throwable th = e3;
            str = null;
            e = th;
            cb.c("DC get sub type exception:");
            cb.a(e);
            return str;
        }
    }

    protected void h() throws Exception {
        cb.a("start connecting");
        String e = x().e("engine_type");
        if (x().a(SpeechConstant.NET_CHECK, true)) {
            if ("cloud".equals(e)) {
                bz.a(this.t);
            } else if ("mixed".equals(e) || "mixed".equals(e)) {
                try {
                    bz.a(this.t);
                } catch (Exception e2) {
                    x().a("engine_type", "local");
                }
            }
        }
        int a = x().a("record_read_rate", 40);
        if (this.f != -1 && v()) {
            cb.a("start  record");
            if (this.f == -2) {
                this.i = new a(t(), a, this.f, x().e(SpeechConstant.ASR_SOURCE_PATH));
            } else {
                this.A = x().a(SpeechConstant.BLUETOOTH, this.A);
                if (this.A) {
                    C();
                }
                this.i = new PcmRecorder(t(), a, this.f);
                if (hasMessages(3)) {
                    throw new SpeechError(10118);
                }
            }
            this.o.a("rec_open");
            this.i.startRecording(this);
            if (-1 != this.r) {
                a(9, a.normal, false, this.r);
            }
        }
        if (this.a != null && this.f > -1) {
            this.a.onBeginOfSpeech();
        }
        this.o.a("app_ssb");
        a(1, a.max, false, 0);
    }

    protected void i() throws Exception {
        cc.a("SDKSessionBegin", null);
        int a = this.h.a(this.t, this.l, (be) this);
        if (a != 0 || this.h.a == null) {
            this.B++;
            if (this.B > 40) {
                throw new SpeechError(a);
            } else if (v()) {
                Thread.sleep(15);
                a(1, a.max, false, 0);
            }
        } else if (v()) {
            MSC.QISRRegisterNotify(this.h.a, "rsltCb", "stusCb", "errCb", this);
            a(b.recording);
            if (x().a(SpeechConstant.ASR_NET_PERF, false)) {
                a(7, a.max, false, 0);
            }
        }
    }

    protected void j() throws SpeechError, IOException, InterruptedException {
        cb.a("recording stop");
        n();
        this.o.a("app_lau");
        this.h.a();
        p();
    }

    public void k() {
        if (v()) {
            int b = this.h.b("netperf");
            if (this.a != null) {
                this.a.onEvent(10001, b, 0, null);
            }
            a(7, a.normal, false, 100);
        }
    }

    public void l() {
        if (b.recording == w()) {
            cb.a("Msc recognize vadEndCall");
            if (this.a != null) {
                this.a.onEndOfSpeech();
            }
            a(false);
        }
    }

    public boolean m() {
        return this.g;
    }

    protected void n() {
        if (this.i != null) {
            this.i.stopRecord(x().a("record_force_stop", false));
            this.i = null;
            this.o.a("rec_close");
            if (this.a != null) {
                this.a.onEvent(22003, 0, 0, null);
            }
            if (this.A) {
                D();
            }
        }
    }

    public bg o() {
        return this.o;
    }

    public void onError(SpeechError speechError) {
        b(speechError);
    }

    public void onRecordBuffer(byte[] bArr, int i, int i2) {
        if (bArr != null && i2 > 0 && bArr.length >= i2 && i2 > 0 && v()) {
            if (!this.b) {
                this.b = true;
                this.o.a("rec_start");
            }
            Object obj;
            if (this.p <= 0) {
                obj = new byte[i2];
                System.arraycopy(bArr, i, obj, 0, i2);
                d(obtainMessage(2, obj));
            } else if (this.p >= i2) {
                this.p -= i2;
            } else {
                obj = new byte[(i2 - this.p)];
                System.arraycopy(bArr, this.p + i, obj, 0, i2 - this.p);
                d(obtainMessage(2, obj));
                this.p = 0;
            }
        }
    }

    public void onRecordReleased() {
        if (this.i != null && (this.i instanceof a)) {
            a(true);
        }
    }

    public void onRecordStarted(boolean z) {
        this.o.a("rec_ready");
    }

    void rsltCb(char[] cArr, byte[] bArr, int i, int i2) {
        if (bArr != null) {
            cb.a("MscRecognizer", "rsltCb:" + i2 + "result:" + new String(bArr));
        } else {
            cb.b("MscRecognizer", "rsltCb:" + i2 + "result:null");
        }
        Message obtainMessage = obtainMessage(4, i2, 0, bArr);
        if (hasMessages(4)) {
            a(obtainMessage, a.normal, false, 0);
        } else {
            a(obtainMessage, a.max, false, 0);
        }
    }

    void stusCb(char[] cArr, int i, int i2, int i3, byte[] bArr) {
        if (i == 0 && i2 == 3) {
            cb.a("MscRecognizer", "stusCb:" + i2 + ",type:" + i);
            l();
        }
    }
}

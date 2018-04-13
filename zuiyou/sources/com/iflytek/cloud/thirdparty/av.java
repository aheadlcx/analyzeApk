package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import com.iflytek.cloud.EvaluatorListener;
import com.iflytek.cloud.EvaluatorResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechEvent;
import com.iflytek.cloud.record.PcmRecorder;
import com.iflytek.cloud.record.PcmRecorder.PcmRecordListener;
import com.iflytek.cloud.thirdparty.bf.a;
import com.meizu.cloud.pushsdk.networking.common.ANConstants;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ConcurrentLinkedQueue;

public class av extends be implements PcmRecordListener {
    public static int a = 0;
    public static int b = 0;
    private static Boolean l = Boolean.valueOf(false);
    private String A;
    private boolean B;
    long c;
    protected int d;
    protected au e;
    protected PcmRecorder f;
    protected bg g;
    protected String h;
    protected byte[] i;
    protected String j;
    protected String k;
    private volatile EvaluatorListener m;
    private ConcurrentLinkedQueue<byte[]> n;
    private ConcurrentLinkedQueue<byte[]> o;
    private ArrayList<String> p;
    private boolean q;
    private a z;

    public av(Context context, ce ceVar, HandlerThread handlerThread) {
        super(context, handlerThread);
        this.m = null;
        this.c = 0;
        this.d = 1;
        this.e = new au();
        this.f = null;
        this.g = new bg();
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = false;
        this.z = a.noResult;
        this.A = null;
        this.B = false;
        this.o = new ConcurrentLinkedQueue();
        this.n = new ConcurrentLinkedQueue();
        this.p = new ArrayList();
        this.q = false;
        a(ceVar);
    }

    private void a(byte[] bArr, int i) {
        if (this.m != null && v()) {
            this.m.onVolumeChanged(i, bArr);
        }
    }

    private void a(byte[] bArr, boolean z) throws SpeechError {
        this.e.a(bArr, bArr.length);
        if (!z) {
            return;
        }
        if (this.e.b() == 3) {
            j();
        } else {
            a(bArr, this.e.c());
        }
    }

    private void d(boolean z) throws SpeechError, UnsupportedEncodingException {
        this.v = SystemClock.elapsedRealtime();
        if (this.e.d() != null && this.e.d().length > 0) {
            String str = new String(this.e.d(), "utf-8");
            this.p.add(str);
            try {
                this.x.a(str, z);
            } catch (Throwable th) {
                cb.c("DC exception:");
                cb.a(th);
            }
        }
        c(z);
    }

    private void h() throws SpeechError, IOException, InterruptedException {
        cb.a("--->onStoped: in");
        if (!v()) {
            k();
        }
        this.e.a();
        p();
        cb.a("--->onStoped: out");
    }

    private void i() throws SpeechError, UnsupportedEncodingException {
        a e = this.e.e();
        this.z = e;
        switch (e) {
            case hasResult:
                d(false);
                return;
            case resultOver:
                d(true);
                return;
            default:
                return;
        }
    }

    private void j() {
        if (b.recording == w()) {
            cb.a("Ise Msc vadEndCall");
            a(false);
            if (this.m != null) {
                this.m.onEndOfSpeech();
            }
        }
    }

    private void k() {
        if (this.f != null) {
            this.f.stopRecord(x().a("record_force_stop", false));
            this.f = null;
            if (this.B) {
                D();
            }
        }
    }

    protected void a() throws Exception {
        cb.a("--->onStart: in");
        if (x().a(SpeechConstant.NET_CHECK, true)) {
            bz.a(this.t);
        }
        int a = x().a("record_read_rate", 40);
        this.d = x().a(SpeechConstant.AUDIO_SOURCE, 1);
        if (this.d != -1 && v()) {
            cb.a("start  record");
            if (this.d == -2) {
                this.f = new com.iflytek.cloud.record.a(t(), a, this.d, x().e(SpeechConstant.ISE_SOURCE_PATH));
            } else {
                this.B = x().a(SpeechConstant.BLUETOOTH, this.B);
                if (this.B) {
                    C();
                }
                this.f = new PcmRecorder(t(), a, this.d);
            }
            this.f.startRecording(this);
        }
        if (!(w() == b.exiting || this.m == null)) {
            this.m.onBeginOfSpeech();
        }
        removeMessages(9);
        if (-1 != this.r) {
            a(9, a.normal, false, this.r);
        }
        a(1, a.max, false, 0);
        cb.a("--->onStart: out");
    }

    protected void a(Message message) throws Throwable, SpeechError {
        super.a(message);
        switch (message.what) {
            case 0:
                a();
                return;
            case 1:
                b();
                return;
            case 2:
                b(message);
                return;
            case 3:
                h();
                return;
            case 4:
                c(message);
                return;
            case 9:
                cb.a("--->on timeout vad");
                j();
                return;
            default:
                return;
        }
    }

    protected void a(SpeechError speechError) {
        cb.a("--->onEnd: in");
        k();
        f();
        cc.a("SessionEndBegin", null);
        if (this.u) {
            this.e.a("user abort");
        } else if (speechError != null) {
            this.e.a("error" + speechError.getErrorCode());
        } else {
            this.e.a(ANConstants.SUCCESS);
        }
        cc.a("SessionEndEnd", null);
        super.a(speechError);
        if (!(this.m == null || this.u)) {
            cb.a("VerifyListener#onEnd");
            if (speechError != null) {
                Bundle bundle = new Bundle();
                bundle.putString(SpeechEvent.KEY_EVENT_SESSION_ID, f());
                this.m.onEvent(20001, 0, 0, bundle);
                this.m.onError(speechError);
            }
        }
        this.m = null;
        cb.a("--->onEnd: out");
    }

    public synchronized void a(String str, String str2, EvaluatorListener evaluatorListener) {
        l = Boolean.valueOf(false);
        this.j = str;
        this.h = str2;
        this.k = x().e(SpeechConstant.ISE_USER_MODEL_ID);
        this.m = evaluatorListener;
        cb.a("startListening called");
        a_();
    }

    public synchronized void a(byte[] bArr, String str, EvaluatorListener evaluatorListener) {
        l = Boolean.valueOf(true);
        this.i = bArr;
        this.h = str;
        this.k = x().e(SpeechConstant.ISE_USER_MODEL_ID);
        this.m = evaluatorListener;
        cb.a("startListening called");
        a_();
    }

    public synchronized boolean a(boolean z) {
        boolean z2 = false;
        synchronized (this) {
            if (w() != b.recording) {
                cb.a("stopRecognize fail  status is :" + w());
            } else {
                if (this.f != null) {
                    this.f.stopRecord(x().a("record_force_stop", false));
                }
                this.q = z;
                a(3);
                z2 = true;
            }
        }
        return z2;
    }

    protected void b() throws Exception {
        byte[] bArr = null;
        if (this.e.a == null) {
            cc.a("SDKSessionBegin", null);
            this.e.a(this.t, this.k, (be) this);
        }
        byte[] a = l.booleanValue() ? "1".equals(x().e(SpeechConstant.TEXT_BOM)) ? bt.a(this.i) : this.i : "1".equals(x().e(SpeechConstant.TEXT_BOM)) ? bt.a(this.j) : this.j.getBytes("gb2312");
        au auVar = this.e;
        if (!TextUtils.isEmpty(this.h)) {
            bArr = this.h.getBytes("gb2312");
        }
        auVar.a(a, bArr);
        a(b.recording);
        a(4, a.normal, false, 20);
    }

    protected void b(Message message) throws Exception {
        cb.a("proc_Msg_Record_Data");
        byte[] bArr = (byte[]) message.obj;
        if (bArr != null && bArr.length != 0) {
            if (!TextUtils.isEmpty(x().e(SpeechConstant.ISE_AUDIO_PATH))) {
                this.n.add(bArr);
            }
            a(bArr, true);
        }
    }

    public void b(boolean z) {
        if (z && v() && this.m != null) {
            this.m.onError(new SpeechError(20017));
        }
        k();
        super.b(z);
    }

    protected void c() {
        this.r = x().a(SpeechConstant.KEY_SPEECH_TIMEOUT, -1);
        cb.a("mSpeechTimeOut=" + this.r);
        if ("utf-8".equals(x().e(SpeechConstant.TEXT_ENCODING)) && Locale.CHINA.toString().equalsIgnoreCase(x().e("language"))) {
            x().a(SpeechConstant.TEXT_BOM, "1", false);
        } else {
            x().a(SpeechConstant.TEXT_BOM, "0", false);
        }
        super.c();
    }

    void c(Message message) throws SpeechError, InterruptedException, UnsupportedEncodingException {
        i();
        if (a.noResult == this.z) {
            a(4, a.normal, false, 20);
        } else if (a.hasResult == this.z) {
            a(4);
        }
    }

    public void c(boolean z) throws SpeechError, UnsupportedEncodingException {
        cb.a("msc result time:" + System.currentTimeMillis());
        EvaluatorResult evaluatorResult = new EvaluatorResult(new String(this.e.d(), x().b("rse", "gb2312")));
        if (this.m != null) {
            Bundle bundle = new Bundle();
            bundle.putString(SpeechEvent.KEY_EVENT_SESSION_ID, f());
            this.m.onEvent(20001, 0, 0, bundle);
            cc.a("GetNotifyResult", null);
            this.m.onResult(evaluatorResult, z);
        }
        if (z) {
            b(null);
        }
    }

    public ConcurrentLinkedQueue<byte[]> d() {
        while (true) {
            byte[] bArr = (byte[]) this.o.poll();
            if (bArr == null) {
                return this.n;
            }
            this.n.add(bArr);
        }
    }

    public String e() {
        return this.e.g();
    }

    public String f() {
        if (TextUtils.isEmpty(this.A)) {
            this.A = this.e.f();
        }
        return this.A;
    }

    public String g() {
        return "ise";
    }

    public void onError(SpeechError speechError) {
        b(speechError);
    }

    public void onRecordBuffer(byte[] bArr, int i, int i2) {
        if (b.recording != w()) {
            cb.c("onRecordBuffer statuts not recording");
        } else if (i2 > 0) {
            Object obj = new byte[i2];
            System.arraycopy(bArr, i, obj, 0, i2);
            d(obtainMessage(2, obj));
        }
    }

    public void onRecordReleased() {
        if (this.f != null && (this.f instanceof com.iflytek.cloud.record.a)) {
            a(true);
        }
    }

    public void onRecordStarted(boolean z) {
    }
}

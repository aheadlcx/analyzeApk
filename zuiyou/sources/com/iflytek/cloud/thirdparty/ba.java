package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechEvent;
import com.iflytek.cloud.VerifierListener;
import com.iflytek.cloud.VerifierResult;
import com.iflytek.cloud.record.PcmRecorder;
import com.iflytek.cloud.record.PcmRecorder.PcmRecordListener;
import com.iflytek.cloud.record.a;
import com.meizu.cloud.pushsdk.networking.common.ANConstants;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ba extends be implements PcmRecordListener {
    protected volatile VerifierListener a;
    protected long b;
    protected boolean c;
    protected bb d;
    protected PcmRecorder e;
    protected String f;
    protected String g;
    protected VerifierResult h;
    protected ConcurrentLinkedQueue<byte[]> i;
    protected int j;
    private long k;
    private int l;
    private String m;
    private boolean n;

    public ba(Context context, ce ceVar, HandlerThread handlerThread) {
        super(context, handlerThread);
        this.a = null;
        this.b = 0;
        this.c = true;
        this.d = new bb();
        this.e = null;
        this.f = "train";
        this.g = "";
        this.h = null;
        this.i = null;
        this.j = 1;
        this.k = 0;
        this.l = 0;
        this.m = null;
        this.n = false;
        this.i = new ConcurrentLinkedQueue();
        a(ceVar);
    }

    private void E() throws SpeechError, UnsupportedEncodingException {
        this.v = SystemClock.elapsedRealtime();
        String str = new String(this.d.d(), "utf-8");
        this.h = new VerifierResult(str);
        if (this.a != null) {
            Bundle bundle = new Bundle();
            bundle.putString(SpeechEvent.KEY_EVENT_SESSION_ID, f());
            this.a.onEvent(20001, 0, 0, bundle);
        }
        if (this.f.equals("train") && this.h.ret == 0 && this.h.suc < this.h.rgn) {
            if (this.a != null) {
                cc.a("GetNotifyResult", null);
                this.a.onResult(this.h);
            }
            a(0);
            return;
        }
        if (this.a != null) {
            cc.a("GetNotifyResult", null);
            this.a.onResult(this.h);
        }
        try {
            this.x.a(str, true);
        } catch (Throwable th) {
            cb.c("DC exception:");
            cb.a(th);
        }
        b(null);
    }

    private void F() {
        if (this.e != null) {
            this.e.stopRecord(x().a("record_force_stop", false));
            this.e = null;
            if (this.n) {
                D();
            }
        }
    }

    private boolean l() {
        return "train".equalsIgnoreCase(x().e("sst"));
    }

    private void n() throws SpeechError, IOException, InterruptedException {
        cb.a("record stop msg in");
        if (!l()) {
            F();
        }
        this.d.a();
        a(4);
        cb.a("record stop msg out");
    }

    private void o() throws SpeechError, UnsupportedEncodingException {
        switch (this.d.e()) {
            case hasResult:
                E();
                return;
            default:
                return;
        }
    }

    protected void a(Message message) throws Throwable, SpeechError {
        super.a(message);
        switch (message.what) {
            case 0:
                b();
                return;
            case 1:
                h();
                return;
            case 2:
                b(message);
                return;
            case 3:
                n();
                return;
            case 4:
                c(message);
                return;
            case 9:
                i();
                return;
            default:
                return;
        }
    }

    protected void a(SpeechError speechError) {
        cb.a("isv msc onEnd in");
        F();
        f();
        cc.a("SessionEndBegin", null);
        if (this.u) {
            this.d.a("user abort");
        } else if (speechError != null) {
            this.d.a("error" + speechError.getErrorCode());
        } else {
            this.d.a(ANConstants.SUCCESS);
        }
        cc.a("SessionEndEnd", null);
        super.a(speechError);
        if (!(this.a == null || this.u)) {
            cb.a("VerifyListener#onEnd");
            if (speechError != null) {
                Bundle bundle = new Bundle();
                bundle.putString(SpeechEvent.KEY_EVENT_SESSION_ID, f());
                this.a.onEvent(20001, 0, 0, bundle);
                this.a.onError(speechError);
            }
        }
        this.a = null;
        cb.a("isv msc onEnd out");
    }

    public synchronized void a(VerifierListener verifierListener) {
        cb.a("Isv Msc startVerify in");
        this.a = verifierListener;
        a_();
        cb.a("Isv Msc startVerify out");
    }

    public void a(byte[] bArr, int i) {
        if (v()) {
            this.a.onVolumeChanged(i, bArr);
        }
    }

    protected void a(byte[] bArr, boolean z) throws SpeechError {
        this.d.a(bArr, bArr.length);
        if (!z) {
            return;
        }
        if (this.d.b()) {
            i();
        } else {
            a(bArr, this.d.c());
        }
    }

    public synchronized boolean a() {
        boolean z;
        cb.a("Isv Msc stopRecord in");
        if (w() != b.recording) {
            cb.a("endVerify fail  status is :" + w());
            z = false;
        } else {
            if (!l()) {
                F();
            }
            a(3);
            cb.a("Isv Msc stopRecord out");
            z = true;
        }
        return z;
    }

    protected void b() throws Exception {
        cb.a("isv msc msg start in");
        String e = x().e("engine_type");
        boolean a = x().a(SpeechConstant.NET_CHECK, true);
        if ("cloud".equals(e) && a) {
            bz.a(this.t);
        }
        int a2 = x().a("record_read_rate", 40);
        if (this.j != -1 && v()) {
            cb.a("start  record");
            if (this.e == null) {
                this.n = x().a(SpeechConstant.BLUETOOTH, this.n);
                if (this.n) {
                    C();
                }
                this.e = new PcmRecorder(t(), a2, this.j);
                this.e.startRecording(this);
            }
        }
        if (!(w() == b.exiting || this.a == null)) {
            this.a.onBeginOfSpeech();
        }
        this.b = SystemClock.elapsedRealtime();
        removeMessages(9);
        a(9, a.normal, false, this.r);
        a(1, a.max, false, 0);
        cb.a("isv msc msg start out");
    }

    protected void b(Message message) throws Exception {
        byte[] bArr = (byte[]) message.obj;
        if (bArr != null && bArr.length != 0) {
            this.i.add(bArr);
            a(bArr, true);
        }
    }

    public void b(boolean z) {
        if (z && v() && this.a != null) {
            this.a.onError(new SpeechError(20017));
        }
        F();
        super.b(z);
    }

    protected void c() {
        this.r = x().a(SpeechConstant.KEY_SPEECH_TIMEOUT, this.r);
        this.g = x().e(SpeechConstant.ISV_VID);
        this.j = x().a(SpeechConstant.AUDIO_SOURCE, 1);
        this.l = x().a(SpeechConstant.FILTER_AUDIO_TIME, 0) * (((x().a("sample_rate", this.s) / 1000) * 16) / 8);
        cb.a("mSpeechTimeOut=" + this.r);
        super.c();
    }

    void c(Message message) throws SpeechError, InterruptedException, UnsupportedEncodingException {
        if (!l()) {
            F();
        }
        o();
        if (w() == b.waitresult) {
            a(4, a.normal, false, 20);
        }
    }

    public String e() {
        return this.d.g();
    }

    public String f() {
        if (TextUtils.isEmpty(this.m)) {
            this.m = this.d.f();
        }
        return this.m;
    }

    public String g() {
        return SpeechConstant.ENG_IVP;
    }

    protected void h() throws Exception {
        if (this.d.a == null) {
            cc.a("SDKSessionBegin", null);
            this.d.a(this.t, this.g, (be) this);
        }
        a(b.recording);
    }

    public void i() {
        if (b.recording == w()) {
            cb.a("Isv Msc vadEndCall");
            a();
            if (this.a != null) {
                this.a.onEndOfSpeech();
            }
        }
    }

    public ConcurrentLinkedQueue<byte[]> j() {
        return this.i;
    }

    public int k() {
        return this.j;
    }

    public void onError(SpeechError speechError) {
        b(speechError);
    }

    public void onRecordBuffer(byte[] bArr, int i, int i2) {
        if (b.recording != w() || i2 <= 0) {
            return;
        }
        Object obj;
        if (this.l <= 0) {
            obj = new byte[i2];
            System.arraycopy(bArr, i, obj, 0, i2);
            d(obtainMessage(2, obj));
        } else if (this.l >= i2) {
            this.l -= i2;
        } else {
            obj = new byte[(i2 - this.l)];
            System.arraycopy(bArr, this.l + i, obj, 0, i2 - this.l);
            d(obtainMessage(2, obj));
            this.l = 0;
        }
    }

    public void onRecordReleased() {
        if (this.e != null && (this.e instanceof a)) {
            a();
        }
    }

    public void onRecordStarted(boolean z) {
        cb.a("time cost: onRecordStarted:" + (SystemClock.elapsedRealtime() - this.k));
    }
}

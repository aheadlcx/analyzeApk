package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import com.iflytek.cloud.IdentityListener;
import com.iflytek.cloud.IdentityResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechEvent;
import com.iflytek.msc.MSC;
import com.meizu.cloud.pushsdk.networking.common.ANConstants;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import org.json.JSONObject;

public class bd extends be {
    public static int f = 0;
    public static int g = 0;
    private static final String k = bd.class.getSimpleName();
    protected volatile IdentityListener a;
    protected boolean b;
    protected boolean c;
    protected boolean d;
    protected bc e;
    protected String h;
    protected bg i;
    long j;
    private boolean l;
    private bs m;
    private HashMap<String, ce> n;
    private HashMap<String, StringBuffer> o;
    private HashMap<String, Boolean> p;
    private String q;
    private int z;

    public bd(Context context, ce ceVar, HandlerThread handlerThread) {
        super(context, handlerThread);
        this.l = true;
        this.a = null;
        this.b = false;
        this.c = false;
        this.d = false;
        this.e = new bc();
        this.h = null;
        this.i = new bg();
        this.n = null;
        this.o = null;
        this.q = null;
        this.z = 0;
        this.j = 0;
        this.m = new bs();
        this.d = false;
        this.n = new HashMap();
        this.o = new HashMap();
        this.p = new HashMap();
        a(ceVar);
    }

    private void a(boolean z, byte[] bArr) throws SpeechError, UnsupportedEncodingException {
        this.v = SystemClock.elapsedRealtime();
        if (bArr == null || bArr.length <= 0) {
            throw new SpeechError(10118);
        }
        String replace = new String(bArr, "utf-8").replace("\"return\"", "\"ret\"");
        this.h = replace;
        if (this.a != null && v()) {
            Bundle bundle = new Bundle();
            bundle.putString(SpeechEvent.KEY_EVENT_SESSION_ID, f());
            this.a.onEvent(20001, 0, 0, bundle);
            IdentityResult identityResult = new IdentityResult(replace);
            cc.a("GetNotifyResult", null);
            this.a.onResult(identityResult, z);
        }
        cb.a("msc result time:" + System.currentTimeMillis());
        if (z) {
            b(null);
        }
    }

    private void e(Message message) throws SpeechError, IOException, InterruptedException {
        cb.a("recording stop");
        this.i.a("app_lau");
        this.p.put((String) message.obj, Boolean.valueOf(true));
        this.e.b((String) message.obj);
        p();
    }

    public bs a() {
        return this.m;
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
                e(message);
                return;
            case 4:
                c(message);
                return;
            case 7:
                i();
                return;
            case 9:
                j();
                return;
            default:
                return;
        }
    }

    public synchronized void a(IdentityListener identityListener) {
        this.a = identityListener;
        cb.a("startWorking called");
        a_();
    }

    protected void a(SpeechError speechError) {
        cb.a("onSessionEnd");
        f = this.e.c("upflow");
        g = this.e.c("downflow");
        f();
        if (this.h == null && speechError == null && x().a(SpeechConstant.ASR_NOMATCH_ERROR, true)) {
            speechError = new SpeechError(10118);
        }
        if (speechError != null) {
            this.i.a("app_ret", (long) speechError.getErrorCode(), false);
        } else {
            this.i.a("app_ret", 0, false);
        }
        this.i.a("rec_ustop", this.d ? "1" : "0", false);
        this.e.a("sessinfo", this.i.a());
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
        if (this.a != null) {
            if (!(this.u || speechError == null)) {
                this.a.onError(speechError);
            }
            this.a.onEvent(SpeechEvent.EVENT_SESSION_END, 0, 0, null);
        }
        this.a = null;
    }

    public void a(String str, String str2, byte[] bArr, int i, int i2) {
        if (!this.n.containsKey(str)) {
            ce ceVar = new ce();
            ceVar.a(str2);
            ceVar.a("sst", x().e("sst"), false);
            ceVar.a("mver", "2.0", false);
            ceVar.a("ssub", str);
            this.l = ceVar.a("vad_enable", true);
            this.n.put(str, ceVar);
            this.o.put(str, new StringBuffer(ceVar.toString()));
        }
        if (v()) {
            if (!this.b) {
                this.b = true;
                this.i.a("rec_start");
            }
            HashMap hashMap = new HashMap();
            hashMap.put("ssub", str);
            Object obj = null;
            if (bArr != null) {
                obj = new byte[i2];
                System.arraycopy(bArr, i, obj, 0, i2);
            }
            hashMap.put("data", obj);
            d(obtainMessage(2, hashMap));
        }
    }

    protected void a(StringBuffer stringBuffer, byte[] bArr, boolean z, boolean z2) throws SpeechError {
        this.e.a(stringBuffer, bArr, bArr == null ? 0 : bArr.length, z);
        if (z2) {
            int a = this.e.a();
            cb.b("QISRAudioWrite volume:" + a);
            a(bArr, a);
        }
    }

    public void a(byte[] bArr, int i) {
        if (this.a != null && v()) {
            this.a.onEvent(SpeechEvent.EVENT_VOLUME, i, 0, null);
        }
    }

    public synchronized boolean a(String str, boolean z) {
        cb.a("stopRecognize, current status is :" + w() + " usercancel : " + z);
        this.i.a("app_stop");
        this.d = z;
        d(obtainMessage(3, str));
        return true;
    }

    protected void a_() {
        this.i.a(x());
        super.a_();
    }

    protected void b() throws Exception {
        cb.a("start connecting");
        a(1, a.max, false, 0);
    }

    protected void b(Message message) throws Exception {
        HashMap hashMap = (HashMap) message.obj;
        byte[] bArr = (byte[]) hashMap.get("data");
        String str = (String) hashMap.get("ssub");
        StringBuffer stringBuffer = (StringBuffer) this.o.get(str);
        Object e = ((ce) this.n.get(str)).e(SpeechConstant.MFV_DATA_PATH);
        if (!(TextUtils.isEmpty(e) || bArr == null)) {
            this.m.a(e, bArr);
        }
        Boolean bool = (Boolean) this.p.get(str);
        if (bool == null) {
            bool = Boolean.valueOf(true);
        }
        if (SpeechConstant.ENG_IVP.equals(str) && this.l) {
            a(stringBuffer, bArr, bool.booleanValue(), true);
        } else {
            a(stringBuffer, bArr, bool.booleanValue(), false);
        }
        if (bool.booleanValue()) {
            this.p.put(str, Boolean.valueOf(false));
        }
    }

    public void b(boolean z) {
        if (z && v() && this.a != null) {
            cb.a("cancel");
            this.a.onError(new SpeechError(20017));
        }
        if (w() == b.recording) {
            this.d = true;
        }
        super.b(z);
    }

    protected void c() {
        this.r = x().a(SpeechConstant.KEY_SPEECH_TIMEOUT, this.r);
        cb.a("mSpeechTimeOut=" + this.r);
        super.c();
    }

    void c(Message message) throws SpeechError, InterruptedException, UnsupportedEncodingException {
        byte[] bArr = (byte[]) message.obj;
        switch (message.arg1) {
            case 0:
                if (!this.c) {
                    this.c = true;
                    this.i.a("app_frs");
                }
                a(false, bArr);
                return;
            case 5:
                if (!this.c) {
                    this.c = true;
                    this.i.a("app_frs");
                }
                this.i.a("app_lrs");
                a(true, bArr);
                return;
            default:
                return;
        }
    }

    public String e() {
        return this.e.g();
    }

    void errCb(char[] cArr, int i, byte[] bArr) {
        cb.a(k, "clientSessionID:" + new String(cArr) + "errorcode:" + i);
        Bundle bundle = new Bundle();
        bundle.putString(SpeechEvent.KEY_EVENT_SESSION_ID, f());
        this.a.onEvent(20001, 0, 0, bundle);
        b(new SpeechError(i));
    }

    public String f() {
        if (TextUtils.isEmpty(this.q)) {
            this.q = this.e.b();
        }
        return this.q;
    }

    public String g() {
        return "mfv";
    }

    protected void h() throws Exception {
        if (x().a(SpeechConstant.NET_CHECK, true)) {
            bz.a(this.t);
        }
        cc.a("SDKSessionBegin", null);
        this.i.a("app_ssb");
        int a = this.e.a(this.t, null, (be) this);
        if (a != 0 || this.e.a == null) {
            this.z++;
            if (this.z > 40) {
                throw new SpeechError(a);
            } else if (v()) {
                Thread.sleep(15);
                a(1, a.max, false, 0);
            }
        } else if (v()) {
            MSC.QMFVRegisterNotify(this.e.a, "rsltCb", "stusCb", "errCb", this);
            a(b.recording);
            if (x().a(SpeechConstant.ASR_NET_PERF, false)) {
                a(7, a.max, false, 0);
            }
        }
    }

    public void i() {
        if (v()) {
            int c = this.e.c("netperf");
            if (this.a != null) {
                this.a.onEvent(10001, c, 0, null);
            }
            a(7, a.normal, false, 100);
        }
    }

    public void j() {
        if (b.recording == w()) {
            cb.a("mfv msc vadEndCall");
            if (this.a != null) {
                this.a.onEvent(SpeechEvent.EVENT_VAD_EOS, 0, 0, null);
            }
            a(SpeechConstant.ENG_IVP, false);
        }
    }

    public bg k() {
        return this.i;
    }

    void rsltCb(char[] cArr, byte[] bArr, int i, int i2) {
        if (bArr != null) {
            cb.a(k, "rsltCb:" + i2 + "result:" + new String(bArr));
        } else {
            cb.b(k, "rsltCb:" + i2 + "result:null");
        }
        Message obtainMessage = obtainMessage(4, i2, 0, bArr);
        if (hasMessages(4)) {
            a(obtainMessage, a.normal, false, 0);
        } else {
            a(obtainMessage, a.max, false, 0);
        }
    }

    void stusCb(char[] cArr, int i, int i2, byte[] bArr, int i3) {
        cb.a(k, "stusCb:" + i2 + ",type:" + i);
        if (i == 0 && 3 == i2) {
            j();
        }
        if (1 == i) {
            String[] split = x().b(SpeechConstant.MFV_SCENES, "").split("\\|");
            if (split == null || split.length >= 2) {
                try {
                    String str = new String(bArr, "utf-8");
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("sub", str);
                    jSONObject.put("sret", i2);
                    jSONObject.put("ret", i2);
                    jSONObject.put("sst", ((ce) this.n.get(str)).e("sst"));
                    if (this.a != null) {
                        this.a.onResult(new IdentityResult(jSONObject.toString()), true);
                    }
                } catch (Throwable e) {
                    cb.a(e);
                } catch (Throwable e2) {
                    cb.a(e2);
                }
            }
        }
    }
}

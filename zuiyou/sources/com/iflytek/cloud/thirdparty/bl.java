package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.meizu.cloud.pushsdk.networking.common.ANConstants;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class bl extends be {
    public static int a = 0;
    public static int b = 0;
    private String c;
    private bo d;
    private bm e;
    private ArrayList<byte[]> f;
    private int g;
    private int h;
    private boolean i;
    private boolean j;
    private int k;
    private final JSONObject l;
    private JSONArray m;
    private int n;
    private int o;
    private String p;
    private int q;

    public bl(Context context, ce ceVar, HandlerThread handlerThread) {
        super(context, handlerThread);
        this.c = "";
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = 0;
        this.h = 0;
        this.i = false;
        this.j = false;
        this.k = 0;
        this.l = new JSONObject();
        this.m = null;
        this.n = -1;
        this.o = 100;
        this.p = null;
        this.q = 0;
        this.d = new bo();
        this.f = new ArrayList();
        a(ceVar);
    }

    private void i() throws SpeechError, JSONException {
        String str = null;
        int min = Math.min(this.o - 1, (this.g * this.o) / this.c.length());
        if (this.j) {
            this.l.put("audio_len", this.k);
        }
        if (this.m != null) {
            this.l.put("spell_info", this.m);
            this.m = null;
        }
        bm bmVar = this.e;
        ArrayList arrayList = this.f;
        int i = this.h;
        int i2 = this.g;
        if (this.l.length() > 0) {
            str = this.l.toString();
        }
        bmVar.a(arrayList, min, i, i2, str);
        this.f = new ArrayList();
        this.h = Math.min(this.g + 1, this.c.length() - 1);
    }

    protected void a() throws Exception {
        cb.a("tts msg start:" + System.currentTimeMillis());
        String e = x().e("engine_type");
        boolean a = x().a(SpeechConstant.NET_CHECK, true);
        if (("cloud".equals(e) || SpeechConstant.TYPE_DISTRIBUTED.equals(e)) && a) {
            bz.a(this.t);
        }
        a(1);
    }

    protected void a(Message message) throws Throwable, SpeechError {
        switch (message.what) {
            case 0:
                a();
                return;
            case 1:
                b();
                return;
            case 5:
                h();
                return;
            default:
                return;
        }
    }

    protected void a(SpeechError speechError) {
        a = this.d.b("upflow");
        b = this.d.b("downflow");
        f();
        cc.a("SessionEndBegin", null);
        if (this.e == null) {
            this.d.a("user abort");
        } else if (speechError != null) {
            this.d.a("error" + speechError.getErrorCode());
            cb.a("QTts Error Code = " + speechError.getErrorCode());
        } else {
            this.d.a(ANConstants.SUCCESS);
        }
        cc.a("SessionEndEnd", null);
        super.a(speechError);
        if (this.e == null) {
            return;
        }
        if (this.u) {
            cb.a("MscSynthesizer#onCancel");
            return;
        }
        cb.a("MscSynthesizer#onEnd");
        this.e.a(speechError);
    }

    public void a(String str, bm bmVar) {
        this.c = str;
        this.e = bmVar;
        if (str == null || TextUtils.isEmpty(str)) {
            b(new SpeechError(20009));
            return;
        }
        this.i = x().a(SpeechConstant.TTS_SPELL_INFO, false);
        this.j = x().a("audio_info", this.j);
        a_();
    }

    protected void b() throws Exception {
        int i = 0;
        cc.a("SDKSessionBegin", null);
        int a = this.d.a(this.t, null, this);
        if (a == 0) {
            byte[] bytes = this.c.getBytes(r());
            if ("unicode".equals(r())) {
                byte[] bArr = new byte[(bytes.length - 2)];
                System.arraycopy(bytes, 2, bArr, 0, bytes.length - 2);
                if (VERSION.SDK_INT >= 27) {
                    while (i < (bytes.length - 2) / 2) {
                        bArr[(i * 2) + 1] = bytes[i * 2];
                        bArr[i * 2] = bytes[(i * 2) + 1];
                        i++;
                    }
                }
                this.d.a(bArr);
            } else {
                this.d.a(bytes);
            }
            a(b.waitresult);
            a(5);
            p();
            return;
        }
        this.q++;
        if (this.q > 40) {
            throw new SpeechError(a);
        } else if (v()) {
            a(1, a.normal, false, 15);
        }
    }

    public void b(boolean z) {
        if (z && v() && this.e != null) {
            this.e.a(new SpeechError(20017));
        }
        super.b(z);
    }

    protected void c() {
        this.n = x().a(SpeechConstant.TTS_BUFFER_TIME, this.n);
        this.o = x().a("tts_proc_scale", this.o);
        super.c();
    }

    public String e() {
        return this.d.g();
    }

    public String f() {
        if (TextUtils.isEmpty(this.p)) {
            this.p = this.d.e();
        }
        return this.p;
    }

    public String g() {
        return "tts";
    }

    protected void h() throws Exception {
        cc.a("GetNotifyResult", null);
        if (this.d.d()) {
            cb.a("tts msc get last audio");
            if (this.e != null) {
                if (this.j) {
                    this.l.put("audio_len", this.k);
                }
                if (this.m != null) {
                    this.l.put("spell_info", this.m);
                    this.m = null;
                }
                this.e.a(this.f, this.o, this.h, this.c.length() - 1, this.l.length() > 0 ? this.l.toString() : null);
            }
            b(null);
            return;
        }
        Object a = this.d.a();
        f();
        if (a == null || this.e == null) {
            a(5, a.normal, false, 10);
            return;
        }
        this.k += a.length;
        int b = (this.d.b() / 2) - 1;
        if (b < 0) {
            cb.a("get audio index value error: " + b);
            b = 0;
        }
        if (this.i) {
            CharSequence c = this.d.c();
            if (!TextUtils.isEmpty(c)) {
                if (this.m == null) {
                    this.m = new JSONArray();
                }
                this.m.put(c);
            }
        }
        if (this.n < 0 && this.g != 0 && b != this.g && this.f.size() > 0) {
            cb.b("tts msc get audio beg=" + this.h + ", end=" + this.g);
            i();
        }
        p();
        this.g = b;
        this.f.add(a);
        if (this.n >= 0) {
            i();
        }
        a(5, a.normal, false, 0);
    }

    public String r() {
        return x().b(SpeechConstant.TEXT_ENCODING, "unicode");
    }
}

package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.util.AudioDetector;
import com.iflytek.cloud.util.AudioDetector.DetectorResult;
import com.iflytek.msc.MetaVAD;
import com.iflytek.msc.MetaVAD.Instance;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.util.HashMap;
import java.util.Map;

public class cy extends AudioDetector {
    private static final Map<String, String> c = new HashMap();
    private static final Map<String, String> d = new HashMap();
    private ce e = new ce();
    private final DetectorResult f = new DetectorResult();
    private Instance g = new Instance();
    private String h = "gb2312";
    private boolean i = false;
    private boolean j = true;
    private int k = 0;
    private int l = 0;
    private int m = 2;
    private long n = -1;
    private long o = 0;

    static {
        c.put("vad_bos", "vad_starttimeout");
        c.put("vad_eos", "vad_endtimeout");
        c.put("threshold", "vad_threshold");
        d.put("vad_bos", String.valueOf(2000));
        d.put("vad_eos", String.valueOf(700));
        d.put("threshold", String.valueOf(0.6f));
    }

    public cy(Context context, String str) {
        super(context, str);
        cb.a("Meta VAD AudioDetector constructor enter, context: " + context + ", param: " + str);
        this.e.a(str);
        try {
            this.h = this.e.b(SpeechConstant.TEXT_ENCODING, this.h);
            String e = this.e.e(PushConstants.EXTRA);
            byte[] a = e != null ? bt.a(e, this.h) : null;
            cb.a("MetaVAD.VADInitialize begin.");
            int VADInitialize = MetaVAD.VADInitialize(a);
            if (VADInitialize == 0) {
                this.g.rate = this.e.a("sample_rate", 16000);
                e = this.e.e(AudioDetector.RES_PATH);
                a = e != null ? bt.a(e, this.h) : null;
                cb.a("MetaVAD.VADLoadResource begin.");
                VADInitialize = MetaVAD.VADLoadResource(this.g.rate, a);
                if (VADInitialize == 0) {
                    cb.a("MetaVAD.VADCreateSession begin.");
                    VADInitialize = MetaVAD.VADCreateSession(this.g);
                }
            }
            if (VADInitialize != 0) {
                cb.c("MetaVAD Native error " + VADInitialize);
            }
        } catch (Throwable th) {
            cb.c("Meta VAD AudioDetector constructor exception:");
            cb.a(th);
        }
        cb.a("Meta VAD AudioDetector constructor leave");
    }

    private void a() {
        this.f.buffer = null;
        this.f.end = 0;
        this.f.error = 0;
        this.f.length = 0;
        this.f.offset = 0;
        this.f.quality = 0;
        this.f.start = 0;
        this.f.status = 0;
        this.f.sub = 0;
        this.f.subs.clear();
        this.f.voice = false;
        this.f.volume = 0;
        this.f.confidence = 1.0f;
        if (this.g != null) {
            this.g.a();
        }
        this.k = 0;
    }

    private void a(int i) {
        int i2 = 2;
        switch (i) {
            case 0:
            case 6:
                this.f.error = 0;
                this.g.seg = 0;
                break;
            case 1:
            case 2:
                this.f.sub = 1;
                break;
            case 3:
                this.f.sub = 2;
                break;
            case 4:
                DetectorResult detectorResult = this.f;
                if (!this.i) {
                    i2 = 3;
                }
                detectorResult.status = i2;
                break;
            case 5:
                this.f.sub = 3;
                break;
            default:
                this.f.error = i;
                break;
        }
        if (!(this.i || this.f.sub == 0)) {
            this.i = true;
            if (this.f.status == 0) {
                this.f.status = 1;
            }
        }
        if (this.f.status == 0 && c()) {
            this.f.status = 4;
        }
    }

    private void b() {
        if (this.g.seg != 0) {
            Integer num = (Integer) this.f.subs.put(Integer.valueOf(this.g.begin), Integer.valueOf(this.g.end));
            if (num != null) {
                cb.c("update result error: repeat sub begin: " + num);
                int i = this.k + 1;
                this.k = i;
                if (10 <= i) {
                    this.f.error = 10100;
                    cb.c("update result error: repeat sub reach max count.");
                }
            }
            this.f.sub = 3;
            if (1 == this.g.seg || (this.j && 3 == this.g.seg)) {
                this.f.start = this.g.begin;
                this.l = this.f.start;
            }
            if (3 == this.g.seg) {
                this.f.end = this.g.end;
                this.f.start = this.l;
                this.f.confidence = MetaVAD.VADGetSentConfidence(this.g);
            }
            this.j = false;
        }
        this.f.quality = 0;
        this.f.voice = false;
        this.f.volume = this.g.volume * 4;
    }

    private boolean c() {
        return 0 < this.n && this.n <= this.o;
    }

    public boolean destroy() {
        boolean z = true;
        cb.a("destroy enter");
        synchronized (b) {
            try {
                if (this.g != null) {
                    int VADDestroySession;
                    if (0 != this.g.handle) {
                        cb.a("destroy MetaVAD.VADDestroySession begin");
                        VADDestroySession = MetaVAD.VADDestroySession(this.g);
                        cb.a("destroy MetaVAD.VADDestroySession ret=" + VADDestroySession);
                    } else {
                        VADDestroySession = 0;
                    }
                    if (VADDestroySession == 0) {
                        this.g.handle = 0;
                        cb.a("destroy MetaVAD.VADDelResource begin");
                        cb.a("destroy MetaVAD.VADDelResource ret=" + MetaVAD.VADDelResource(this.g.rate));
                        cb.a("destroy MetaVAD.VADUninitialize begin");
                        VADDestroySession = MetaVAD.VADUninitialize();
                        cb.a("destroy MetaVAD.VADUninitialize ret=" + VADDestroySession);
                    }
                    if (VADDestroySession != 0) {
                        z = false;
                    }
                    if (z) {
                        this.g = null;
                        a = null;
                    }
                }
            } catch (Throwable th) {
                cb.c("destroy exception:");
                cb.a(th);
                z = false;
            }
        }
        cb.a("destroy leave: " + z);
        return z;
    }

    public DetectorResult detect(byte[] bArr, int i, int i2, boolean z) {
        cb.b("detect enter, buffer: " + bArr + ", offset: " + i + ", length: " + i2 + ", isLast: " + z);
        synchronized (b) {
            try {
                a();
                if (this.g == null || 0 == this.g.handle) {
                    cb.c("detect error: vad instance null, or handle is invalid!");
                    this.f.error = 21003;
                } else {
                    if (bArr != null && i2 > 0 && 32768 >= i2 && i >= 0) {
                        if (bArr.length - i >= i2) {
                            cb.b("detect buffer length: " + i2);
                        }
                    }
                    if (!z) {
                        this.f.error = 20012;
                    }
                }
                if (this.f.error == 0) {
                    int VADAppendPCM = MetaVAD.VADAppendPCM(this.g, bArr, i, i2, z ? 1 : 0);
                    cb.b("MetaVAD VADAppendPCM ret: " + VADAppendPCM);
                    if (this.i) {
                        this.o += (long) i2;
                    }
                    a(VADAppendPCM);
                    if (this.f.error == 0) {
                        VADAppendPCM = 5;
                        while (5 == VADAppendPCM) {
                            VADAppendPCM = MetaVAD.VADGetSeg(this.g);
                            cb.b("MetaVAD VADGetSeg ret: " + VADAppendPCM + ", seg status: " + this.g.seg + ", seg begin: " + this.g.begin + ", seg end: " + this.g.end);
                            a(VADAppendPCM);
                            if (this.f.error == 0) {
                                b();
                                this.f.buffer = bArr;
                                this.f.length = i2;
                                this.f.offset = i;
                            }
                            if (3 != this.g.seg) {
                                if (this.f.error != 0) {
                                }
                            }
                            cb.a("detect get last seg or error.");
                            break;
                        }
                    }
                }
            } catch (Throwable e) {
                cb.c("detect exception");
                cb.a(e);
                a();
                this.f.error = 20021;
            } catch (Throwable e2) {
                cb.c("detect exception");
                cb.a(e2);
                a();
                this.f.error = 20999;
            }
        }
        cb.b("detect leave");
        return this.f;
    }

    public void reset() {
        cb.a("reset enter");
        synchronized (b) {
            if (this.g == null || 0 == this.g.handle) {
                cb.c("setParameter error: vad instance is null, or invalid handle.");
            } else {
                try {
                    cb.a("reset MetaVAD.VADResetSession begin");
                    cb.a("reset MetaVAD.VADResetSession return " + MetaVAD.VADResetSession(this.g));
                    this.g.a();
                    this.j = true;
                    this.i = false;
                    this.o = 0;
                    this.l = 0;
                } catch (Throwable th) {
                    cb.c("reset exception:");
                    cb.a(th);
                }
            }
        }
        cb.a("reset leave");
    }

    public void setParameter(String str, String str2) {
        long j = -1;
        cb.a("setParameter enter, key: " + str + ", value: " + str2);
        synchronized (b) {
            if (this.g == null || 0 == this.g.handle) {
                cb.c("setParameter error: vad instance is null, or invalid handle.");
            } else {
                try {
                    if (AudioDetector.RESET_SENTENCE.equalsIgnoreCase(str)) {
                        cb.a("VAD VADResetSentence ret: " + MetaVAD.VADResetSentence(this.g));
                    } else if (!TextUtils.isEmpty(str) && c.containsKey(str)) {
                        if (TextUtils.isEmpty(str2)) {
                            this.e.d(str);
                        } else {
                            this.e.a(str, str2);
                        }
                        String b = this.e.b(str, (String) d.get(str));
                        String str3 = (String) c.get(str);
                        cb.a("VAD SetParameter key=" + str3 + ", value=" + b + ", ret: " + MetaVAD.VADSetParam(this.g, bt.a(str3, this.h), bt.a(b, this.h)));
                    } else if (!TextUtils.isEmpty(str)) {
                        if (SpeechConstant.KEY_SPEECH_TIMEOUT.equalsIgnoreCase(str)) {
                            try {
                                j = Long.parseLong(str2);
                            } catch (Throwable e) {
                                cb.a(e);
                            }
                            cb.a("SetParameter speech timeout value:" + j);
                            if (0 < j) {
                                this.n = (j * ((long) (this.g.rate * this.m))) / 1000;
                                cb.a("SetParameter BytesOfSpeechTimeout: " + this.n);
                            } else {
                                this.n = -1;
                            }
                        } else {
                            cb.a("VAD SetParameter name=" + str + ", value=" + str2 + ", ret: " + MetaVAD.VADSetParam(this.g, bt.a(str, this.h), bt.a(str2, this.h)));
                        }
                    }
                } catch (Throwable th) {
                    cb.c("setParameter exception");
                    cb.a(th);
                }
            }
        }
        cb.a("setParameter leave.");
    }
}

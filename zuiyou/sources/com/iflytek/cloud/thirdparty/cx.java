package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.util.AudioDetector;
import com.iflytek.cloud.util.AudioDetector.DetectorResult;
import com.iflytek.msc.VAD;
import com.iflytek.msc.VAD.VadData;
import java.util.HashMap;
import java.util.Map;

public class cx extends AudioDetector {
    private static final Map<String, Integer> c = new HashMap();
    private static final Map<String, Integer> d = new HashMap();
    private ce e = new ce();
    private long f = 0;
    private DetectorResult g = new DetectorResult();
    private VadData h = new VadData();
    private byte[] i = new byte[32768];
    private byte[] j = new byte[32784];
    private boolean k = true;
    private int l = 2;
    private long m = -1;
    private long n = 0;

    static {
        c.put("vad_bos", Integer.valueOf(0));
        c.put("vad_eos", Integer.valueOf(1));
        c.put(AudioDetector.SUB_TIMEOUT, Integer.valueOf(3));
        c.put(AudioDetector.EARLY_START, Integer.valueOf(4));
        d.put("vad_bos", Integer.valueOf(2000));
        d.put("vad_eos", Integer.valueOf(700));
        d.put(AudioDetector.SUB_TIMEOUT, Integer.valueOf(20000));
        d.put(AudioDetector.EARLY_START, Integer.valueOf(1));
    }

    public cx(Context context, String str) {
        super(context, str);
        cb.a("AudioDetector constructor enter, context: " + context + ", param: " + str);
        this.e.a(str);
        try {
            this.f = VAD.Initialize(this.e.a("sample_rate", 16000));
            cb.a("VAD Initialize ret: " + this.f);
        } catch (Throwable th) {
            cb.c("AudioDetector constructor exception");
            cb.a(th);
        }
        this.h.wavData = this.j;
    }

    private void a() {
        this.g.buffer = null;
        this.g.end = 0;
        this.g.error = 0;
        this.g.length = 0;
        this.g.offset = 0;
        this.g.quality = 0;
        this.g.start = 0;
        this.g.status = 0;
        this.g.sub = 0;
        this.g.voice = false;
        this.g.volume = 0;
        this.h.audioQuality = 0;
        this.h.endByte = 0;
        this.h.endRemainFrameNum = 0;
        this.h.firstOutByte = 0;
        this.h.inSpeech = 0;
        this.h.startByte = 0;
        this.h.startRemainFrameNum = 0;
        this.h.status = 0;
        this.h.volumeLevel = 0;
        this.h.waitPauseOrEnd = 0;
        this.h.waitStart = 0;
        this.h.wavData = this.j;
        this.h.wavDataSize = 0;
    }

    private void a(int i) {
        switch (i) {
            case 0:
            case 11:
                this.g.error = 0;
                break;
            case 5:
                this.g.sub = 1;
                break;
            case 6:
                this.g.sub = 2;
                break;
            case 7:
                this.g.sub = 3;
                break;
            case 8:
                this.g.status = 2;
                break;
            case 9:
                this.g.sub = 3;
                this.g.status = 2;
                break;
            case 10:
                this.g.status = 3;
                break;
            default:
                this.g.error = i;
                break;
        }
        if (this.k && this.g.sub != 0) {
            this.k = false;
            if (this.g.status == 0) {
                this.g.status = 1;
            }
        }
        if (this.g.status == 0 && c()) {
            this.g.status = 4;
        }
    }

    private void b() {
        boolean z = true;
        this.g.buffer = this.h.wavData;
        this.g.end = this.h.endByte;
        this.g.length = this.h.wavDataSize;
        this.g.offset = 0;
        this.g.quality = this.h.audioQuality;
        this.g.start = this.h.startByte;
        DetectorResult detectorResult = this.g;
        if (1 != this.h.inSpeech) {
            z = false;
        }
        detectorResult.voice = z;
        this.g.volume = this.h.volumeLevel;
    }

    private boolean c() {
        return 0 < this.m && this.m <= this.n;
    }

    public boolean destroy() {
        cb.a("destroy enter");
        boolean z = true;
        synchronized (b) {
            if (0 != this.f) {
                try {
                    VAD.Uninitialize(this.f);
                    cb.a("VAD Uninitialize");
                    this.f = 0;
                } catch (Throwable th) {
                    cb.c("destroy exception");
                    cb.a(th);
                    z = false;
                }
            }
        }
        a = null;
        cb.a("destroy leave");
        return z;
    }

    public DetectorResult detect(byte[] bArr, int i, int i2, boolean z) {
        synchronized (b) {
            cb.b("detect enter, buffer: " + bArr + ", offset: " + i + ", length: " + i2 + ", isLast: " + z);
            try {
                a();
                if (0 == this.f) {
                    cb.c("detect error: handle is invalid!");
                    this.g.error = 21003;
                } else {
                    int CalcVolumLevel;
                    if (bArr != null && i2 > 0 && 32768 >= i2 && i >= 0) {
                        if (bArr.length - i >= i2) {
                            System.arraycopy(bArr, i, this.i, 0, i2);
                            cb.b("buffer length: " + i2);
                            CalcVolumLevel = VAD.CalcVolumLevel(this.f, this.i, i2, this.h);
                            cb.b("VAD CalcVolumLevel ret: " + CalcVolumLevel);
                            this.g.error = CalcVolumLevel;
                            if (this.g.error == 0) {
                                CalcVolumLevel = VAD.AppendData(this.f, this.i, i2);
                                cb.b("VAD AppendData ret: " + CalcVolumLevel);
                                if (!this.k) {
                                    this.n += (long) i2;
                                }
                                a(CalcVolumLevel);
                                if (this.g.error == 0) {
                                    CalcVolumLevel = VAD.FetchData(this.f, this.h);
                                    cb.b("VAD FetchData ret: " + CalcVolumLevel);
                                    a(CalcVolumLevel);
                                    if (this.g.error == 0) {
                                        if (2 == this.g.status || 3 == this.g.status || z) {
                                            CalcVolumLevel = VAD.EndAudioData(this.f);
                                            cb.a("VAD EndAudioData ret: " + CalcVolumLevel);
                                            a(CalcVolumLevel);
                                            if (this.g.error == 0) {
                                                CalcVolumLevel = VAD.GetLastSpeechPos(this.f, this.h);
                                                cb.a("VAD GetLastSpeechPos ret: " + CalcVolumLevel);
                                                this.g.error = CalcVolumLevel;
                                            }
                                        }
                                        if (this.g.error == 0) {
                                            b();
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (z) {
                        CalcVolumLevel = VAD.EndAudioData(this.f);
                        cb.a("VAD EndAudioData ret: " + CalcVolumLevel);
                        a(CalcVolumLevel);
                        if (this.g.error == 0) {
                            this.g.error = VAD.GetLastSpeechPos(this.f, this.h);
                            cb.a("VAD GetLastSpeechPos ret: " + this.g.error);
                            if (this.g.error == 0) {
                                b();
                            }
                        }
                    } else {
                        this.g.error = 20012;
                    }
                }
            } catch (Throwable e) {
                cb.c("detect exception");
                cb.a(e);
                a();
                this.g.error = 20021;
            } catch (Throwable e2) {
                cb.c("detect exception");
                cb.a(e2);
                a();
                this.g.error = 20999;
            }
        }
        cb.a("detect leave");
        return this.g;
    }

    public void reset() {
        cb.a("reset enter");
        synchronized (b) {
            if (0 != this.f) {
                try {
                    VAD.Reset(this.f);
                    cb.a("VAD Reset");
                    this.k = true;
                    this.n = 0;
                } catch (Throwable th) {
                    cb.c("reset exception");
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
            int parseInt;
            int parseInt2;
            if (TextUtils.isEmpty(str) || !c.containsKey(str)) {
                try {
                    if (SpeechConstant.KEY_SPEECH_TIMEOUT.equalsIgnoreCase(str)) {
                        try {
                            j = Long.parseLong(str2);
                        } catch (NumberFormatException e) {
                        }
                        cb.a("SetParameter speech timeout value:" + j);
                        if (0 < j) {
                            this.m = (j * ((long) (this.e.a("sample_rate", 16000) * this.l))) / 1000;
                            cb.a("SetParameter BytesOfSpeechTimeout: " + this.m);
                        } else {
                            this.m = -1;
                        }
                    } else {
                        parseInt = Integer.parseInt(str);
                        parseInt2 = Integer.parseInt(str2);
                        cb.a("VAD SetParameter key=" + parseInt + ", value=" + parseInt2 + ", ret: " + VAD.SetParam(this.f, parseInt, parseInt2));
                    }
                } catch (Throwable th) {
                    cb.c("setParameter exception");
                    cb.a(th);
                }
            } else {
                if (TextUtils.isEmpty(str2)) {
                    this.e.d(str);
                } else {
                    this.e.a(str, str2);
                }
                parseInt2 = this.e.a(str, ((Integer) d.get(str)).intValue());
                parseInt = ((Integer) c.get(str)).intValue();
                cb.a("VAD SetParameter key=" + parseInt + ", value=" + parseInt2 + ", ret: " + VAD.SetParam(this.f, parseInt, parseInt2));
            }
        }
        cb.a("setParameter leave.");
    }
}

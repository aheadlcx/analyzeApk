package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.record.PcmRecorder;
import com.iflytek.cloud.record.a;
import com.iflytek.cloud.util.AudioDetector;
import com.iflytek.cloud.util.AudioDetector.DetectorResult;
import java.io.IOException;

public class ay extends ax {
    private AudioDetector A = null;
    private byte[] B = null;
    boolean q = false;
    private int z = 16000;

    public ay(Context context, ce ceVar, HandlerThread handlerThread) {
        int i = 16000;
        super(context, ceVar, handlerThread);
        ce x = x();
        if (x != null) {
            i = x.a("sample_rate", 16000);
        }
        this.z = i;
        x.a("vad_enable", "0", true);
        this.A = AudioDetector.getDetector();
        if (this.A == null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("sample_rate").append("=").append(this.z);
            stringBuffer.append(",").append(AudioDetector.VAD_ENGINE).append("=").append(x.b(AudioDetector.VAD_ENGINE, AudioDetector.TYPE_META));
            Object e = x != null ? x.e(AudioDetector.RES_PATH) : null;
            if (!TextUtils.isEmpty(e)) {
                stringBuffer.append(",").append(AudioDetector.RES_PATH).append("=").append(e);
            }
            this.A = AudioDetector.createDetector(context, stringBuffer.toString());
        }
        this.A.setParameter("vad_bos", Integer.toString(this.r > 0 ? this.r : Integer.MAX_VALUE));
        String b = x.b("vad_eos", Integer.toString(cg.b((be) this)));
        cb.a("meta vad eos on recog: " + b);
        this.A.setParameter("vad_eos", b);
        this.A.setParameter(SpeechConstant.KEY_SPEECH_TIMEOUT, Integer.toString(this.r));
    }

    private void E() throws Exception {
        if (SpeechUtility.getUtility() != null) {
            this.o.a("app_ssb");
            cb.a("begin session");
            i();
            return;
        }
        cb.c("not init while begin session");
        b(new SpeechError(20015));
    }

    private byte[] a(byte[] bArr) throws Exception {
        if (this.A != null) {
            int max;
            DetectorResult detectorResult;
            int min = Math.min(32768, bArr.length);
            int i = 0;
            int i2 = 0;
            DetectorResult detectorResult2 = null;
            boolean z = false;
            while (min > 0) {
                detectorResult2 = this.A.detect(bArr, i2, min, false);
                if (detectorResult2.error != 0) {
                    throw new SpeechError(detectorResult2.error);
                }
                if (3 == detectorResult2.status) {
                    this.A.reset();
                } else if (detectorResult2.status == 0) {
                    continue;
                } else {
                    if (!this.q) {
                        max = Math.max(i, i2 - this.B.length);
                        this.q = true;
                        cb.a("detectAudioData find start and begin session");
                        E();
                        i = max;
                        z = true;
                    }
                    if (1 != detectorResult2.status) {
                        detectorResult = detectorResult2;
                        break;
                    }
                }
                i2 += min;
                min = Math.min(32768, bArr.length - i2);
            }
            detectorResult = detectorResult2;
            if (detectorResult != null) {
                a(bArr, detectorResult.volume);
            }
            if (detectorResult == null || !this.q) {
                max = Math.min(this.B.length, bArr.length);
                System.arraycopy(this.B, max, this.B, 0, this.B.length - max);
                System.arraycopy(bArr, bArr.length - max, this.B, this.B.length - max, max);
                return null;
            }
            if (z) {
                Object obj = new byte[((bArr.length - i) + this.B.length)];
                if (this.B.length <= i) {
                    System.arraycopy(bArr, i - this.B.length, obj, 0, obj.length);
                } else {
                    System.arraycopy(this.B, i, obj, 0, this.B.length - i);
                    System.arraycopy(bArr, 0, obj, this.B.length - i, bArr.length);
                }
                bArr = obj;
            }
            if (2 != detectorResult.status && 4 != detectorResult.status) {
                return bArr;
            }
            cb.a("detectAudioData find eos or timeout");
            l();
            return bArr;
        }
        throw new SpeechError(22001);
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
    }

    protected void b(Message message) throws Exception {
        byte[] bArr = (byte[]) message.obj;
        if (bArr != null && bArr.length != 0) {
            Object a = a(bArr);
            if (a != null) {
                this.m.add(a);
                a(a, true);
            }
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
        a(b.recording);
        int i = ((this.z * 300) * 2) / 1000;
        cb.a("MscRecognizerMeta last buffer len: " + i);
        this.B = new byte[i];
        if (this.f != -1 && v()) {
            cb.a("start  record");
            if (this.f == -2) {
                this.i = new a(t(), a, this.f, x().e(SpeechConstant.ASR_SOURCE_PATH));
            } else {
                this.i = new PcmRecorder(t(), a, this.f);
                if (hasMessages(3)) {
                    throw new SpeechError(10118);
                }
            }
            this.o.a("rec_open");
            this.i.startRecording(this);
            this.r = x().a(SpeechConstant.KEY_SPEECH_TIMEOUT, -1);
            if (-1 != this.r) {
                a(9, a.normal, false, this.r);
            }
        }
        if (this.a != null && this.f > -1) {
            this.a.onBeginOfSpeech();
        }
        if (this.A == null) {
            throw new SpeechError(21003);
        }
        this.A.reset();
    }

    protected void j() throws SpeechError, IOException, InterruptedException {
        if (this.q) {
            this.o.a("app_lau");
            this.h.a();
            p();
            return;
        }
        cb.a("exit with no speech audio");
        b(null);
    }
}

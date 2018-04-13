package com.iflytek.cloud.thirdparty;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.aiui.AIUISetting;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.util.AudioDetector;
import com.iflytek.cloud.util.AudioDetector.DetectorResult;
import com.iflytek.cloud.util.ResourceUtil;
import com.iflytek.cloud.util.ResourceUtil.RESOURCE_TYPE;
import com.iflytek.cloud.util.VolumeUtil;
import com.tencent.connect.common.Constants;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ab extends u {
    private String d = AudioDetector.TYPE_META;
    private String e = "cloud";
    private com.iflytek.cloud.thirdparty.ak.a f;
    private v g;
    private aa h = new aa(15);
    private z i;
    private long j = 0;
    private aj k = new aj();
    private ce l;
    private String m = "";
    private long n = 0;
    private x o;
    private boolean p;
    private HandlerThread q = new HandlerThread("AIUI:VAD-DetectThread");
    private AudioDetector r;
    private a s;
    private aj t = new aj();
    private int u = 16000;
    private String v = AIUIConstant.WORK_MODE_INTENT;
    private String w = "0.6";
    private String x = "5000";
    private boolean y = true;
    private String z = Constants.DEFAULT_UIN;

    class a extends Handler {
        final /* synthetic */ ab a;
        private aa b = new aa(15);
        private boolean c = false;
        private boolean d = true;

        public a(ab abVar, Looper looper, boolean z) {
            this.a = abVar;
            super(looper);
            this.d = z;
            if (abVar.r != null) {
                abVar.r.reset();
            }
        }

        private void a(y yVar) {
            synchronized (this.a) {
                DetectorResult detect = (this.a.r == null || !this.a.a) ? null : yVar.a() == 0 ? this.a.r.detect(new byte[320], 0, 320, false) : this.a.r.detect(yVar.c, 0, yVar.a(), false);
            }
            if (detect != null) {
                if (detect.error != 0) {
                    this.a.a(detect.error, "Vad error!");
                    Log.e("VadUnit", "Vad error=" + detect.error);
                    return;
                }
                int i = detect.status;
                if (this.c) {
                    this.a.a(Message.obtain(this.a.c.b(), 6, 1, VolumeUtil.computeVolume(yVar.c, yVar.a())));
                    if (4 == yVar.d) {
                        i = 2;
                    }
                }
                y d;
                switch (i) {
                    case 0:
                        if (this.c) {
                            yVar.d = 2;
                            yVar.a("stream_id", this.a.m, true);
                            c(yVar);
                            break;
                        }
                        break;
                    case 1:
                        if (this.c) {
                            cb.a("VadUnit", "sub vad_bos found.");
                            yVar.d = 2;
                        } else {
                            this.c = true;
                            this.a.a(Message.obtain(this.a.c.b(), 6, 0, 0));
                            long e = this.a.n;
                            long c = this.b.c() / this.a.j;
                            this.a.m = al.a("audio");
                            d = this.b.d();
                            d.d = 1;
                            d.a("stream_id", this.a.m, true);
                            int a = d.a();
                            if (a > 0) {
                                cb.a("VadUnit", "audio before bos, length=" + a);
                                c(d);
                                yVar.d = 2;
                            } else {
                                yVar.d = 1;
                            }
                            this.a.a(e - c, "frame_bos", this.a.m);
                            cb.a("VadUnit", "vad_bos found, stmid=" + this.a.m);
                        }
                        yVar.a("stream_id", this.a.m, true);
                        c(yVar);
                        break;
                    case 3:
                        cb.a("VadUnit", "vad_bos timeout.");
                        this.a.a(Message.obtain(this.a.c.b(), 6, 3, 0));
                        if (!this.c) {
                            this.c = false;
                            this.a.r.reset();
                            break;
                        }
                    case 2:
                    case 4:
                        if (4 == i) {
                            cb.a("VadUnit", "speech timeout.");
                        } else if (2 == i) {
                            cb.a("VadUnit", "vad_eos found.");
                            this.a.a((((long) yVar.a()) / this.a.j) + this.a.n, "frame_eos", this.a.m);
                        }
                        if (this.c) {
                            this.a.a(Message.obtain(this.a.c.b(), 6, 2, 0));
                            yVar.a("stream_id", this.a.m, true);
                            yVar.a("confidence", String.valueOf(0.0f), true);
                            if (4 != yVar.d) {
                                yVar.d = 2;
                                c(yVar);
                                d = new y(new byte[0], "");
                                d.d = 4;
                                d.a("stream_id", this.a.m, true);
                                d.a("confidence", String.valueOf(detect.confidence), true);
                                c(d);
                            } else {
                                c(yVar);
                            }
                            this.c = false;
                            this.a.r.reset();
                            break;
                        }
                        return;
                }
                this.a.n = this.a.n + (((long) yVar.a()) / this.a.j);
                this.b.a(yVar);
            }
        }

        private void b(y yVar) {
            if (this.a.y) {
                this.a.a(Message.obtain(this.a.c.b(), 6, 1, VolumeUtil.computeVolume(yVar.c, yVar.a())));
            }
            Log.d("VadUnit", "direcetWrite blcok.dts =" + yVar.d);
            if (4 == yVar.d) {
                this.c = false;
                cb.a("VadUnit", "audio_end found.");
            } else if (this.c) {
                yVar.d = 2;
            } else {
                this.c = true;
                this.a.m = al.a("audio");
                yVar.d = 1;
                cb.a("VadUnit", "audio_start found, stmid=" + this.a.m);
            }
            yVar.a("stream_id", this.a.m, true);
            c(yVar);
        }

        private void c(y yVar) {
            if (1 == yVar.d) {
                this.a.g();
            }
            if (!AIUIConstant.WORK_MODE_REC_ONLY.equals(this.a.v)) {
                yVar.a(AIUIConstant.KEY_INTENT_ENGINE_TYPE, this.a.e, true);
                if (this.a.g != null) {
                    this.a.g.a((y) yVar.clone());
                }
                if (this.a.o != null) {
                    this.a.o.a(yVar);
                }
            }
        }

        public void a() {
            if (this.b != null) {
                this.b.a();
            }
        }

        public void b() {
            for (int i = 1; i < 3; i++) {
                removeMessages(i);
            }
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            y yVar;
            switch (message.what) {
                case 1:
                    yVar = (y) message.obj;
                    Object b = al.b();
                    if (!TextUtils.isEmpty(b)) {
                        yVar.a("wake_id", b, false);
                    }
                    if (!this.d || this.a.p) {
                        b(yVar);
                    } else {
                        a(yVar);
                    }
                    this.a.k.a(yVar.c, false);
                    if (this.a.f != null) {
                        try {
                            this.a.f.a(yVar.c, false);
                            return;
                        } catch (IOException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                    return;
                case 2:
                    yVar = new y(new byte[0], "");
                    yVar.d = 4;
                    yVar.a("stream_id", this.a.m, true);
                    c(yVar);
                    return;
                default:
                    return;
            }
        }
    }

    public ab(t tVar) {
        super("VadUnit", tVar);
        this.q.setPriority(10);
        this.q.start();
    }

    private int a(boolean z) {
        if (this.a) {
            cb.a("VadUnit", "VadUnit is already started.");
            return 0;
        }
        i();
        if (this.r == null) {
            Log.e("VadUnit", "VadUnit start failed, VadDetector is null.");
            return -1;
        }
        if (z) {
            this.n = 0;
            h();
        }
        this.s = new a(this, this.q.getLooper(), this.y);
        if (16000 == this.u) {
            this.i = new z(1280);
            this.j = 320;
        } else if (8000 == this.u) {
            this.i = new z(640);
            this.j = 160;
        }
        cb.a("VadUnit", "VadUnit started, vad_engine=" + this.d);
        this.a = true;
        return 0;
    }

    private void a(long j, String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(j).append(" ").append(str).append(" ").append(str2).append("\n");
        try {
            this.t.a(stringBuilder.toString().getBytes("utf-8"), true);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private boolean a(String str) {
        if (this.l != null) {
            this.l.b(str);
            if (this.l.g(AIUIConstant.KEY_AUDIO_ENCODE) && this.l.e(AIUIConstant.KEY_AUDIO_ENCODE).equals(AIUIConstant.AUDIO_ICO_ENCODE)) {
                return true;
            }
        }
        return false;
    }

    private void b(boolean z) {
        synchronized (this) {
            if (this.a) {
                this.a = false;
                a(this.n, "force_eos", "");
                if (z) {
                    this.k.d();
                    this.t.d();
                    if (this.f != null) {
                        this.f.b();
                    }
                }
                if (this.s != null) {
                    this.s.b();
                    this.s.obtainMessage(2).sendToTarget();
                }
                if (this.r != null) {
                    this.r.destroy();
                }
                if (this.i != null) {
                    this.i.a();
                }
                cb.a("VadUnit", "VadUnit stopped.");
            }
        }
    }

    private void h() {
        this.k.e();
        this.k.a(SpeechConstant.PLUS_LOCAL_ALL, ".pcm", true);
        this.t.e();
        this.t.a("vad_pos", ".txt", true);
        String a = ac.a("log", "allpcm_copy_path", "");
        if (AIUISetting.getSaveDataLog() && !TextUtils.isEmpty(a)) {
            this.f = ak.a(a);
            this.f.a("wake" + aj.b() + "-all", ".pcm", true);
        }
    }

    private void i() {
        g();
        ce a = ac.a("vad");
        if (a == null) {
            a = new ce();
        }
        this.e = ac.a(AIUIConstant.PARAM_SPEECH, AIUIConstant.KEY_INTENT_ENGINE_TYPE, "cloud");
        this.d = a.b("engine_type", this.d);
        a.d("engine_type");
        this.u = ac.a("iat", "sample_rate", 16000);
        this.w = ac.a("vad", "threshold", this.w);
        a.a("sample_rate", this.u + "", false);
        a.a(AudioDetector.VAD_ENGINE, this.d, false);
        this.x = a.b("vad_bos", this.x);
        this.z = a.b("vad_eos", this.z);
        CharSequence e = a.e(AIUIConstant.KEY_RES_TYPE);
        if (!TextUtils.isEmpty(e)) {
            String e2 = a.e(AIUIConstant.KEY_RES_PATH);
            String str = "";
            if (AIUIConstant.RES_TYPE_PATH.equals(e)) {
                str = ResourceUtil.generateResourcePath(this.c.a(), RESOURCE_TYPE.path, e2);
            } else if (AIUIConstant.RES_TYPE_ASSETS.equals(e)) {
                str = ResourceUtil.generateResourcePath(this.c.a(), RESOURCE_TYPE.assets, e2);
            }
            a.d(AIUIConstant.KEY_RES_PATH);
            a.a(AudioDetector.RES_PATH, str, true);
        }
        this.r = AudioDetector.createDetector(this.c.a(), a.toString());
        this.l = new ce();
        j();
    }

    private void j() {
        if (this.r != null) {
            this.r.setParameter("vad_bos", this.x);
            this.r.setParameter("vad_eos", this.z);
            this.r.setParameter("threshold", this.w);
            this.r.setParameter(AudioDetector.REDUCE_FLOW, "0");
        }
    }

    public void a(v vVar) {
        this.g = vVar;
    }

    public void a(x xVar) {
        this.o = xVar;
    }

    public void a(byte[] bArr, String str, int i, int i2, int i3) {
        if (bArr == null) {
            Log.e("VadUnit", "audio is null.");
        } else if (!this.a || this.s == null) {
            cb.a("VadUnit", "write before start.");
            r0 = new y(bArr, str);
            r0.d = i;
            this.h.a(r0);
        } else {
            this.p = a(str);
            Log.d("VadUnit", "mIsIcoEncode: " + this.p);
            if (this.p || bArr.length == 0 || bArr.length == 1280) {
                r0 = new y(bArr, str);
                r0.d = i;
                Message.obtain(this.s, 1, r0).sendToTarget();
                if (4 == i && this.i != null) {
                    this.i.a();
                    Log.d("VadUnit", "AudioFrameBuffer clear");
                    return;
                }
                return;
            }
            if (0 != this.h.c()) {
                cb.a("VadUnit", "audio before start, length=" + this.h.c());
                r0 = this.h.d();
                if (this.i != null) {
                    this.i.a(r0.c, r0.a());
                }
            }
            if (this.i != null) {
                this.i.a(bArr, bArr.length);
                while (true) {
                    byte[] a = this.i.a(1);
                    if (a != null) {
                        y yVar = new y(a, str);
                        yVar.d = i;
                        Message.obtain(this.s, 1, yVar).sendToTarget();
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public boolean a() {
        return this.y != ac.a("vad", "vad_enable", true);
    }

    public void b() {
        b(true);
        a(false);
        cb.a("VadUnit", "VadUnit reset.");
    }

    public int c() {
        return a(true);
    }

    public void d() {
        b(true);
    }

    public void e() {
        if (this.s != null) {
            this.s.a();
        }
    }

    public void f() {
        b(true);
        if (this.q != null) {
            this.q.quit();
        }
    }

    public void g() {
        this.y = ac.a("vad", "vad_enable", true);
        this.v = ac.a(AIUIConstant.PARAM_SPEECH, AIUIConstant.KEY_WORK_MODE, AIUIConstant.WORK_MODE_INTENT);
        this.e = ac.a(AIUIConstant.PARAM_SPEECH, AIUIConstant.KEY_INTENT_ENGINE_TYPE, "cloud");
    }
}

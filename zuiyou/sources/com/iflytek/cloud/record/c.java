package com.iflytek.cloud.record;

import android.content.Context;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.media.AudioTrack;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.thirdparty.bw;
import com.iflytek.cloud.thirdparty.cb;

public class c {
    private boolean A = false;
    private boolean B = false;
    private int C = 0;
    private Handler D = new Handler(this, Looper.getMainLooper()) {
        final /* synthetic */ c a;

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    if (this.a.f != null) {
                        this.a.f.a((SpeechError) message.obj);
                        this.a.f = null;
                        return;
                    }
                    return;
                case 1:
                    if (this.a.f != null) {
                        this.a.f.a();
                        return;
                    }
                    return;
                case 2:
                    if (this.a.f != null) {
                        this.a.f.b();
                        return;
                    }
                    return;
                case 3:
                    if (this.a.f != null) {
                        this.a.f.a(message.arg1, message.arg2, this.a.C);
                        return;
                    }
                    return;
                case 4:
                    if (this.a.f != null) {
                        this.a.f.c();
                        this.a.f = null;
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    OnAudioFocusChangeListener a = new OnAudioFocusChangeListener(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        public void onAudioFocusChange(int i) {
            if (i == -2 || i == -3 || i == -1) {
                cb.a("PcmPlayer", "pause start");
                if (this.a.c()) {
                    cb.a("PcmPlayer", "pause success");
                    this.a.l = true;
                    if (this.a.f != null) {
                        this.a.f.a();
                    }
                }
            } else if (i == 1) {
                cb.a("PcmPlayer", "resume start");
                if (this.a.l) {
                    this.a.l = false;
                    if (this.a.d()) {
                        cb.a("PcmPlayer", "resume success");
                        if (this.a.f != null) {
                            this.a.f.b();
                        }
                    }
                }
            }
        }
    };
    private AudioTrack b = null;
    private b c = null;
    private Context d = null;
    private b e = null;
    private a f = null;
    private volatile int g = 0;
    private int h = 3;
    private boolean i = true;
    private int j;
    private boolean k = false;
    private boolean l = false;
    private Object m = new Object();
    private Object n = this;
    private final int o = 2;
    private final int p = 500;
    private final int q = 50;
    private int r = 1600;
    private final float s = 1.0f;
    private final float t = 0.0f;
    private final float u = 0.1f;
    private int v = (this.r * 10);
    private float w = 0.0f;
    private float x = 1.0f;
    private float y = 0.1f;
    private boolean z = false;

    public interface a {
        void a();

        void a(int i, int i2, int i3);

        void a(SpeechError speechError);

        void b();

        void c();
    }

    private class b extends Thread {
        final /* synthetic */ c a;
        private int b;

        private b(c cVar) {
            this.a = cVar;
            this.b = this.a.h;
        }

        public int a() {
            return this.b;
        }

        public void a(int i) {
            this.b = i;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r9 = this;
            r8 = 3;
            r7 = 1;
            r6 = 4;
            r5 = 2;
            r4 = 0;
            r0 = "PcmPlayer";
            r1 = "start player";
            com.iflytek.cloud.thirdparty.cb.a(r0, r1);	 Catch:{ Exception -> 0x016f }
            r0 = "PcmPlayer";
            r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x016f }
            r1.<init>();	 Catch:{ Exception -> 0x016f }
            r2 = "mAudioFocus= ";
            r1 = r1.append(r2);	 Catch:{ Exception -> 0x016f }
            r2 = r9.a;	 Catch:{ Exception -> 0x016f }
            r2 = r2.i;	 Catch:{ Exception -> 0x016f }
            r1 = r1.append(r2);	 Catch:{ Exception -> 0x016f }
            r1 = r1.toString();	 Catch:{ Exception -> 0x016f }
            com.iflytek.cloud.thirdparty.cb.a(r0, r1);	 Catch:{ Exception -> 0x016f }
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0 = r0.i;	 Catch:{ Exception -> 0x016f }
            if (r0 == 0) goto L_0x01da;
        L_0x0036:
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0 = r0.d;	 Catch:{ Exception -> 0x016f }
            r1 = r9.a;	 Catch:{ Exception -> 0x016f }
            r1 = r1.k;	 Catch:{ Exception -> 0x016f }
            r1 = java.lang.Boolean.valueOf(r1);	 Catch:{ Exception -> 0x016f }
            r2 = r9.a;	 Catch:{ Exception -> 0x016f }
            r2 = r2.a;	 Catch:{ Exception -> 0x016f }
            com.iflytek.cloud.thirdparty.bw.a(r0, r1, r2);	 Catch:{ Exception -> 0x016f }
        L_0x004d:
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0 = r0.c;	 Catch:{ Exception -> 0x016f }
            r0.c();	 Catch:{ Exception -> 0x016f }
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r1 = r0.n;	 Catch:{ Exception -> 0x016f }
            monitor-enter(r1);	 Catch:{ Exception -> 0x016f }
            r0 = r9.a;	 Catch:{ all -> 0x0243 }
            r0 = r0.g;	 Catch:{ all -> 0x0243 }
            if (r0 == r6) goto L_0x0073;
        L_0x0065:
            r0 = r9.a;	 Catch:{ all -> 0x0243 }
            r0 = r0.g;	 Catch:{ all -> 0x0243 }
            if (r0 == r8) goto L_0x0073;
        L_0x006d:
            r0 = r9.a;	 Catch:{ all -> 0x0243 }
            r2 = 2;
            r0.g = r2;	 Catch:{ all -> 0x0243 }
        L_0x0073:
            monitor-exit(r1);	 Catch:{ all -> 0x0243 }
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0.f();	 Catch:{ Exception -> 0x016f }
        L_0x0079:
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0.k();	 Catch:{ Exception -> 0x016f }
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0 = r0.g;	 Catch:{ Exception -> 0x016f }
            if (r0 == r5) goto L_0x0096;
        L_0x0086:
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0 = r0.g;	 Catch:{ Exception -> 0x016f }
            if (r0 == r7) goto L_0x0096;
        L_0x008e:
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0 = r0.z;	 Catch:{ Exception -> 0x016f }
            if (r0 == 0) goto L_0x0352;
        L_0x0096:
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0 = r0.c;	 Catch:{ Exception -> 0x016f }
            r0 = r0.g();	 Catch:{ Exception -> 0x016f }
            if (r0 == 0) goto L_0x028d;
        L_0x00a2:
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r1 = 1;
            r2 = 2;
            r0 = r0.a(r1, r2);	 Catch:{ Exception -> 0x016f }
            if (r0 == 0) goto L_0x00c5;
        L_0x00ac:
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0 = r0.D;	 Catch:{ Exception -> 0x016f }
            r1 = 2;
            r0 = android.os.Message.obtain(r0, r1);	 Catch:{ Exception -> 0x016f }
            r0.sendToTarget();	 Catch:{ Exception -> 0x016f }
            r0 = "BUFFERING to PLAYING  fading ";
            com.iflytek.cloud.thirdparty.cb.a(r0);	 Catch:{ Exception -> 0x016f }
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0.f();	 Catch:{ Exception -> 0x016f }
        L_0x00c5:
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0 = r0.c;	 Catch:{ Exception -> 0x016f }
            r0 = r0.d();	 Catch:{ Exception -> 0x016f }
            r1 = r9.a;	 Catch:{ Exception -> 0x016f }
            r1 = r1.c;	 Catch:{ Exception -> 0x016f }
            r1 = r1.e();	 Catch:{ Exception -> 0x016f }
            if (r1 == 0) goto L_0x00f2;
        L_0x00db:
            r2 = r9.a;	 Catch:{ Exception -> 0x016f }
            r3 = r1.d;	 Catch:{ Exception -> 0x016f }
            r2.C = r3;	 Catch:{ Exception -> 0x016f }
            r2 = r9.a;	 Catch:{ Exception -> 0x016f }
            r2 = r2.D;	 Catch:{ Exception -> 0x016f }
            r3 = 3;
            r1 = r1.c;	 Catch:{ Exception -> 0x016f }
            r0 = android.os.Message.obtain(r2, r3, r0, r1);	 Catch:{ Exception -> 0x016f }
            r0.sendToTarget();	 Catch:{ Exception -> 0x016f }
        L_0x00f2:
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0 = r0.b;	 Catch:{ Exception -> 0x016f }
            r0 = r0.getPlayState();	 Catch:{ Exception -> 0x016f }
            if (r0 == r8) goto L_0x0107;
        L_0x00fe:
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0 = r0.b;	 Catch:{ Exception -> 0x016f }
            r0.play();	 Catch:{ Exception -> 0x016f }
        L_0x0107:
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0 = r0.A;	 Catch:{ Exception -> 0x016f }
            if (r0 == 0) goto L_0x014b;
        L_0x010f:
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0 = r0.c;	 Catch:{ Exception -> 0x016f }
            r0 = r0.h();	 Catch:{ Exception -> 0x016f }
            if (r0 != 0) goto L_0x0246;
        L_0x011b:
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0 = r0.c;	 Catch:{ Exception -> 0x016f }
            r1 = r9.a;	 Catch:{ Exception -> 0x016f }
            r1 = r1.v;	 Catch:{ Exception -> 0x016f }
            r0 = r0.b(r1);	 Catch:{ Exception -> 0x016f }
            if (r0 != 0) goto L_0x0246;
        L_0x012d:
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0 = r0.x;	 Catch:{ Exception -> 0x016f }
            r1 = 0;
            r0 = r0 - r1;
            r0 = java.lang.Math.abs(r0);	 Catch:{ Exception -> 0x016f }
            r1 = 1036831949; // 0x3dcccccd float:0.1 double:5.122630465E-315;
            r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1));
            if (r0 < 0) goto L_0x0246;
        L_0x0140:
            r0 = "no more size  fading ";
            com.iflytek.cloud.thirdparty.cb.a(r0);	 Catch:{ Exception -> 0x016f }
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0.g();	 Catch:{ Exception -> 0x016f }
        L_0x014b:
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0 = r0.z;	 Catch:{ Exception -> 0x016f }
            if (r0 == 0) goto L_0x0158;
        L_0x0153:
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0.h();	 Catch:{ Exception -> 0x016f }
        L_0x0158:
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0 = r0.c;	 Catch:{ Exception -> 0x016f }
            r1 = r9.a;	 Catch:{ Exception -> 0x016f }
            r1 = r1.b;	 Catch:{ Exception -> 0x016f }
            r2 = r9.a;	 Catch:{ Exception -> 0x016f }
            r2 = r2.r;	 Catch:{ Exception -> 0x016f }
            r0.a(r1, r2);	 Catch:{ Exception -> 0x016f }
            goto L_0x0079;
        L_0x016f:
            r0 = move-exception;
            com.iflytek.cloud.thirdparty.cb.a(r0);	 Catch:{ all -> 0x01f0 }
            r0 = r9.a;	 Catch:{ all -> 0x01f0 }
            r0 = r0.D;	 Catch:{ all -> 0x01f0 }
            r1 = 0;
            r2 = new com.iflytek.cloud.SpeechError;	 Catch:{ all -> 0x01f0 }
            r3 = 20011; // 0x4e2b float:2.8041E-41 double:9.8867E-320;
            r2.<init>(r3);	 Catch:{ all -> 0x01f0 }
            r0 = android.os.Message.obtain(r0, r1, r2);	 Catch:{ all -> 0x01f0 }
            r0.sendToTarget();	 Catch:{ all -> 0x01f0 }
            r0 = r9.a;
            r1 = r0.n;
            monitor-enter(r1);
            r0 = r9.a;	 Catch:{ all -> 0x03aa }
            r2 = 4;
            r0.g = r2;	 Catch:{ all -> 0x03aa }
            monitor-exit(r1);	 Catch:{ all -> 0x03aa }
            r0 = r9.a;
            r0 = r0.b;
            if (r0 == 0) goto L_0x01ac;
        L_0x019e:
            r0 = r9.a;
            r0 = r0.b;
            r0.release();
            r0 = r9.a;
            r0.b = r4;
        L_0x01ac:
            r0 = r9.a;
            r0 = r0.i;
            if (r0 == 0) goto L_0x03ad;
        L_0x01b4:
            r0 = r9.a;
            r0 = r0.d;
            r1 = r9.a;
            r1 = r1.k;
            r1 = java.lang.Boolean.valueOf(r1);
            r2 = r9.a;
            r2 = r2.a;
            com.iflytek.cloud.thirdparty.bw.b(r0, r1, r2);
        L_0x01cb:
            r0 = r9.a;
            r0.e = r4;
            r0 = "PcmPlayer";
            r1 = "player stopped";
            com.iflytek.cloud.thirdparty.cb.a(r0, r1);
        L_0x01d9:
            return;
        L_0x01da:
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0 = r0.d;	 Catch:{ Exception -> 0x016f }
            r1 = r9.a;	 Catch:{ Exception -> 0x016f }
            r1 = r1.k;	 Catch:{ Exception -> 0x016f }
            r1 = java.lang.Boolean.valueOf(r1);	 Catch:{ Exception -> 0x016f }
            r2 = 0;
            com.iflytek.cloud.thirdparty.bw.a(r0, r1, r2);	 Catch:{ Exception -> 0x016f }
            goto L_0x004d;
        L_0x01f0:
            r0 = move-exception;
            r1 = r9.a;
            r1 = r1.n;
            monitor-enter(r1);
            r2 = r9.a;	 Catch:{ all -> 0x03c2 }
            r3 = 4;
            r2.g = r3;	 Catch:{ all -> 0x03c2 }
            monitor-exit(r1);	 Catch:{ all -> 0x03c2 }
            r1 = r9.a;
            r1 = r1.b;
            if (r1 == 0) goto L_0x0215;
        L_0x0207:
            r1 = r9.a;
            r1 = r1.b;
            r1.release();
            r1 = r9.a;
            r1.b = r4;
        L_0x0215:
            r1 = r9.a;
            r1 = r1.i;
            if (r1 == 0) goto L_0x03c5;
        L_0x021d:
            r1 = r9.a;
            r1 = r1.d;
            r2 = r9.a;
            r2 = r2.k;
            r2 = java.lang.Boolean.valueOf(r2);
            r3 = r9.a;
            r3 = r3.a;
            com.iflytek.cloud.thirdparty.bw.b(r1, r2, r3);
        L_0x0234:
            r1 = r9.a;
            r1.e = r4;
            r1 = "PcmPlayer";
            r2 = "player stopped";
            com.iflytek.cloud.thirdparty.cb.a(r1, r2);
            throw r0;
        L_0x0243:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0243 }
            throw r0;	 Catch:{ Exception -> 0x016f }
        L_0x0246:
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0 = r0.g;	 Catch:{ Exception -> 0x016f }
            if (r5 != r0) goto L_0x014b;
        L_0x024e:
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0 = r0.c;	 Catch:{ Exception -> 0x016f }
            r0 = r0.h();	 Catch:{ Exception -> 0x016f }
            if (r0 != 0) goto L_0x026c;
        L_0x025a:
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0 = r0.c;	 Catch:{ Exception -> 0x016f }
            r1 = r9.a;	 Catch:{ Exception -> 0x016f }
            r1 = r1.v;	 Catch:{ Exception -> 0x016f }
            r0 = r0.b(r1);	 Catch:{ Exception -> 0x016f }
            if (r0 == 0) goto L_0x014b;
        L_0x026c:
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0 = r0.x;	 Catch:{ Exception -> 0x016f }
            r1 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
            r0 = r0 - r1;
            r0 = java.lang.Math.abs(r0);	 Catch:{ Exception -> 0x016f }
            r1 = 1036831949; // 0x3dcccccd float:0.1 double:5.122630465E-315;
            r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1));
            if (r0 < 0) goto L_0x014b;
        L_0x0280:
            r0 = "has buffer  fading ";
            com.iflytek.cloud.thirdparty.cb.a(r0);	 Catch:{ Exception -> 0x016f }
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0.f();	 Catch:{ Exception -> 0x016f }
            goto L_0x014b;
        L_0x028d:
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0 = r0.c;	 Catch:{ Exception -> 0x016f }
            r0 = r0.f();	 Catch:{ Exception -> 0x016f }
            if (r0 == 0) goto L_0x031d;
        L_0x0299:
            r0 = "play stoped";
            com.iflytek.cloud.thirdparty.cb.a(r0);	 Catch:{ Exception -> 0x016f }
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r1 = 4;
            r0.g = r1;	 Catch:{ Exception -> 0x016f }
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0 = r0.D;	 Catch:{ Exception -> 0x016f }
            r1 = 4;
            r0 = android.os.Message.obtain(r0, r1);	 Catch:{ Exception -> 0x016f }
            r0.sendToTarget();	 Catch:{ Exception -> 0x016f }
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r1 = 0;
            r0.z = r1;	 Catch:{ Exception -> 0x016f }
        L_0x02b9:
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0 = r0.b;	 Catch:{ Exception -> 0x016f }
            if (r0 == 0) goto L_0x02ca;
        L_0x02c1:
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0 = r0.b;	 Catch:{ Exception -> 0x016f }
            r0.stop();	 Catch:{ Exception -> 0x016f }
        L_0x02ca:
            r0 = r9.a;
            r1 = r0.n;
            monitor-enter(r1);
            r0 = r9.a;	 Catch:{ all -> 0x0392 }
            r2 = 4;
            r0.g = r2;	 Catch:{ all -> 0x0392 }
            monitor-exit(r1);	 Catch:{ all -> 0x0392 }
            r0 = r9.a;
            r0 = r0.b;
            if (r0 == 0) goto L_0x02ee;
        L_0x02e0:
            r0 = r9.a;
            r0 = r0.b;
            r0.release();
            r0 = r9.a;
            r0.b = r4;
        L_0x02ee:
            r0 = r9.a;
            r0 = r0.i;
            if (r0 == 0) goto L_0x0395;
        L_0x02f6:
            r0 = r9.a;
            r0 = r0.d;
            r1 = r9.a;
            r1 = r1.k;
            r1 = java.lang.Boolean.valueOf(r1);
            r2 = r9.a;
            r2 = r2.a;
            com.iflytek.cloud.thirdparty.bw.b(r0, r1, r2);
        L_0x030d:
            r0 = r9.a;
            r0.e = r4;
            r0 = "PcmPlayer";
            r1 = "player stopped";
            com.iflytek.cloud.thirdparty.cb.a(r0, r1);
            goto L_0x01d9;
        L_0x031d:
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0 = r0.z;	 Catch:{ Exception -> 0x016f }
            if (r0 == 0) goto L_0x032d;
        L_0x0325:
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r1 = 0;
            r0.z = r1;	 Catch:{ Exception -> 0x016f }
            goto L_0x0079;
        L_0x032d:
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r1 = 2;
            r2 = 1;
            r0 = r0.a(r1, r2);	 Catch:{ Exception -> 0x016f }
            if (r0 == 0) goto L_0x034b;
        L_0x0337:
            r0 = "play onpaused!";
            com.iflytek.cloud.thirdparty.cb.a(r0);	 Catch:{ Exception -> 0x016f }
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0 = r0.D;	 Catch:{ Exception -> 0x016f }
            r1 = 1;
            r0 = android.os.Message.obtain(r0, r1);	 Catch:{ Exception -> 0x016f }
            r0.sendToTarget();	 Catch:{ Exception -> 0x016f }
        L_0x034b:
            r0 = 5;
            sleep(r0);	 Catch:{ Exception -> 0x016f }
            goto L_0x0079;
        L_0x0352:
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0 = r0.g;	 Catch:{ Exception -> 0x016f }
            if (r0 != r8) goto L_0x0383;
        L_0x035a:
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0 = r0.b;	 Catch:{ Exception -> 0x016f }
            r0 = r0.getPlayState();	 Catch:{ Exception -> 0x016f }
            if (r5 == r0) goto L_0x037c;
        L_0x0366:
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0 = r0.b;	 Catch:{ Exception -> 0x016f }
            r0.pause();	 Catch:{ Exception -> 0x016f }
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0 = r0.z;	 Catch:{ Exception -> 0x016f }
            if (r0 == 0) goto L_0x037c;
        L_0x0377:
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0.i();	 Catch:{ Exception -> 0x016f }
        L_0x037c:
            r0 = 5;
            sleep(r0);	 Catch:{ Exception -> 0x016f }
            goto L_0x0079;
        L_0x0383:
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0 = r0.g;	 Catch:{ Exception -> 0x016f }
            if (r6 != r0) goto L_0x0079;
        L_0x038b:
            r0 = r9.a;	 Catch:{ Exception -> 0x016f }
            r0.i();	 Catch:{ Exception -> 0x016f }
            goto L_0x02b9;
        L_0x0392:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0392 }
            throw r0;
        L_0x0395:
            r0 = r9.a;
            r0 = r0.d;
            r1 = r9.a;
            r1 = r1.k;
            r1 = java.lang.Boolean.valueOf(r1);
            com.iflytek.cloud.thirdparty.bw.b(r0, r1, r4);
            goto L_0x030d;
        L_0x03aa:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x03aa }
            throw r0;
        L_0x03ad:
            r0 = r9.a;
            r0 = r0.d;
            r1 = r9.a;
            r1 = r1.k;
            r1 = java.lang.Boolean.valueOf(r1);
            com.iflytek.cloud.thirdparty.bw.b(r0, r1, r4);
            goto L_0x01cb;
        L_0x03c2:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x03c2 }
            throw r0;
        L_0x03c5:
            r1 = r9.a;
            r1 = r1.d;
            r2 = r9.a;
            r2 = r2.k;
            r2 = java.lang.Boolean.valueOf(r2);
            com.iflytek.cloud.thirdparty.bw.b(r1, r2, r4);
            goto L_0x0234;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.iflytek.cloud.record.c.b.run():void");
        }
    }

    public c(Context context) {
        this.d = context;
    }

    public c(Context context, int i, boolean z, boolean z2, boolean z3) {
        this.d = context;
        this.h = i;
        this.k = z;
        this.B = z2;
        this.A = z3;
    }

    private boolean a(int i, int i2) {
        boolean z = false;
        synchronized (this.n) {
            if (i == this.g) {
                this.g = i2;
                z = true;
            }
        }
        return z;
    }

    private void j() throws Exception {
        cb.a("PcmPlayer", "createAudio start");
        int a = this.c.a();
        this.j = AudioTrack.getMinBufferSize(a, 2, 2);
        this.r = ((a / 1000) * 2) * 50;
        this.v = this.r * 10;
        if (this.b != null) {
            b();
        }
        cb.a("PcmPlayer", "createAudio || mStreamType = " + this.h + ", buffer size: " + this.j);
        this.b = new AudioTrack(this.h, a, 2, 2, this.j * 2, 1);
        if (this.j == -2 || this.j == -1) {
            throw new Exception();
        }
        cb.a("PcmPlayer", "createAudio end");
    }

    private void k() throws Exception {
        b bVar = this.e;
        if (this.b == null || !(bVar == null || bVar.a() == this.h)) {
            cb.a("PcmPlayer", "prepAudioPlayer || audiotrack is null or stream type is change.");
            j();
            if (bVar != null) {
                bVar.a(this.h);
            }
        }
    }

    public int a() {
        return this.g;
    }

    public boolean a(b bVar, a aVar) {
        cb.a("PcmPlayer", "play mPlaytate= " + this.g + ",mAudioFocus= " + this.i);
        boolean z = true;
        synchronized (this.n) {
            if (this.g == 4 || this.g == 0 || this.g == 3 || this.e == null) {
                this.c = bVar;
                this.f = aVar;
                this.e = new b();
                this.e.start();
            } else {
                z = false;
            }
        }
        return z;
    }

    public void b() {
        synchronized (this.m) {
            if (this.b != null) {
                if (this.b.getPlayState() == 3) {
                    this.b.stop();
                }
                this.b.release();
                this.b = null;
            }
            cb.a("PcmPlayer", "mAudioTrack released");
        }
    }

    public boolean c() {
        if (this.g == 4 || this.g == 3) {
            return false;
        }
        cb.a("pause start fade out");
        g();
        this.g = 3;
        return true;
    }

    public boolean d() {
        boolean a = a(3, 2);
        bw.a(this.d, Boolean.valueOf(this.k), this.a);
        if (a) {
            cb.a("resume start fade in");
            f();
        }
        return a;
    }

    public void e() {
        if (4 != this.g) {
            cb.a("stop start fade out");
            g();
        }
        synchronized (this.n) {
            this.g = 4;
        }
    }

    public void f() {
        if (this.B) {
            synchronized (this.n) {
                cb.a("start fade in");
                this.z = true;
                this.x = 1.0f;
                this.y = 0.1f;
            }
        }
    }

    public void g() {
        if (this.B) {
            synchronized (this.n) {
                cb.a("start fade out");
                this.z = true;
                this.x = 0.0f;
                this.y = -0.1f;
            }
        }
    }

    public void h() {
        if (this.B) {
            synchronized (this.n) {
                if (Math.abs(this.x - this.w) < 0.1f) {
                    this.w = this.x;
                    this.z = false;
                    cb.a("fading finish");
                } else {
                    this.w += this.y;
                }
            }
            this.b.setStereoVolume(this.w, this.w);
            return;
        }
        this.z = false;
    }

    public void i() {
        cb.a("fading set silence");
        synchronized (this.n) {
            if (Math.abs(0.0f - this.x) < 0.1f) {
                this.w = 0.0f;
                this.z = false;
            }
        }
        this.b.setStereoVolume(this.w, this.w);
    }
}

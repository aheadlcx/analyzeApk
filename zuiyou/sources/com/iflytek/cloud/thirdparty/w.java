package com.iflytek.cloud.thirdparty;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.aiui.AIUIErrorCode;
import com.iflytek.aiui.AIUIEvent;
import com.iflytek.aiui.AIUISetting;
import com.iflytek.cae.jni.CAEJni;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class w extends u implements ar {
    private static final String d = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/AIUI/clearhistory/");
    private int e = 1536;
    private String f;
    private String g;
    private String h;
    private ap i;
    private boolean j = false;
    private boolean k = false;
    private com.iflytek.cloud.thirdparty.ak.a l;
    private b m;
    private ab n;

    class a extends Handler {
        final /* synthetic */ w a;
        private ConcurrentLinkedQueue<byte[]> b = new ConcurrentLinkedQueue();
        private com.iflytek.cloud.thirdparty.ak.a c = ak.a(w.d);

        public a(w wVar, Looper looper) {
            this.a = wVar;
            super(looper);
        }

        public void a() {
            synchronized (this) {
                this.b.clear();
                removeMessages(1);
            }
            if (this.c != null) {
                this.c.a();
            }
        }

        public void a(byte[] bArr) {
            synchronized (this) {
                if (bArr != null) {
                    this.b.add(bArr);
                    sendEmptyMessage(1);
                }
            }
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    byte[] bArr = (byte[]) this.b.poll();
                    if (bArr != null && bArr.length != 0) {
                        long size = (long) ((this.b.size() * bArr.length) / 1024);
                        if (size >= 10) {
                            Log.d("CaeUnit", "blocked raw audio size=" + size + "KB");
                        }
                        if (this.a.i != null) {
                            this.a.i.a(bArr, bArr.length);
                        }
                        if (5000 <= size) {
                            this.b.clear();
                            if (this.c != null) {
                                this.c.a("", ".txt");
                                this.c.a(new Date().toString() + " cleared 5000KB raw audio.\n\n");
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    class b {
        final /* synthetic */ w a;
        private a b;
        private HandlerThread c = new HandlerThread("AIUI:CAE-WriteRawAudioThread");

        public b(w wVar) {
            this.a = wVar;
        }

        public void a() {
            if (this.b != null) {
                this.c.quit();
                this.b.a();
            }
        }

        public void a(byte[] bArr) {
            if (this.b != null) {
                this.b.a(bArr);
            }
        }

        public void b() {
            this.c.start();
            this.b = new a(this.a, this.c.getLooper());
        }
    }

    public w(t tVar) {
        super("CaeUnit", tVar);
    }

    private void a(byte[] bArr) {
        byte[] bArr2 = (byte[]) bArr.clone();
        Bundle bundle = new Bundle();
        bundle.putByteArray("audio", bArr2);
        Message obtain = Message.obtain();
        obtain.what = 9;
        obtain.obj = new AIUIEvent(9, 0, 0, null, bundle);
        a(obtain);
    }

    public void a(int i) {
        if (this.i != null) {
            this.i.a(i);
        }
    }

    public void a(int i, byte[] bArr, byte[] bArr2) {
        if (this.i != null) {
            this.i.a(i, bArr, bArr2);
        }
    }

    public void a(int i, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        switch (i) {
            case 0:
                af b = this.c.b();
                if (b != null) {
                    Bundle bundle = new Bundle();
                    bundle.putByteArray("plain", bArr);
                    AIUIEvent aIUIEvent = new AIUIEvent(1000, 0, 0, "", bundle);
                    Message obtain = Message.obtain();
                    obtain.what = 10;
                    obtain.obj = aIUIEvent;
                    b.sendMessage(obtain);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void a(ab abVar) {
        this.n = abVar;
    }

    public void a(aq aqVar) {
        this.k = false;
        a(aqVar.a(), "CAE error!");
        Log.e("CaeUnit", "error=" + aqVar.a());
    }

    public void a(String str) {
        cb.a("CaeUnit", "wakeup, wakeInfo=" + str);
        if (this.k && this.n != null) {
            this.n.b();
        }
        this.k = true;
        if (this.c != null) {
            this.c.a(str);
            Message obtain = Message.obtain();
            obtain.what = 1;
            obtain.obj = str;
            a(obtain);
        }
    }

    public void a(byte[] bArr, int i, int i2, int i3) {
        if (this.n != null) {
            if (this.j) {
                a(bArr);
            }
            this.n.a(bArr, "", 0, 0, 0);
        }
    }

    public void a(byte[] bArr, String str, int i, int i2, int i3) {
        if (bArr == null) {
            Log.e("CaeUnit", "audio is null.");
            return;
        }
        if (this.l != null) {
            if (IjkMediaMeta.AV_CH_WIDE_LEFT <= this.l.f()) {
                j();
                this.l.b("");
            }
            try {
                this.l.a(bArr, false);
            } catch (IOException e) {
                e.printStackTrace();
                a(AIUIErrorCode.ERROR_IO_EXCEPTION, e.getMessage());
            }
        }
        if (this.m != null) {
            this.m.a(bArr);
        }
    }

    public boolean a() {
        String a = ac.a("ivw", AIUIConstant.KEY_RES_TYPE, "");
        String a2 = ac.a("ivw", AIUIConstant.KEY_RES_PATH, "");
        if (this.g.equals(a) && this.f.equals(a2)) {
            return false;
        }
        cb.a("CaeUnit", "critical params changed.");
        return true;
    }

    public void b() {
        d();
        c();
        cb.a("CaeUnit", "CaeUnit reset.");
    }

    public void b(int i) {
        this.j = true;
        if (!this.k && this.i != null) {
            this.i.a(i);
        }
    }

    public int c() {
        if (this.a) {
            cb.a("CaeUnit", "CaeUnit was already started.");
            return 0;
        }
        g();
        this.i = ap.a(this.h, at.a(this.c.a(), com.iflytek.cloud.thirdparty.at.a.valueOf(this.g), this.f), this.e);
        if (this.i == null) {
            return -1;
        }
        this.i.a((ar) this);
        JSONObject b = ac.b("ivw");
        if (b != null) {
            JSONObject optJSONObject = b.optJSONObject(AIUIConstant.KEY_CAE_PARAMS);
            if (optJSONObject != null) {
                Iterator keys = optJSONObject.keys();
                while (keys.hasNext()) {
                    String str = (String) keys.next();
                    this.i.a(str, optJSONObject.optString(str));
                }
            }
        }
        this.k = false;
        this.l = ak.a(AIUISetting.getRawAudioPath());
        this.m = new b(this);
        this.m.b();
        this.a = true;
        cb.a("CaeUnit", "CaeUnit started.");
        return 0;
    }

    public void d() {
        cb.a("CaeUnit", "destroy cae engine.");
        if (this.i != null) {
            this.i.b();
        }
        cb.a("CaeUnit", "cae engine has been destroyed.");
        if (this.m != null) {
            this.m.a();
        }
        this.k = false;
        this.a = false;
        this.j = false;
    }

    public int f() {
        return CAEJni.a() ? ap.c() : -1;
    }

    public void g() {
        this.g = ac.a("ivw", AIUIConstant.KEY_RES_TYPE, AIUIConstant.RES_TYPE_ASSETS);
        this.f = ac.a("ivw", AIUIConstant.KEY_RES_PATH, "");
        this.h = ac.a("ivw", AIUIConstant.KEY_LIB_CAE, "");
        this.e = ac.a("ivw", "audio_throw_size", this.e);
    }

    public void h() {
        if (!(this.i == null || this.j)) {
            this.i.a();
        }
        this.k = false;
    }

    public void i() {
        if (this.l != null) {
            this.l.b("");
        }
    }

    public void j() {
        if (this.l != null) {
            this.l.d();
        }
    }

    public void k() {
        this.j = false;
        if (!this.k && this.i != null) {
            this.i.a();
        }
    }
}

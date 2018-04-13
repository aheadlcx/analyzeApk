package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.aiui.AIUIEvent;
import com.iflytek.aiui.AIUIMessage;
import com.iflytek.aiui.Version;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

public class t extends r {
    private String d = AIUIConstant.AUDIO_CAPTOR_ALSA;
    private String e = "cloud";
    private v f;
    private j g;
    private k h = new k(this) {
        final /* synthetic */ t a;

        {
            this.a = r1;
        }

        public void a() {
            Handler b = this.a.b();
            if (b != null) {
                Message.obtain(b, 10, new AIUIEvent(11, 0, 0, "", null)).sendToTarget();
            }
            if ("system".equals(this.a.d) && this.a.n != null) {
                this.a.n.e();
            }
        }

        public void a(int i, String str) {
            this.a.a(i, str);
        }

        public void a(byte[] bArr, int i, Bundle bundle) {
            byte[] bArr2 = bArr;
            this.a.a("sdk", bArr2, "data_type=audio", 0, 0, 0, this.a.g.a());
        }

        public void b() {
            Handler b = this.a.b();
            if (b != null) {
                Message.obtain(b, 10, new AIUIEvent(12, 0, 0, "", null)).sendToTarget();
            }
            this.a.a(new byte[0], "data_type=audio", 4, 0, 0, this.a.g.a());
        }
    };
    private w i;
    private HandlerThread j = new HandlerThread("AIUI:SpeechModuleThread");
    private x k;
    private boolean l = false;
    private a m;
    private ab n;
    private String o;
    private String p = "sdk";
    private String q = "cae";

    class a extends Handler {
        final /* synthetic */ t a;

        public a(t tVar, Looper looper) {
            this.a = tVar;
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.a.p();
                    this.a.o();
                    this.a.n();
                    return;
                case 2:
                    this.a.r();
                    this.a.q();
                    return;
                case 3:
                    if (this.a.i != null) {
                        this.a.i.a(message.arg1);
                        return;
                    }
                    return;
                case 4:
                    if (this.a.i != null) {
                        this.a.i.i();
                        return;
                    }
                    return;
                case 5:
                    if (this.a.i != null) {
                        this.a.i.j();
                        return;
                    }
                    return;
                case 6:
                    if (this.a.f != null) {
                        this.a.f.a((String) message.obj);
                        return;
                    }
                    return;
                case 7:
                    if (this.a.k != null) {
                        this.a.k.a((String) message.obj);
                        return;
                    }
                    return;
                case 8:
                    if (this.a.f != null) {
                        this.a.f.b((String) message.obj);
                        return;
                    }
                    return;
                case 9:
                    if (this.a.i != null) {
                        this.a.i.b(message.arg1);
                        return;
                    }
                    return;
                case 10:
                    if (this.a.i != null) {
                        this.a.i.k();
                        return;
                    }
                    return;
                case 11:
                    try {
                        JSONObject jSONObject = new JSONObject(new String((byte[]) message.obj, "utf-8"));
                        this.a.a(1, jSONObject.getString("dev_id").getBytes("utf-8"), jSONObject.getString("ver").getBytes("utf-8"));
                        return;
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        return;
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                        return;
                    }
                case 12:
                    this.a.a(0, (byte[]) message.obj, null);
                    return;
                case 13:
                    if (this.a.l) {
                        this.a.f();
                        this.a.m();
                        return;
                    }
                    return;
                case 14:
                    if (this.a.n != null) {
                        this.a.n.b();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public t(Context context, af afVar) {
        super(context, afVar);
        this.j.start();
        this.m = new a(this, this.j.getLooper());
    }

    private void a(int i, String str) {
        if (this.a != null) {
            this.a.a(i, str);
        }
    }

    private void a(int i, byte[] bArr, byte[] bArr2) {
        if (this.i != null) {
            this.i.a(i, bArr, bArr2);
        }
    }

    private void m() {
        if (AIUIConstant.USER.equals(this.p)) {
            this.g.d();
        }
        if (this.n != null && this.n.a()) {
            this.n.b();
        }
        if ("cae".equals(this.q)) {
            if (this.i == null) {
                if (this.n != null) {
                    this.n.b();
                }
                this.i = new w(this);
                this.i.a(this.n);
                int c = this.i.c();
                if (c != 0) {
                    a(c, "start CaeUnit error.");
                }
            } else if (this.i.a()) {
                this.i.b();
            }
        } else if (!("ivw".equals(this.q) || !"off".equals(this.q) || this.i == null)) {
            this.i.h();
            if (this.n != null) {
                this.n.b();
            }
        }
        if ("cloud".equals(this.e)) {
            q();
        } else if (this.f == null) {
            this.f = new v(this);
            if (this.n != null) {
                this.n.a(this.f);
            }
        }
    }

    private void n() {
        if (this.f != null) {
            this.f.c();
        }
    }

    private void o() {
        if (this.k != null) {
            this.k.c();
        }
    }

    private void p() {
        if (this.n != null) {
            this.n.c();
        }
    }

    private void q() {
        if (this.f != null) {
            this.f.d();
        }
    }

    private void r() {
        if (this.k != null) {
            this.k.d();
        }
    }

    private void s() {
        if (this.n != null) {
            this.n.d();
        }
    }

    public void a(int i) {
        Message.obtain(this.m, 3, i, 0).sendToTarget();
    }

    public void a(Message message) {
        synchronized (this) {
            this.m.sendMessage(message);
        }
    }

    public void a(AIUIMessage aIUIMessage) {
        Message.obtain(this.m, 6, aIUIMessage.params).sendToTarget();
    }

    public void a(String str) {
        this.o = str;
    }

    public void a(String str, byte[] bArr, String str2, int i, int i2, int i3, int i4) {
        if (this.p.equals(str)) {
            a(bArr, str2, i, i2, i3, i4);
        }
    }

    public void a(byte[] bArr, String str, int i, int i2, int i3, int i4) {
        if ("cae".equals(this.q)) {
            if (this.i != null) {
                this.i.a(bArr, str, i, i2, i3);
            }
        } else if (this.n != null) {
            this.n.a(bArr, str, i, i2, i3);
        }
    }

    public void b(AIUIMessage aIUIMessage) {
        Message.obtain(this.m, 9, aIUIMessage.arg1, 0).sendToTarget();
    }

    public void c() {
        if (this.l) {
            j();
        }
        if (this.n != null) {
            this.n.f();
        }
        if (this.k != null) {
            this.k.e();
        }
        if (this.f != null) {
            this.f.f();
        }
        if (this.j != null) {
            this.j.quit();
        }
    }

    public void c(AIUIMessage aIUIMessage) {
        Message.obtain(this.m, 8, aIUIMessage.params).sendToTarget();
    }

    public int d() {
        int i = -1;
        if (this.i != null) {
            i = this.i.f();
        }
        cb.a("SpeechModule", "mic_type=" + i);
        return i;
    }

    public void d(AIUIMessage aIUIMessage) {
        Message.obtain(this.m, 7, aIUIMessage.params).sendToTarget();
    }

    public String e() {
        return this.o;
    }

    public void e(AIUIMessage aIUIMessage) {
        Message.obtain(this.m, 11, aIUIMessage.data).sendToTarget();
    }

    public void f() {
        this.c = ac.a("iat");
        this.d = ac.a(AIUIConstant.PARAM_SPEECH, AIUIConstant.KEY_AUDIO_CAPTOR, AIUIConstant.AUDIO_CAPTOR_ALSA);
        this.p = ac.a(AIUIConstant.PARAM_SPEECH, AIUIConstant.KEY_DATA_SOURCE, "sdk");
        this.q = ac.a(AIUIConstant.PARAM_SPEECH, AIUIConstant.KEY_WAKEUP_MODE, "cae");
        this.e = ac.a(AIUIConstant.PARAM_SPEECH, AIUIConstant.KEY_INTENT_ENGINE_TYPE, "cloud");
        if (Version.isMobileVersion()) {
            this.d = "system";
            this.q = "off";
        }
    }

    public void f(AIUIMessage aIUIMessage) {
        Message.obtain(this.m, 12, aIUIMessage.data).sendToTarget();
    }

    public void g() {
        al.a();
        this.o = "";
        if ("system".equals(this.d) && this.g != null) {
            this.g.d();
        }
        if (this.i != null) {
            this.i.h();
        }
        s();
        r();
        q();
    }

    public int h() throws n {
        int c;
        f();
        if (AIUIConstant.AUDIO_CAPTOR_ALSA.equals(this.d)) {
            this.g = new l(this.h);
        } else if ("system".equals(this.d)) {
            this.g = new m(this.h);
        }
        if ("sdk".equals(this.p)) {
            c = AIUIConstant.AUDIO_CAPTOR_ALSA.equals(this.d) ? this.g.c() : 0;
            if (c != 0) {
                throw new n(c, "AlsaCaptor start error.");
            }
        }
        if (this.n == null) {
            this.n = new ab(this);
            this.k = new x(this);
            if (!"cloud".equals(this.e)) {
                this.f = new v(this);
            }
            this.n.a(this.k);
            this.n.a(this.f);
        }
        if ("cae".equals(this.q)) {
            this.i = new w(this);
            this.i.a(this.n);
            c = this.i.c();
            if (c != 0) {
                throw new n(c, "CaeUnit start error.");
            }
        } else if (!"ivw".equals(this.q) && "off".equals(this.q)) {
        }
        this.l = true;
        cb.a("SpeechModule", "SpeechModule started.");
        return 0;
    }

    public void i() {
        if (this.g != null) {
            int c = this.g.c();
            if (c != 0) {
                a(c, "start audio captor error.");
            }
        }
    }

    public void j() {
        if (this.g != null) {
            this.g.d();
        }
        if (this.i != null) {
            this.i.d();
        }
        if (this.n != null) {
            this.n.d();
        }
        if (this.k != null) {
            this.k.d();
        }
        this.l = false;
        cb.a("SpeechModule", "SpeechModule stopped.");
    }

    public void k() {
        if (this.g != null) {
            this.g.d();
        }
    }

    public void l() {
        Message.obtain(this.m, 10).sendToTarget();
    }
}

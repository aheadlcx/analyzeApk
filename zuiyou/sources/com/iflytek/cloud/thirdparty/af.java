package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.aiui.AIUIErrorCode;
import com.iflytek.aiui.AIUIEvent;
import com.iflytek.aiui.AIUIListener;
import com.iflytek.aiui.AIUIMessage;
import com.iflytek.aiui.Version;
import com.iflytek.cloud.SpeechConstant;
import org.json.JSONException;
import org.json.JSONObject;

public class af extends Handler {
    private String a = "";
    private String b = "auto";
    private String c = "cloud";
    private int d = -1;
    private AIUIListener e;
    private ah f;
    private Context g;
    private int h = 1;
    private o i;
    private String j = "";
    private q k;
    private ag l;
    private t m;
    private s n;
    private ai o;
    private int p = 5000;
    private String q = AIUIConstant.INTERACT_MODE_CONTINUOUS;

    public af(Context context, Looper looper, String str, AIUIListener aIUIListener) {
        super(looper);
        this.g = context.getApplicationContext();
        this.j = str;
        this.e = aIUIListener;
        this.f = new ah(this);
        this.i = new o(this.f);
        this.o = new ai(this.f);
        this.k = new q(this);
        this.l = new ag(this);
        this.m = new t(this.g, this);
        this.n = new s(this.g, this);
        ao.a(this.f);
    }

    private void a(Message message) {
        if (this.m != null) {
            this.m.a(message);
        }
    }

    private void a(Message message, boolean z) {
        cb.a("AIUIScheduler", "wakeup, isoutter=" + z);
        if (z) {
            int i = message.arg1;
            if (this.m != null) {
                this.m.a(i);
            }
            JSONObject a = am.a(i);
            if (!Version.isMobileVersion()) {
                a(a.toString());
            }
        } else {
            a((String) message.obj);
        }
        if (AIUIConstant.WORK_MODE_INTENT.equals(ac.a(AIUIConstant.PARAM_SPEECH, AIUIConstant.KEY_WORK_MODE, AIUIConstant.WORK_MODE_INTENT)) && "cloud".equals(this.c) && !bz.b(e())) {
            a(20001, "no network.");
            return;
        }
        byte[] bArr = o.a;
        String str = "sdk";
        if (z) {
            str = AIUIConstant.USER;
        }
        if (b(3)) {
            this.f.a();
            a("re_wakeup", "working", "working", "", bArr, "", str);
            ao.a("wakeup", "working", al.b());
        } else {
            al.a(this.g, System.currentTimeMillis(), this.a);
            aj.a(this.m.a(), "", "wake");
            a("wakeup", "working", "ready", "", bArr, "audio", str);
            if (AIUIConstant.INTERACT_MODE_CONTINUOUS.equals(this.q) && "auto".equals(this.b)) {
                a(SpeechConstant.ISV_CMD, "working", "ready", "clean_history", null, "", str);
            }
            ao.a("wakeup", "ready", al.b());
        }
        c(3);
        h();
        if (z) {
            a(obtainMessage(14));
        }
        a(obtainMessage(1));
    }

    private void a(AIUIMessage aIUIMessage) {
        switch (aIUIMessage.msgType) {
            case 1:
                cb.a("AIUIScheduler", "CMD_GET_STATE");
                i();
                return;
            case 2:
                h(aIUIMessage);
                return;
            case 3:
                g(aIUIMessage);
                return;
            case 4:
                cb.a("AIUIScheduler", "CMD_RESET");
                k();
                return;
            case 5:
                cb.a("AIUIScheduler", "CMD_START");
                a(false);
                return;
            case 6:
                cb.a("AIUIScheduler", "CMD_STOP");
                a();
                n();
                return;
            case 7:
                cb.a("AIUIScheduler", "CMD_WAKEUP, beam=" + aIUIMessage.arg1);
                a(Message.obtain(this, 1, aIUIMessage.arg1, aIUIMessage.arg2, aIUIMessage.params), true);
                return;
            case 8:
                cb.a("AIUIScheduler", "CMD_RESET_WAKEUP, restType=" + aIUIMessage.arg1 + ", delayTime=" + aIUIMessage.arg2);
                a(3);
                if (1 == aIUIMessage.arg1) {
                    int i = aIUIMessage.arg2;
                    if (i < 0) {
                        i = 0;
                    } else if (i > 180000) {
                        i = 180000;
                    }
                    sendMessageDelayed(Message.obtain(this, 3, 0, 0, Boolean.valueOf(false)), (long) i);
                    return;
                }
                if (b(3)) {
                    a(new AIUIEvent(5, 1, 0, null, null));
                    a("reset_wakeup", "ready", "working", "", null, "", AIUIConstant.USER);
                }
                b(true);
                c(2);
                return;
            case 9:
                cb.a("AIUIScheduler", "CMD_SET_BEAM, beam=" + aIUIMessage.arg1);
                if (this.m != null) {
                    this.m.a(aIUIMessage.arg1);
                    return;
                }
                return;
            case 10:
                cb.a("AIUIScheduler", "CMD_SET_PARAMS, params=" + aIUIMessage.params);
                ac.e(aIUIMessage.params);
                j();
                if (this.l != null) {
                    this.l.a();
                }
                Message obtain = Message.obtain();
                obtain.what = 13;
                a(obtain);
                return;
            case 11:
                cb.a("AIUIScheduler", "CMD_UPLOAD_LEXICON");
                if (this.m != null) {
                    this.m.d(aIUIMessage);
                    return;
                }
                return;
            case 12:
                this.k.a(aIUIMessage.params, aIUIMessage.arg1);
                return;
            case 13:
                cb.a("AIUIScheduler", "CMD_SYNC");
                if (this.n != null) {
                    this.n.b(aIUIMessage);
                    return;
                }
                return;
            case 14:
                cb.a("AIUIScheduler", "CMD_START_SAVE");
                d(aIUIMessage);
                return;
            case 15:
                cb.a("AIUIScheduler", "CMD_STOP_SAVE");
                f(aIUIMessage);
                return;
            case 16:
                cb.a("AIUIScheduler", "CMD_BUILD_GRAMMAR");
                if (this.m != null) {
                    this.m.a(aIUIMessage);
                    return;
                }
                return;
            case 17:
                cb.a("AIUIScheduler", "CMD_UPDATE_LOCAL_LEXICON");
                if (this.m != null) {
                    this.m.c(aIUIMessage);
                    return;
                }
                return;
            case 18:
                cb.a("AIUIScheduler", "CMD_START_THROW_AUDIO");
                if (this.m != null) {
                    this.m.b(aIUIMessage);
                    return;
                }
                return;
            case 19:
                cb.a("AIUIScheduler", "CMD_STOP_THROW_AUDIO");
                if (this.m != null) {
                    this.m.l();
                    return;
                }
                return;
            case 20:
                cb.a("AIUIScheduler", "CMD_RESULT_VALIDATION_ACK");
                if (hasMessages(11)) {
                    removeMessages(11);
                    h();
                    cb.a("AIUIScheduler", "interact timeout has been reset.");
                    return;
                }
                cb.a("AIUIScheduler", "result ack timeout.");
                return;
            case 21:
                cb.a("AIUIScheduler", "CMD_CLEAN_DIALOG_HISTORY");
                String a = o.a(this.h);
                a(SpeechConstant.ISV_CMD, a, a, "clean_history", null, "", AIUIConstant.USER);
                return;
            case 22:
                cb.a("AIUIScheduler", "CMD_START_RECORD");
                c(aIUIMessage);
                return;
            case 23:
                cb.a("AIUIScheduler", "CMD_STOP_RECORD");
                e(aIUIMessage);
                return;
            case 24:
                cb.a("AIUIScheduler", "CMD_QUERY_SYNC_STATUS");
                if (this.n != null) {
                    this.n.a(aIUIMessage);
                    return;
                }
                return;
            case 25:
                cb.a("AIUIScheduler", "CMD_QUERY_PARAMS");
                b(aIUIMessage);
                return;
            case 1000:
                if (this.m != null) {
                    this.m.e(aIUIMessage);
                    return;
                }
                return;
            case 1001:
                if (this.m != null) {
                    this.m.f(aIUIMessage);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void a(String str) {
        a(new AIUIEvent(4, 0, 0, str, null));
    }

    private void a(String str, String str2, String str3, String str4, byte[] bArr, String str5, String str6) {
        if (this.i != null) {
            try {
                this.i.a(str, str2, str3, str4, bArr, str5, str6);
            } catch (n e) {
                e.printStackTrace();
                a(e.b(), e.a());
            }
        }
    }

    private void b(AIUIMessage aIUIMessage) {
        try {
            a(new AIUIEvent(8, 25, 0, ac.b(new JSONObject(aIUIMessage.params).optString(AIUIConstant.PARAMS_TYPE)).toString(), null));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void b(boolean z) {
        a(3);
        if (AIUIConstant.INTERACT_MODE_CONTINUOUS.equals(this.q)) {
            this.f.a();
        }
        this.m.g();
        if (z) {
            ao.a("reset", "outer", al.b());
        } else {
            ao.a("reset", "inner", al.b());
        }
        cb.a("AIUIScheduler", "wakeup satatus reset, forced=" + z);
    }

    private boolean b(int i) {
        return this.h == i;
    }

    private void c(int i) {
        this.h = i;
        i();
    }

    private void c(AIUIMessage aIUIMessage) {
        if ("audio".equals(ac.d(aIUIMessage.params).e("data_type")) && this.m != null) {
            this.m.i();
        }
    }

    private void d(AIUIMessage aIUIMessage) {
        ce ceVar = new ce();
        ceVar.a(aIUIMessage.params);
        if ("raw_audio".endsWith(ceVar.e("data_type"))) {
            Message obtain = Message.obtain();
            obtain.what = 4;
            a(obtain);
        }
    }

    private void e(AIUIMessage aIUIMessage) {
        if ("audio".equals(ac.d(aIUIMessage.params).e("data_type")) && this.m != null) {
            this.m.k();
        }
    }

    private void f(AIUIMessage aIUIMessage) {
        ce ceVar = new ce();
        ceVar.a(aIUIMessage.params);
        if ("raw_audio".endsWith(ceVar.e("data_type"))) {
            Message obtain = Message.obtain();
            obtain.what = 5;
            a(obtain);
        }
    }

    private void g(AIUIMessage aIUIMessage) {
        ce d = ac.d(aIUIMessage.params);
        if ("audio".equals(d.e("data_type")) && this.m != null) {
            byte[] bArr = new byte[0];
            this.m.a(bArr, aIUIMessage.params, 4, aIUIMessage.arg1, aIUIMessage.arg2, d.a("sample_rate", 96000));
        }
    }

    private void h(AIUIMessage aIUIMessage) {
        if (TextUtils.isEmpty(aIUIMessage.params)) {
            a(10106, "invalid write data params, empty or null.");
            return;
        }
        ce d = ac.d(aIUIMessage.params);
        String e = d.e("data_type");
        if ("audio".equals(e)) {
            if (this.m != null) {
                this.m.a(AIUIConstant.USER, aIUIMessage.data, aIUIMessage.params, 0, aIUIMessage.arg1, aIUIMessage.arg2, d.a("sample_rate", 96000));
            }
        } else if (!"text".equals(e)) {
        } else {
            if (!b(3)) {
                a((int) AIUIErrorCode.ERROR_NOT_WORKING, "AIUI not working, please wakeup first.");
            } else if (this.o != null) {
                try {
                    this.o.a(aIUIMessage.params, aIUIMessage.data);
                } catch (n e2) {
                    e2.printStackTrace();
                    a(e2.b(), e2.a());
                }
            }
        }
    }

    private void i() {
        AIUIEvent aIUIEvent = new AIUIEvent(3, this.h, 0, null, null);
        if (3 == this.h && this.m != null) {
            aIUIEvent.info = this.m.e();
        }
        a(aIUIEvent);
    }

    private void j() {
        this.a = ac.a();
        this.d = ac.a("interact", AIUIConstant.KEY_INTERACT_TIMEOUT, 10000);
        this.c = ac.a(AIUIConstant.PARAM_SPEECH, AIUIConstant.KEY_INTENT_ENGINE_TYPE, "cloud");
        this.p = ac.a("interact", AIUIConstant.KEY_RESULT_TIMEOUT, this.p);
        this.q = ac.a(AIUIConstant.PARAM_SPEECH, AIUIConstant.KEY_INTERACT_MODE, AIUIConstant.INTERACT_MODE_CONTINUOUS);
        this.b = ac.a("global", AIUIConstant.KEY_CLEAN_DIALOG_HISTORY, "auto");
        if (-1 != this.d) {
            if (this.d < 10000) {
                this.d = 10000;
            } else if (this.d > 180000) {
                this.d = 180000;
            }
        }
        if (Version.isMobileVersion()) {
            this.q = ac.a(AIUIConstant.PARAM_SPEECH, AIUIConstant.KEY_INTERACT_MODE, AIUIConstant.INTERACT_MODE_ONESHOT);
        }
    }

    private void k() {
        a(3);
        n();
        a(false);
        cb.a("AIUIScheduler", "AIUIScheduler reset.");
    }

    private void l() {
        if (-1 != this.d) {
            a(3, (long) this.d);
        }
    }

    private int m() {
        ce a = ac.a("aiui_ssb");
        if (a == null) {
            a = new ce();
        }
        a.a("delay_init", "0");
        a.a("rslt_timeout", this.p + "");
        try {
            if (this.f.c(a) == 0) {
                this.f.a("resultCb", "statusCb", "errorCb", "syncDataCb", "pushCb", this.l);
            }
            return 0;
        } catch (n e) {
            a(e.b(), e.a());
            return -1;
        }
    }

    private void n() {
        synchronized (this) {
            String str = "";
            if (b(2)) {
                str = "ready";
            } else if (b(3)) {
                str = "working";
            }
            a("stop", "idle", str, "", null, "", "sdk");
            ao.a("stop", null);
            this.m.j();
            o();
            c(1);
            cb.a("AIUIScheduler", "AIUIScheduler stopped.");
        }
    }

    private void o() {
        try {
            this.f.d();
        } catch (n e) {
            a(e.b(), e.a());
        }
    }

    public int a(boolean z) {
        int i = -1;
        synchronized (this) {
            if (b(1)) {
                if (z) {
                    if (!ac.e(this.j)) {
                        a(10106, "invalid params json format.");
                    }
                }
                this.l.a();
                this.l.b();
                j();
                if (m() == 0) {
                    try {
                        if (this.m.h() == 0) {
                            c(2);
                        }
                        this.k.a(this.m.d());
                        a("start", "ready", "idle", "", null, "", "sdk");
                        ao.a("start", null);
                        cb.a("AIUIScheduler", "AIUIScheduler started.");
                        i = 0;
                    } catch (n e) {
                        a(e.b(), e.a());
                    }
                }
            } else {
                cb.a("AIUIScheduler", "AIUIScheduler was already started.");
                i = 0;
            }
        }
        return i;
    }

    public void a() {
        cb.a("AIUIScheduler", "clear all messages.");
        for (int i = 1; i < 13; i++) {
            removeMessages(i);
        }
    }

    public void a(int i) {
        removeMessages(i);
    }

    public void a(int i, int i2, int i3, Object obj, long j) {
        Message obtain = Message.obtain(this, i, i2, i3);
        obtain.obj = obj;
        sendMessageDelayed(obtain, j);
    }

    public void a(int i, long j) {
        sendMessageDelayed(Message.obtain(this, i), j);
    }

    public void a(int i, String str) {
        sendMessage(obtainMessage(7, new AIUIEvent(2, i, 0, str, null)));
    }

    public void a(AIUIEvent aIUIEvent) {
        if (this.e != null) {
            this.e.onEvent(aIUIEvent);
        }
    }

    public void b() {
        a();
        n();
        if (this.m != null) {
            this.m.c();
        }
    }

    public ah c() {
        return this.f;
    }

    public String d() {
        return this.a;
    }

    public Context e() {
        return this.g;
    }

    public ag f() {
        return this.l;
    }

    public String g() {
        return this.q;
    }

    public void h() {
        cb.a("AIUIScheduler", "reset interact timeout.");
        a(3);
        l();
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                a(message, false);
                return;
            case 2:
                AIUIMessage aIUIMessage = (AIUIMessage) message.obj;
                if (5 == aIUIMessage.msgType || 1 == aIUIMessage.msgType || this.f.b()) {
                    a(aIUIMessage);
                    return;
                } else {
                    Log.e("AIUIScheduler", "AIUISession has not began yet. ignore msgType=" + aIUIMessage.msgType);
                    return;
                }
            case 3:
                boolean z = true;
                if (message.obj != null) {
                    z = ((Boolean) message.obj).booleanValue();
                }
                if (z && (this.f.c() || ae.a().c())) {
                    a(3, 0, 0, Boolean.valueOf(false), (long) this.p);
                    return;
                } else if (!b(2)) {
                    if (10 != message.arg1) {
                        a(new AIUIEvent(10, 0, 0, null, null));
                        a(3, 10, 0, Boolean.valueOf(false), 10000);
                        cb.a("AIUIScheduler", "prepare to sleep, sleep after 10000ms");
                        return;
                    }
                    cb.a("AIUIScheduler", "interact timeout, reset wakeup.");
                    this.f.a();
                    a("sleep", "ready", "working", "", null, "", "sdk");
                    a(new AIUIEvent(5, 0, 0, null, null));
                    b(false);
                    c(2);
                    return;
                } else {
                    return;
                }
            case 4:
                if (b(3)) {
                    h();
                    return;
                }
                return;
            case 5:
            case 8:
            case 10:
                if (5 != message.what || !AIUIConstant.INTERACT_MODE_CONTINUOUS.equals(this.q) || b(3)) {
                    a((AIUIEvent) message.obj);
                    return;
                }
                return;
            case 6:
                a(new AIUIEvent(6, message.arg1, message.arg2, null, null));
                if ((2 == message.arg1 || 3 == message.arg1) && AIUIConstant.INTERACT_MODE_ONESHOT.equals(this.q)) {
                    a(new AIUIEvent(5, 0, 0, null, null));
                    b(false);
                    c(2);
                    return;
                }
                return;
            case 7:
                AIUIEvent aIUIEvent = (AIUIEvent) message.obj;
                a(aIUIEvent);
                if (AIUIErrorCode.MSP_ERROR_AIUI_NO_ENOUGH_LICENSE == aIUIEvent.arg1) {
                    sendMessage(obtainMessage(2, new AIUIMessage(6, 0, 0, "", null)));
                    return;
                }
                return;
            case 9:
                a((AIUIEvent) message.obj);
                return;
            case 11:
                switch (message.arg1) {
                    case 0:
                        removeMessages(11);
                        Message obtain = Message.obtain();
                        obtain.what = 11;
                        obtain.arg1 = 1;
                        sendMessageDelayed(obtain, 5000);
                        return;
                    case 1:
                        removeMessages(11);
                        return;
                    default:
                        return;
                }
            case 12:
                cb.a("AIUIScheduler", "lost connection to server for long time, reset AIUI session.");
                if (this.f != null) {
                    o();
                    m();
                    if (this.l != null) {
                        this.l.b();
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

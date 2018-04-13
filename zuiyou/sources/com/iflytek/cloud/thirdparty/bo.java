package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.msc.MSC;
import com.iflytek.msc.MSCSessionInfo;
import java.io.UnsupportedEncodingException;

public class bo extends bf {
    private MSCSessionInfo c = new MSCSessionInfo();

    public int a(Context context, String str, be beVar) throws SpeechError, UnsupportedEncodingException {
        this.a = null;
        String d = cg.d(context, beVar);
        cb.a("QTTSSessionBegin enter");
        long currentTimeMillis = System.currentTimeMillis();
        byte[] bytes = d.getBytes(beVar.q());
        cc.a("MSCSessionBegin", null);
        synchronized (bo.class) {
            this.a = MSC.QTTSSessionBegin(bytes, this.c);
        }
        cc.a("SessionBeginEnd", null);
        cb.a("QTTSSessionBegin leave:" + (System.currentTimeMillis() - currentTimeMillis) + " ErrorCode:" + this.c.errorcode);
        int i = this.c.errorcode;
        if (i == 0 || i == 10129 || i == 10113 || i == 10132) {
            return i;
        }
        throw new SpeechError(i);
    }

    public void a(String str) {
        if (this.a != null) {
            if (TextUtils.isEmpty(str)) {
                str = "unknown";
            }
            cb.a("QTTSSessionEnd enter");
            cb.a("QTTSSessionEnd leavel:" + MSC.QTTSSessionEnd(this.a, str.getBytes()));
            this.a = null;
            this.b = null;
        }
    }

    public synchronized void a(byte[] bArr) throws SpeechError {
        cb.a("QTTSTextPut enter");
        cc.a("LastDataFlag", null);
        int QTTSTextPut = MSC.QTTSTextPut(this.a, bArr);
        cb.a("QTTSTextPut leavel:" + QTTSTextPut);
        if (QTTSTextPut != 0) {
            throw new SpeechError(QTTSTextPut);
        }
    }

    public synchronized byte[] a() throws SpeechError {
        byte[] QTTSAudioGet;
        if (this.a == null) {
            throw new SpeechError(20003);
        }
        cb.b("QTTSAudioGet enter");
        QTTSAudioGet = MSC.QTTSAudioGet(this.a, this.c);
        cb.b("QTTSAudioGet leave:" + this.c.errorcode + "value len = " + (QTTSAudioGet == null ? 0 : QTTSAudioGet.length));
        int i = this.c.errorcode;
        if (i != 0) {
            throw new SpeechError(i);
        }
        return QTTSAudioGet;
    }

    public int b() {
        int i = 0;
        try {
            i = Integer.parseInt(c("ced"));
        } catch (Exception e) {
        }
        return i;
    }

    public synchronized int b(String str) {
        int i = 0;
        synchronized (this) {
            if (this.a != null) {
                try {
                    Object c = c(str);
                    if (!TextUtils.isEmpty(c)) {
                        i = Integer.parseInt(new String(c));
                    }
                } catch (Exception e) {
                }
            }
        }
        return i;
    }

    public String c() {
        String str = "";
        try {
            char[] QTTSAudioInfo = MSC.QTTSAudioInfo(this.a);
            if (QTTSAudioInfo != null && QTTSAudioInfo.length > 0) {
                return new String(QTTSAudioInfo);
            }
        } catch (Throwable e) {
            cb.a(e);
        }
        return str;
    }

    public synchronized String c(String str) {
        String str2 = null;
        synchronized (this) {
            if (this.a != null) {
                try {
                    if (MSC.QTTSGetParam(this.a, str.getBytes(), this.c) == 0) {
                        str2 = new String(this.c.buffer);
                    }
                } catch (Exception e) {
                }
            }
        }
        return str2;
    }

    public synchronized boolean d() {
        return 2 == this.c.sesstatus;
    }

    protected String e() {
        if (this.b == null) {
            this.b = c(SpeechConstant.IST_SESSION_ID);
        }
        return this.b;
    }
}

package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.thirdparty.bf.a;
import com.iflytek.msc.MSC;
import com.iflytek.msc.MSCSessionInfo;
import java.io.UnsupportedEncodingException;
import java.util.Date;

public class au extends bf {
    private MSCSessionInfo c = new MSCSessionInfo();
    private MSCSessionInfo d = new MSCSessionInfo();
    private byte[] e = null;

    private synchronized void a(byte[] bArr, int i, int i2) throws SpeechError {
        cb.b("QISEAudioWrite enter, length: " + i);
        int QISEAudioWrite = MSC.QISEAudioWrite(this.a, bArr, i, i2, this.d);
        cb.b("QISEAudioWrite leave: " + QISEAudioWrite);
        this.c.sesstatus = this.d.sesstatus;
        if (QISEAudioWrite != 0) {
            throw new SpeechError(this.d.errorcode);
        }
    }

    public int a(Context context, String str, be beVar) throws SpeechError, UnsupportedEncodingException {
        this.a = null;
        String e = cg.e(context, beVar);
        cc.a("MSCSessionBegin", null);
        cb.a("QISESessionBegin enter");
        if (TextUtils.isEmpty(str)) {
            this.a = MSC.QISESessionBegin(e.getBytes(beVar.q()), null, this.c);
        } else {
            this.a = MSC.QISESessionBegin(e.getBytes(beVar.q()), str.getBytes(beVar.q()), this.c);
            cb.a("sessionBegin userModelId:" + str);
        }
        cb.a("QISESessionBegin leave: " + this.c.errorcode);
        cc.a("SessionBeginEnd", null);
        int i = this.c.errorcode;
        if (i == 0 || i == 10129 || i == 10113 || i == 10132) {
            return i;
        }
        throw new SpeechError(i);
    }

    public synchronized void a() throws SpeechError {
        byte[] bArr = new byte[0];
        cc.a("LastDataFlag", null);
        cb.a("IseSession pushEndFlag");
        a(bArr, 0, 4);
    }

    public void a(String str) {
        if (this.a != null) {
            if (TextUtils.isEmpty(str)) {
                str = "unknown";
            }
            cb.a("ISESessionEnd enter ");
            long currentTimeMillis = System.currentTimeMillis();
            cb.a("ISESessionEnd leave: " + MSC.QISESessionEnd(this.a, str.getBytes()) + " time:" + (System.currentTimeMillis() - currentTimeMillis));
            this.a = null;
            this.b = null;
        }
    }

    public synchronized void a(byte[] bArr, int i) throws SpeechError {
        a(bArr, i, 2);
    }

    public synchronized void a(byte[] bArr, byte[] bArr2) throws SpeechError {
        cb.a("QISETextPut enter");
        int QISETextPut = MSC.QISETextPut(this.a, bArr, bArr2);
        cb.a("QISETextPut leave: " + QISETextPut);
        if (QISETextPut != 0) {
            throw new SpeechError(QISETextPut);
        }
    }

    public synchronized int b() {
        return this.d.epstatues;
    }

    public synchronized String b(String str) {
        String str2 = null;
        synchronized (this) {
            if (this.a != null) {
                try {
                    if (MSC.QISEGetParam(this.a, str.getBytes(), this.c) == 0) {
                        str2 = new String(this.c.buffer);
                    }
                } catch (Exception e) {
                }
            }
        }
        return str2;
    }

    public synchronized int c() {
        int QISEGetParam;
        int i = 0;
        synchronized (this) {
            if (this.a != null) {
                try {
                    QISEGetParam = MSC.QISEGetParam(this.a, SpeechConstant.VOLUME.getBytes(), this.d);
                    if (QISEGetParam == 0) {
                        try {
                            i = Integer.parseInt(new String(new String(this.d.buffer)));
                        } catch (Throwable th) {
                            cb.b("getAudioVolume Exception vadret = " + QISEGetParam);
                            return i;
                        }
                    }
                    cb.b("VAD CHECK FALSE");
                } catch (Throwable th2) {
                    QISEGetParam = 0;
                    cb.b("getAudioVolume Exception vadret = " + QISEGetParam);
                    return i;
                }
            }
        }
        return i;
    }

    public byte[] d() {
        return this.e;
    }

    public a e() throws SpeechError {
        Date date = new Date();
        this.e = MSC.QISEGetResult(this.a, this.c);
        cb.b("QISRGetResult leave: " + (this.e != null) + " time:" + (new Date().getTime() - date.getTime()));
        int i = this.c.errorcode;
        if (i == 0) {
            i = this.c.rsltstatus;
            switch (i) {
                case 0:
                    cb.a("ResultStatus: hasResult" + i);
                    return a.hasResult;
                case 2:
                    cb.b("ResultStatus: noResult" + i);
                    return a.noResult;
                case 5:
                    cb.a("ResultStatus: resultOver" + i);
                    return a.resultOver;
                default:
                    cb.a("IseSession getResult get unmatched result status: " + i);
                    return a.noResult;
            }
        }
        cb.c("Result: error " + i);
        throw new SpeechError(i);
    }

    protected String f() {
        if (this.b == null) {
            this.b = b(SpeechConstant.IST_SESSION_ID);
        }
        return this.b;
    }
}

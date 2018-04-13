package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.SystemClock;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.thirdparty.bf.a;
import com.iflytek.msc.MSC;
import com.iflytek.msc.MSCSessionInfo;
import java.io.UnsupportedEncodingException;
import java.util.Date;

public class bb extends bf {
    private MSCSessionInfo c = new MSCSessionInfo();
    private MSCSessionInfo d = new MSCSessionInfo();
    private MSCSessionInfo e = new MSCSessionInfo();
    private byte[] f = null;

    private synchronized void a(byte[] bArr, int i, int i2) throws SpeechError {
        int QISVAudioWrite = MSC.QISVAudioWrite(this.a, null, bArr, i, i2, this.c);
        cb.b("QISVAudioWrite error:" + QISVAudioWrite);
        if (QISVAudioWrite != 0) {
            throw new SpeechError(QISVAudioWrite);
        }
    }

    public int a(Context context, be beVar) throws UnsupportedEncodingException, SpeechError {
        int i;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        String e = beVar.x().e(SpeechConstant.ISV_VID);
        String b = cg.b(context, beVar);
        cb.a("sendRequest enter ");
        cc.a("LastDataFlag", null);
        char[] QISVQueDelModel = MSC.QISVQueDelModel(e == null ? null : e.getBytes(beVar.q()), b.getBytes(beVar.q()), this.e);
        cc.a("GetNotifyResult", null);
        MSC.QISVQueDelModelRelease(QISVQueDelModel);
        if (this.e.errorcode != 0) {
            i = this.e.errorcode;
        } else {
            i = "true".equals(new String(this.e.buffer)) ? 0 : -1;
        }
        cb.a("sendRequest leave:" + i + " time:" + (SystemClock.elapsedRealtime() - elapsedRealtime));
        if (i == 0 || -1 == i) {
            return i;
        }
        throw new SpeechError(i);
    }

    public int a(Context context, String str, be beVar) throws SpeechError, UnsupportedEncodingException {
        String b = cg.b(context, beVar);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        cc.a("MSCSessionBegin", null);
        cb.a("QISVSessionBegin begin");
        this.a = MSC.QISVSessionBegin(b.getBytes(beVar.q()), str == null ? null : str.getBytes(beVar.q()), this.c);
        cc.a("SessionBeginEnd", null);
        cb.a("QISVSessionBegin ret: " + this.c.errorcode + " time:" + (SystemClock.elapsedRealtime() - elapsedRealtime));
        int i = this.c.errorcode;
        if (i == 0 || i == 10129 || i == 10113 || i == 10132) {
            return 0;
        }
        throw new SpeechError(i);
    }

    public synchronized void a() throws SpeechError {
        byte[] bArr = new byte[0];
        cc.a("LastDataFlag", null);
        cb.a("IsvSession pushEndFlag");
        a(bArr, 0, 4);
    }

    public void a(String str) {
        if (this.a != null) {
            cb.a("isv sessionEnd enter ");
            cb.a("isv sessionEnd leave:" + (MSC.QISVSessionEnd(this.a, str.getBytes()) == 0) + " time:" + (System.currentTimeMillis() - System.currentTimeMillis()));
            this.a = null;
            this.b = null;
        }
    }

    public synchronized void a(byte[] bArr, int i) throws SpeechError {
        a(bArr, i, 2);
    }

    public synchronized String b(String str) {
        String str2 = null;
        synchronized (this) {
            if (this.a != null) {
                try {
                    if (MSC.QISVGetParam(this.a, str.getBytes(), this.c) == 0) {
                        str2 = new String(this.c.buffer);
                    }
                } catch (Exception e) {
                }
            }
        }
        return str2;
    }

    public synchronized boolean b() {
        return this.c.epstatues >= 3;
    }

    public synchronized int c() {
        int i = 0;
        synchronized (this) {
            int QISVGetParam;
            try {
                QISVGetParam = MSC.QISVGetParam(this.a, SpeechConstant.VOLUME.getBytes(), this.d);
                if (QISVGetParam == 0) {
                    try {
                        i = Integer.parseInt(new String(new String(this.d.buffer)));
                    } catch (Exception e) {
                        cb.b("getAudioVolume Exception vadret = " + QISVGetParam);
                        return i;
                    }
                } else {
                    cb.b("VAD CHECK FALSE");
                }
            } catch (Exception e2) {
                QISVGetParam = i;
                cb.b("getAudioVolume Exception vadret = " + QISVGetParam);
                return i;
            }
        }
        return i;
    }

    public byte[] d() {
        return this.f;
    }

    public a e() throws SpeechError {
        Date date = new Date();
        this.f = MSC.QISVGetResult(this.a, null, this.c);
        cb.b("QISVGetResult leavel:" + (this.f != null) + " time:" + (new Date().getTime() - date.getTime()));
        int i = this.c.errorcode;
        if (i == 0) {
            i = this.c.rsltstatus;
            switch (i) {
                case 0:
                case 5:
                    if (this.f != null) {
                        cb.a("ResultStatus: hasResult" + i);
                        return a.hasResult;
                    }
                    break;
                case 1:
                    cb.a("ResultStatus: noResult" + i);
                    throw new SpeechError(20005);
            }
            return a.noResult;
        }
        cb.c("Result: error errorcode is " + i);
        throw new SpeechError(i);
    }

    protected String f() {
        if (this.b == null) {
            this.b = b(SpeechConstant.IST_SESSION_ID);
        }
        return this.b;
    }
}

package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.iflytek.cloud.GrammarListener;
import com.iflytek.cloud.LexiconListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.msc.MSC;
import com.iflytek.msc.MSCSessionInfo;
import java.io.UnsupportedEncodingException;

public class aw extends bf {
    private static GrammarListener f;
    private static LexiconListener g;
    private MSCSessionInfo c = new MSCSessionInfo();
    private MSCSessionInfo d = new MSCSessionInfo();
    private byte[] e = null;
    private String h = "";

    private synchronized void a(byte[] bArr, int i, int i2) throws SpeechError {
        int QISRAudioWrite = MSC.QISRAudioWrite(this.a, bArr, i, i2, this.d);
        this.c.sesstatus = this.d.sesstatus;
        cb.b("QISRAudioWrite length:" + i);
        if (QISRAudioWrite != 0) {
            throw new SpeechError(this.d.errorcode);
        }
    }

    public int a(Context context, String str, be beVar) throws SpeechError, UnsupportedEncodingException {
        String a = cg.a(context, str, beVar);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        cb.a("QISRSessionBegin begin, grammar: " + str);
        synchronized (aw.class) {
            cc.a("MSCSessionBegin", null);
            if (TextUtils.isEmpty(str)) {
                this.a = MSC.QISRSessionBegin(null, a.getBytes(beVar.q()), this.c);
            } else {
                this.a = MSC.QISRSessionBegin(str.getBytes(beVar.q()), a.getBytes(beVar.q()), this.c);
            }
            cc.a("SessionBeginEnd", null);
        }
        cb.a("QISRSessionBegin end: " + this.c.errorcode + " time:" + (SystemClock.elapsedRealtime() - elapsedRealtime));
        int i = this.c.errorcode;
        if (i == 0 || i == 10129 || i == 10113 || i == 10132) {
            return i;
        }
        throw new SpeechError(i);
    }

    public int a(String str, String str2, GrammarListener grammarListener, ce ceVar) {
        f = grammarListener;
        String ceVar2 = ceVar.toString();
        String b = ceVar.b(SpeechConstant.TEXT_ENCODING, "utf-8");
        String b2 = ceVar.b("pte", "utf-8");
        try {
            byte[] bytes = str2.getBytes(b);
            cc.a("LastDataFlag", null);
            cb.a("QISRBuildGrammar begin, garmmar: " + str2);
            int QISRBuildGrammar = MSC.QISRBuildGrammar(str.getBytes(b2), bytes, bytes.length, ceVar2.getBytes(b2), "grammarCallBack", this);
            cb.a("QISRBuildGrammar leave: " + QISRBuildGrammar);
            return QISRBuildGrammar;
        } catch (Throwable e) {
            cb.a(e);
            return 20012;
        }
    }

    public int a(String str, String str2, LexiconListener lexiconListener, ce ceVar) {
        g = lexiconListener;
        this.h = str;
        ceVar.a(SpeechConstant.TEXT_ENCODING, "utf-8", false);
        String b = ceVar.b(SpeechConstant.TEXT_ENCODING, "utf-8");
        String b2 = ceVar.b("pte", "utf-8");
        String ceVar2 = ceVar.toString();
        try {
            byte[] bytes = str2.getBytes(b);
            cb.a("QISRUpdateLexicon begin, name: " + str + "content: " + str2);
            cc.a("LastDataFlag", null);
            int QISRUpdateLexicon = MSC.QISRUpdateLexicon(str.getBytes(b2), bytes, bytes.length, ceVar2.getBytes(b2), "lexiconCallBack", this);
            cb.a("QISRUpdateLexicon leave: " + QISRUpdateLexicon);
            return QISRUpdateLexicon;
        } catch (Throwable e) {
            cb.a(e);
            return 20012;
        }
    }

    public synchronized void a() throws SpeechError {
        byte[] bArr = new byte[0];
        cc.a("LastDataFlag", null);
        cb.a("IsrSession pushEndFlag");
        a(bArr, 0, 4);
    }

    public void a(String str) {
        if (this.a != null) {
            cb.a("sessionEnd enter ");
            cb.a("sessionEnd leavel:" + (MSC.QISRSessionEnd(this.a, str.getBytes()) == 0) + " time:" + (System.currentTimeMillis() - System.currentTimeMillis()));
            this.a = null;
            this.b = null;
        }
    }

    public synchronized void a(byte[] bArr, int i) throws SpeechError {
        a(bArr, i, 2);
    }

    public synchronized boolean a(String str, String str2) {
        boolean z = false;
        synchronized (this) {
            if (!(TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || this.a == null)) {
                int QISRSetParam;
                try {
                    QISRSetParam = MSC.QISRSetParam(this.a, str.getBytes("utf-8"), str2.getBytes("utf-8"));
                } catch (Throwable e) {
                    cb.a(e);
                    QISRSetParam = -1;
                }
                if (QISRSetParam == 0) {
                    z = true;
                }
            }
        }
        return z;
    }

    public synchronized int b() {
        int QISRGetParam;
        int i = 0;
        synchronized (this) {
            try {
                QISRGetParam = MSC.QISRGetParam(this.a, SpeechConstant.VOLUME.getBytes(), this.d);
                if (QISRGetParam == 0) {
                    try {
                        i = Integer.parseInt(new String(new String(this.d.buffer)));
                    } catch (Exception e) {
                        cb.b("getAudioVolume Exception vadret = " + QISRGetParam);
                        return i;
                    }
                } else {
                    cb.b("VAD CHECK FALSE");
                }
            } catch (Exception e2) {
                QISRGetParam = i;
                cb.b("getAudioVolume Exception vadret = " + QISRGetParam);
                return i;
            }
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
                } catch (Throwable e) {
                    cb.a(e);
                }
            }
        }
        return i;
    }

    protected String c() {
        if (this.b == null) {
            this.b = c(SpeechConstant.IST_SESSION_ID);
        }
        return this.b;
    }

    public synchronized String c(String str) {
        String str2 = null;
        synchronized (this) {
            if (this.a != null) {
                try {
                    if (MSC.QISRGetParam(this.a, str.getBytes(), this.c) == 0) {
                        str2 = new String(this.c.buffer);
                    }
                } catch (Exception e) {
                }
            }
        }
        return str2;
    }

    protected String d() {
        return c("audio_url");
    }

    int grammarCallBack(int i, char[] cArr) {
        SpeechError speechError = null;
        cb.a("grammarCallBack begin, errorCode: " + i);
        cc.a("GetNotifyResult", null);
        if (f != null) {
            if (i != 0) {
                GrammarListener grammarListener = f;
                String str = "";
                if (i != 0) {
                    speechError = new SpeechError(i);
                }
                grammarListener.onBuildFinish(str, speechError);
            } else {
                f.onBuildFinish(String.valueOf(cArr), null);
            }
        }
        cb.a("grammarCallBack begin, leave");
        return 0;
    }

    int lexiconCallBack(int i, char[] cArr) {
        SpeechError speechError = null;
        cb.a("lexiconCallBack begin, errorCode: " + i);
        cc.a("GetNotifyResult", null);
        if (g != null) {
            if (i != 0) {
                LexiconListener lexiconListener = g;
                String str = this.h;
                if (i != 0) {
                    speechError = new SpeechError(i);
                }
                lexiconListener.onLexiconUpdated(str, speechError);
            } else {
                g.onLexiconUpdated(this.h, null);
            }
        }
        cb.a("lexiconCallBack leave");
        return 0;
    }
}

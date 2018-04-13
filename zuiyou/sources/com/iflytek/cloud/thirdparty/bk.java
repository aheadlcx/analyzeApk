package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechListener;
import com.iflytek.cloud.SpeechUtility;

public class bk extends be {
    private SpeechListener a = null;
    private bj b = new bj();
    private String c = null;

    private class a {
        final /* synthetic */ bk a;
        private byte[] b = null;
        private String c = "";

        public a(bk bkVar, byte[] bArr, String str) {
            this.a = bkVar;
            this.b = bArr;
            this.c = str;
        }

        public byte[] a() {
            return this.b;
        }

        public String b() {
            return this.c;
        }
    }

    public bk(Context context, ce ceVar) {
        super(context);
        a(ceVar);
    }

    public bk(Context context, ce ceVar, HandlerThread handlerThread) {
        super(context, handlerThread);
        a(ceVar);
    }

    public SpeechError a(String str, String str2) {
        SpeechError speechError;
        this.c = "auth";
        SpeechError speechError2 = null;
        try {
            bj.a(this.t, str, str2, (be) this);
            if (speechError2 == null) {
                return speechError2;
            }
            cb.a(z() + " occur Error = " + speechError2.toString());
            return speechError2;
        } catch (Throwable e) {
            cb.a(e);
            if (e == null) {
                return e;
            }
            cb.a(z() + " occur Error = " + e.toString());
            return e;
        } catch (Throwable e2) {
            cb.a(e2);
            speechError = new SpeechError(20010);
            if (speechError == null) {
                return speechError;
            }
            cb.a(z() + " occur Error = " + speechError.toString());
            return speechError;
        } catch (Throwable e22) {
            cb.a(e22);
            speechError = new SpeechError(21003);
            if (speechError == null) {
                return speechError;
            }
            cb.a(z() + " occur Error = " + speechError.toString());
            return speechError;
        } catch (Throwable th) {
            if (speechError2 != null) {
                cb.a(z() + " occur Error = " + speechError2.toString());
            }
        }
    }

    protected void a(Message message) throws Throwable, SpeechError {
        super.a(message);
        if (SpeechUtility.getUtility() == null) {
            cb.c("MscCommon process while utility is null!");
            b(new SpeechError(20015));
            return;
        }
        byte[] a;
        switch (message.what) {
            case 10:
                a aVar = (a) message.obj;
                if (aVar.a() != null && aVar.a().length > 0) {
                    a = this.b.a(this.t, aVar.b(), aVar.a(), (be) this);
                    break;
                }
                throw new SpeechError(20009);
            case 11:
                a = this.b.a(this.t, this);
                break;
            case 12:
                String str = (String) message.obj;
                if (!TextUtils.isEmpty(str)) {
                    a(b.waitresult);
                    byte[] a2 = this.b.a(this.t, this, str);
                    try {
                        this.x.a(a2 == null ? null : new String(a2), true);
                        a = a2;
                        break;
                    } catch (Throwable th) {
                        cb.c("DC exception:");
                        cb.a(th);
                        a = a2;
                        break;
                    }
                }
                throw new SpeechError(20009);
            default:
                a = null;
                break;
        }
        if (a == null) {
            throw new SpeechError(20004);
        }
        if (!(this.a == null || this.u)) {
            this.a.onBufferReceived(a);
        }
        b(null);
    }

    protected void a(SpeechError speechError) {
        super.a(speechError);
        if (this.a != null && !this.u) {
            this.a.onCompleted(speechError);
        }
    }

    public void a(SpeechListener speechListener) {
        this.a = speechListener;
        a(11);
    }

    public void a(SpeechListener speechListener, String str) {
        a(b.start);
        this.c = "sch";
        this.a = speechListener;
        try {
            this.x.b();
        } catch (Throwable th) {
            cb.c("DC exception:");
            cb.a(th);
        }
        d(obtainMessage(12, str));
    }

    public void a(SpeechListener speechListener, String str, byte[] bArr) {
        this.c = "uup";
        this.a = speechListener;
        d(obtainMessage(10, new a(this, bArr, str)));
    }

    public String e() {
        return null;
    }

    public String f() {
        return null;
    }

    public String g() {
        return this.c;
    }
}

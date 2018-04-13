package com.iflytek.cloud;

import android.content.Context;
import com.iflytek.cloud.thirdparty.bh;
import com.iflytek.cloud.thirdparty.cb;
import com.iflytek.cloud.thirdparty.ck;

public class SpeakerVerifier extends bh {
    private static SpeakerVerifier a = null;
    private ck d = null;

    protected SpeakerVerifier(Context context, InitListener initListener) {
        this.d = new ck(context);
    }

    public static SpeakerVerifier createVerifier(Context context, InitListener initListener) {
        synchronized (b) {
            if (a == null && SpeechUtility.getUtility() != null) {
                a = new SpeakerVerifier(context, initListener);
            }
        }
        return a;
    }

    public static SpeakerVerifier getVerifier() {
        return a;
    }

    public void cancel() {
        if (this.d != null && this.d.f()) {
            this.d.cancel(false);
        }
    }

    public boolean destroy() {
        boolean z = true;
        ck ckVar = this.d;
        if (ckVar != null) {
            z = ckVar.destroy();
        }
        if (z) {
            z = super.destroy();
            if (z) {
                synchronized (b) {
                    a = null;
                }
            }
        }
        return z;
    }

    public String generatePassword(int i) {
        if (this.d != null) {
            return this.d.a(i);
        }
        cb.c("SpeakerVerifier getPasswordList failed, is not running");
        return null;
    }

    public String getParameter(String str) {
        return super.getParameter(str);
    }

    public void getPasswordList(SpeechListener speechListener) {
        if (this.d != null) {
            this.d.setParameter(SpeechConstant.PARAMS, null);
            this.c.a(SpeechConstant.SUBJECT, SpeechConstant.ENG_IVP, true);
            this.c.a("rse", "gb2312", false);
            this.d.setParameter(this.c);
            this.d.a(speechListener);
            return;
        }
        cb.c("SpeakerVerifier getPasswordList failed, is not running");
    }

    public boolean isListening() {
        return this.d != null && this.d.f();
    }

    public int sendRequest(String str, String str2, SpeechListener speechListener) {
        return this.d.setParameter(this.c) ? this.d.a(str, str2, speechListener) : 20012;
    }

    public boolean setParameter(String str, String str2) {
        return super.setParameter(str, str2);
    }

    public int startListening(VerifierListener verifierListener) {
        if (this.d == null) {
            return 21001;
        }
        this.d.setParameter(this.c);
        return this.d.a(verifierListener);
    }

    public void stopListening() {
        if (this.d == null || !this.d.f()) {
            cb.c("SpeakerVerifier stopListening failed, is not running");
        } else {
            this.d.e();
        }
    }

    public int writeAudio(byte[] bArr, int i, int i2) {
        if (this.d != null && this.d.f()) {
            return this.d.a(bArr, i, i2);
        }
        cb.c("SpeakerVerifier writeAudio failed, is not running");
        return 21004;
    }
}

package com.iflytek.cloud.thirdparty;

import android.text.TextUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.speech.ISpeechModule;

public abstract class bh {
    protected static final Object b = new Object();
    protected ce c = new ce();

    public enum a {
        MSC
    }

    protected bh() {
    }

    protected a a(String str, ISpeechModule iSpeechModule) {
        return a.MSC;
    }

    public boolean destroy() {
        return true;
    }

    public String getParameter(String str) {
        return SpeechConstant.PARAMS.equals(str) ? this.c.toString() : this.c.e(str);
    }

    public boolean setParameter(ce ceVar) {
        this.c = ceVar.b();
        return true;
    }

    public boolean setParameter(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.equals(SpeechConstant.PARAMS)) {
            if (TextUtils.isEmpty(str2)) {
                this.c.a();
                return true;
            }
            this.c.b(str2);
            return true;
        } else if (TextUtils.isEmpty(str2)) {
            return this.c.c(str).booleanValue();
        } else {
            this.c.a(str, str2);
            return true;
        }
    }
}

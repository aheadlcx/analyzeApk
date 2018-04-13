package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechListener;
import com.iflytek.cloud.TextUnderstanderListener;
import com.iflytek.cloud.UnderstanderResult;
import com.iflytek.msc.MSC;

public class cp extends bi {

    private class a implements SpeechListener {
        final /* synthetic */ cp a;
        private TextUnderstanderListener b;

        public a(cp cpVar, TextUnderstanderListener textUnderstanderListener) {
            this.a = cpVar;
            this.b = textUnderstanderListener;
        }

        public void onBufferReceived(byte[] bArr) {
            if (bArr != null) {
                try {
                    this.b.onResult(new UnderstanderResult(new String(bArr, "utf-8")));
                } catch (Throwable e) {
                    cb.a(e);
                } catch (Throwable e2) {
                    cb.a(e2);
                }
            }
        }

        public void onCompleted(SpeechError speechError) {
            if (this.b != null && speechError != null) {
                this.b.onError(speechError);
            }
        }

        public void onEvent(int i, Bundle bundle) {
        }
    }

    public cp(Context context) {
        super(context);
    }

    public int a(String str, TextUnderstanderListener textUnderstanderListener) {
        try {
            if (TextUtils.isEmpty(getParameter(SpeechConstant.ASR_SCH))) {
                setParameter(SpeechConstant.ASR_SCH, "1");
            }
            if (TextUtils.isEmpty(getParameter(SpeechConstant.NLP_VERSION))) {
                setParameter(SpeechConstant.NLP_VERSION, MSC.isIflyVersion() ? "3.0" : "2.0");
            }
            if (TextUtils.isEmpty(getParameter(SpeechConstant.RESULT_TYPE))) {
                setParameter(SpeechConstant.RESULT_TYPE, "json");
            }
            if (d()) {
                return 21005;
            }
            this.e = new bk(this.a, this.c, a("textunderstand"));
            ((bk) this.e).a(new a(this, new a(this, textUnderstanderListener)), str);
            return 0;
        } catch (Throwable e) {
            Throwable th = e;
            int errorCode = th.getErrorCode();
            cb.a(th);
            return errorCode;
        } catch (Throwable e2) {
            cb.a(e2);
            return 20999;
        }
    }

    public void cancel(boolean z) {
        super.cancel(z);
    }

    public boolean destroy() {
        return super.destroy();
    }

    public boolean e() {
        return d();
    }

    public String getParameter(String str) {
        return super.getParameter(str);
    }

    public boolean setParameter(String str, String str2) {
        return super.setParameter(str, str2);
    }
}

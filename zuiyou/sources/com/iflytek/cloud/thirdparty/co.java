package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUnderstanderListener;
import com.iflytek.cloud.UnderstanderResult;
import com.iflytek.msc.MSC;

public class co {
    protected static co a = null;
    private cm b = null;

    private class a implements RecognizerListener {
        final /* synthetic */ co a;
        private final SpeechUnderstanderListener b;

        public a(co coVar, SpeechUnderstanderListener speechUnderstanderListener) {
            this.a = coVar;
            this.b = speechUnderstanderListener;
        }

        public void onBeginOfSpeech() {
            if (this.b != null) {
                this.b.onBeginOfSpeech();
            }
        }

        public void onEndOfSpeech() {
            if (this.b != null) {
                this.b.onEndOfSpeech();
            }
        }

        public void onError(SpeechError speechError) {
            if (this.b != null && speechError != null) {
                this.b.onError(speechError);
            }
        }

        public void onEvent(int i, int i2, int i3, Bundle bundle) {
            if (this.b != null) {
                this.b.onEvent(i, i2, i3, bundle);
            }
        }

        public void onResult(RecognizerResult recognizerResult, boolean z) {
            if (this.b != null) {
                this.b.onResult(new UnderstanderResult(recognizerResult.getResultString()));
            }
        }

        public void onVolumeChanged(int i, byte[] bArr) {
            if (this.b != null) {
                this.b.onVolumeChanged(i, bArr);
            }
        }
    }

    public co(Context context) {
        this.b = new cm(context);
    }

    public int a(SpeechUnderstanderListener speechUnderstanderListener) {
        RecognizerListener aVar = new a(this, speechUnderstanderListener);
        if (TextUtils.isEmpty(this.b.getParameter(SpeechConstant.ASR_SCH))) {
            this.b.setParameter(SpeechConstant.ASR_SCH, "1");
        }
        if (TextUtils.isEmpty(this.b.getParameter(SpeechConstant.NLP_VERSION))) {
            this.b.setParameter(SpeechConstant.NLP_VERSION, MSC.isIflyVersion() ? "3.0" : "2.0");
        }
        if (TextUtils.isEmpty(this.b.getParameter(SpeechConstant.RESULT_TYPE))) {
            this.b.setParameter(SpeechConstant.RESULT_TYPE, "json");
        }
        this.b.a(aVar);
        return 0;
    }

    public int a(byte[] bArr, int i, int i2) {
        return this.b.a(bArr, i, i2);
    }

    public void a(boolean z) {
        this.b.cancel(z);
    }

    public boolean a() {
        return this.b.g();
    }

    public boolean a(ce ceVar) {
        return this.b.setParameter(ceVar);
    }

    public void b() {
        this.b.e();
    }

    public boolean c() {
        boolean destroy = this.b.destroy();
        if (destroy) {
            a = null;
        }
        return destroy;
    }
}

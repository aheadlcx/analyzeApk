package com.iflytek.cloud.thirdparty;

import android.util.Log;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.record.PcmRecorder;
import com.iflytek.cloud.record.PcmRecorder.PcmRecordListener;

public class m extends j implements PcmRecordListener {
    private PcmRecorder c;
    private int d = 16000;

    public m(k kVar) {
        super(kVar);
    }

    private void e() {
        this.d = ac.a("iat", "sample_rate", 16000);
        this.c = new PcmRecorder(this.d, 40);
    }

    public int a() {
        return this.d;
    }

    public int c() {
        if (b()) {
            cb.a("SystemAudioCaptor", "SingleAudioCaptor was already started.");
            return 0;
        }
        e();
        if (this.c == null) {
            return 0;
        }
        try {
            this.c.startRecording(this);
            return 0;
        } catch (SpeechError e) {
            e.printStackTrace();
            int errorCode = e.getErrorCode();
            Log.e("SystemAudioCaptor", "SingleAudioCaptor start error, error=" + errorCode);
            return errorCode;
        }
    }

    public void d() {
        if (b() && this.c != null) {
            this.c.stopRecord(true);
        }
    }

    public void onError(SpeechError speechError) {
        if (this.a != null) {
            this.a.a(speechError.getErrorCode(), speechError.getErrorDescription());
        }
        Log.e("SystemAudioCaptor", "SingleAudioCaptor error, error=" + speechError.getErrorCode());
    }

    public void onRecordBuffer(byte[] bArr, int i, int i2) {
        if (this.a != null) {
            Object obj = new byte[i2];
            System.arraycopy(bArr, i, obj, 0, i2);
            this.a.a(obj, i2, null);
        }
    }

    public void onRecordReleased() {
        this.b = false;
        if (this.a != null) {
            this.a.b();
        }
        cb.a("SystemAudioCaptor", "SingleAudioCaptor stopped.");
    }

    public void onRecordStarted(boolean z) {
        if (z) {
            this.b = true;
            if (this.a != null) {
                this.a.a();
            }
            cb.a("SystemAudioCaptor", "SingleAudioCaptor started.");
        }
    }
}

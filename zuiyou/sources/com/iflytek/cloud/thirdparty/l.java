package com.iflytek.cloud.thirdparty;

import android.util.Log;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.alsa.AlsaRecorder;
import com.iflytek.alsa.AlsaRecorder.PcmListener;

public class l extends j implements PcmListener {
    private int c = 96000;
    private AlsaRecorder d;
    private int e = 1536;
    private int f = 2;

    public l(k kVar) {
        super(kVar);
    }

    private void e() {
        this.f = ac.a(AIUIConstant.AUDIO_CAPTOR_ALSA, AIUIConstant.KEY_SOUND_CARD, 2);
        this.c = ac.a(AIUIConstant.AUDIO_CAPTOR_ALSA, AIUIConstant.KEY_CARD_SAMPLE_RATE, 96000);
        this.e = ac.a(AIUIConstant.AUDIO_CAPTOR_ALSA, "period_size", this.e);
        this.d = AlsaRecorder.createInstance(this.f, this.c, this.e);
        this.d.setBufferSize(12288);
    }

    public int a() {
        return this.c;
    }

    public int c() {
        int i = -1;
        if (b()) {
            cb.a("AlsaAudioCaptor", "AlsaAudioCaptor was already started.");
            return 0;
        }
        e();
        if (this.d != null) {
            i = this.d.startRecording(this);
        }
        if (i == 0) {
            this.b = true;
            if (this.a != null) {
                this.a.a();
            }
            cb.a("AlsaAudioCaptor", "AlsaAudioCaptor started.");
            return i;
        }
        Log.e("AlsaAudioCaptor", "AlsaAudioCaptor start error, error=" + 20006);
        return 20006;
    }

    public void d() {
        if (b()) {
            if (this.d != null) {
                this.d.stopRecording();
                this.d.destroy();
                this.b = false;
                if (this.a != null) {
                    this.a.b();
                }
            }
            cb.a("AlsaAudioCaptor", "AlsaAudioCaptor stopped.");
            return;
        }
        Log.e("AlsaAudioCaptor", "AlsaAudioCaptor was not started.");
    }

    public void onPcmData(byte[] bArr, int i) {
        if (this.a != null) {
            this.a.a(bArr, i, null);
        }
    }
}

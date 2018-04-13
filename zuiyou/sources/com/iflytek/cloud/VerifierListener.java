package com.iflytek.cloud;

import android.os.Bundle;

public interface VerifierListener {
    void onBeginOfSpeech();

    void onEndOfSpeech();

    void onError(SpeechError speechError);

    void onEvent(int i, int i2, int i3, Bundle bundle);

    void onResult(VerifierResult verifierResult);

    void onVolumeChanged(int i, byte[] bArr);
}

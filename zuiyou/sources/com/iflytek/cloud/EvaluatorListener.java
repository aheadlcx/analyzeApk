package com.iflytek.cloud;

import android.os.Bundle;

public interface EvaluatorListener {
    void onBeginOfSpeech();

    void onEndOfSpeech();

    void onError(SpeechError speechError);

    void onEvent(int i, int i2, int i3, Bundle bundle);

    void onResult(EvaluatorResult evaluatorResult, boolean z);

    void onVolumeChanged(int i, byte[] bArr);
}

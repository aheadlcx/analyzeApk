package com.iflytek.cloud;

import android.os.Bundle;

public interface RecognizerListener {
    void onBeginOfSpeech();

    void onEndOfSpeech();

    void onError(SpeechError speechError);

    void onEvent(int i, int i2, int i3, Bundle bundle);

    void onResult(RecognizerResult recognizerResult, boolean z);

    void onVolumeChanged(int i, byte[] bArr);
}

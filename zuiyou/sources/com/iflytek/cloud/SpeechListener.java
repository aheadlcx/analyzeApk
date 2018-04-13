package com.iflytek.cloud;

import android.os.Bundle;

public interface SpeechListener {
    void onBufferReceived(byte[] bArr);

    void onCompleted(SpeechError speechError);

    void onEvent(int i, Bundle bundle);
}

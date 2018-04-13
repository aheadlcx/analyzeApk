package com.iflytek.speech;

import android.content.Intent;

public interface ISpeechModule {
    boolean destory();

    Intent getIntent();

    String getParameter(String str);

    boolean isAvailable();

    int setParameter(String str, String str2);
}

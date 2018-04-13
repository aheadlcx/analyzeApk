package com.iflytek.cloud;

public interface LexiconListener {
    void onLexiconUpdated(String str, SpeechError speechError);
}

package com.iflytek.cloud;

public interface GrammarListener {
    void onBuildFinish(String str, SpeechError speechError);
}

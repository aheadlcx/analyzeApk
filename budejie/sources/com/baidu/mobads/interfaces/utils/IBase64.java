package com.baidu.mobads.interfaces.utils;

public interface IBase64 {

    public interface EventHandler {
        void onTimer(int i);

        void onTimerComplete();
    }

    String decodeStr(String str);

    String encode(String str);
}

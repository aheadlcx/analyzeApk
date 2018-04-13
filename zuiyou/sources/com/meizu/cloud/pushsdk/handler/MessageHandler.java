package com.meizu.cloud.pushsdk.handler;

import android.content.Intent;

public interface MessageHandler {
    int getProcessorType();

    boolean messageMatch(Intent intent);

    boolean sendMessage(Intent intent);
}

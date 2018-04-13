package com.meizu.cloud.pushsdk.notification;

import com.meizu.cloud.pushsdk.handler.MessageV3;

public interface PushNotification {
    void show(MessageV3 messageV3);
}

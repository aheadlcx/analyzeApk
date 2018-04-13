package com.meizu.cloud.pushsdk.networking.interfaces;

import com.meizu.cloud.pushsdk.networking.common.ConnectionQuality;

public interface ConnectionQualityChangeListener {
    void onChange(ConnectionQuality connectionQuality, int i);
}

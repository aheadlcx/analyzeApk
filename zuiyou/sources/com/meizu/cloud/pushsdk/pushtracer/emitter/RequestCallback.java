package com.meizu.cloud.pushsdk.pushtracer.emitter;

public interface RequestCallback {
    void onFailure(int i, int i2);

    void onSuccess(int i);
}

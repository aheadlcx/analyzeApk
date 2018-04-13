package com.tencent.smtt.sdk;

public enum WebSettings$ZoomDensity {
    FAR(150),
    MEDIUM(100),
    CLOSE(75);
    
    int value;

    private WebSettings$ZoomDensity(int i) {
        this.value = i;
    }
}

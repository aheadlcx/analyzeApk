package com.tencent.smtt.sdk;

public enum WebSettings$TextSize {
    SMALLEST(50),
    SMALLER(75),
    NORMAL(100),
    LARGER(125),
    LARGEST(150);
    
    int value;

    private WebSettings$TextSize(int i) {
        this.value = i;
    }
}

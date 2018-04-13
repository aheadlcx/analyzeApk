package com.meizu.cloud.pushsdk.pushtracer.emitter;

public enum BufferOption {
    Single(1),
    DefaultGroup(3),
    HeavyGroup(25);
    
    private int code;

    private BufferOption(int i) {
        this.code = i;
    }

    public int getCode() {
        return this.code;
    }
}

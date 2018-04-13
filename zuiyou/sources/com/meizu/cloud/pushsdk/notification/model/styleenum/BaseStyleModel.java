package com.meizu.cloud.pushsdk.notification.model.styleenum;

public enum BaseStyleModel {
    FLYME(0),
    PURE_PICTURE(1),
    ANDROID(2);
    
    private int code;

    private BaseStyleModel(int i) {
        this.code = i;
    }

    public int getCode() {
        return this.code;
    }
}

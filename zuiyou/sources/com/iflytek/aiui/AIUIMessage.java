package com.iflytek.aiui;

public final class AIUIMessage implements Cloneable {
    public int arg1;
    public int arg2;
    public byte[] data;
    public int msgType;
    public String params;

    public AIUIMessage(int i, int i2, int i3, String str, byte[] bArr) {
        this.msgType = i;
        this.arg1 = i2;
        this.arg2 = i3;
        this.params = str;
        this.data = bArr;
    }

    public Object clone() throws CloneNotSupportedException {
        AIUIMessage aIUIMessage = (AIUIMessage) super.clone();
        aIUIMessage.data = (byte[]) this.data.clone();
        return aIUIMessage;
    }
}

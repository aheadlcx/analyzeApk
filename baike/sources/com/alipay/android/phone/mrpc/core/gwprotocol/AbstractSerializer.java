package com.alipay.android.phone.mrpc.core.gwprotocol;

public abstract class AbstractSerializer implements Serializer {
    protected String a;
    protected Object b;

    public AbstractSerializer(String str, Object obj) {
        this.a = str;
        this.b = obj;
    }
}

package com.alipay.android.phone.mrpc.core.gwprotocol;

import java.lang.reflect.Type;

public abstract class AbstractDeserializer implements Deserializer {
    protected Type a;
    protected byte[] b;

    public AbstractDeserializer(Type type, byte[] bArr) {
        this.a = type;
        this.b = bArr;
    }
}

package com.alipay.android.phone.mrpc.core.gwprotocol;

public interface Serializer {
    byte[] packet();

    void setExtParam(Object obj);
}

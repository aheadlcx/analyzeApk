package com.ali.auth.third.mtop.rpc;

import mtopsdk.mtop.domain.b;

public class BaseMtopResponseData<T> implements b {
    public String actionType;
    public int code;
    public String codeGroup;
    public String message;
    public String msgCode;
    public String msgInfo;
    public T returnValue;
}

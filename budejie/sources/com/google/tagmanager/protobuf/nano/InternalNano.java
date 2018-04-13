package com.google.tagmanager.protobuf.nano;

import java.io.UnsupportedEncodingException;

public class InternalNano {
    public static final String stringDefaultValue(String str) {
        try {
            return new String(str.getBytes("ISO-8859-1"), "UTF-8");
        } catch (Throwable e) {
            throw new IllegalStateException("Java VM does not support a standard character set.", e);
        }
    }

    public static final byte[] bytesDefaultValue(String str) {
        try {
            return str.getBytes("ISO-8859-1");
        } catch (Throwable e) {
            throw new IllegalStateException("Java VM does not support a standard character set.", e);
        }
    }

    public static final byte[] copyFromUtf8(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported?");
        }
    }
}

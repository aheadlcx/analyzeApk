package com.androidexlib.util;

public class ImageServException extends Exception {
    private static final long serialVersionUID = 1;
    private String mExtra;

    public ImageServException(String str) {
        super(str);
    }

    public ImageServException(String str, String str2) {
        super(str);
        this.mExtra = str2;
    }

    public String getExtra() {
        return this.mExtra;
    }
}

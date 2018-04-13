package com.bumptech.glide.load.resource;

import java.io.OutputStream;

public class a<T> implements com.bumptech.glide.load.a<T> {
    private static final a<?> a = new a();

    public static <T> com.bumptech.glide.load.a<T> b() {
        return a;
    }

    public boolean a(T t, OutputStream outputStream) {
        return false;
    }

    public String a() {
        return "";
    }
}

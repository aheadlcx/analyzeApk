package com.facebook.drawee.controller;

import android.graphics.drawable.Animatable;
import javax.annotation.Nullable;

public interface c<INFO> {
    void a(String str);

    void a(String str, Object obj);

    void a(String str, @Nullable INFO info, @Nullable Animatable animatable);

    void a(String str, Throwable th);

    void b(String str, @Nullable INFO info);

    void b(String str, Throwable th);
}

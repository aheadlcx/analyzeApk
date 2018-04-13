package com.bumptech.glide.load.engine.c;

import android.os.Handler;
import android.os.Looper;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.a.c;
import com.bumptech.glide.load.engine.b.i;

public final class a {
    private final i a;
    private final c b;
    private final DecodeFormat c;
    private final Handler d = new Handler(Looper.getMainLooper());

    public a(i iVar, c cVar, DecodeFormat decodeFormat) {
        this.a = iVar;
        this.b = cVar;
        this.c = decodeFormat;
    }
}

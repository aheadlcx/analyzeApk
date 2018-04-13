package com.spriteapp.booklibrary.util;

import android.content.Context;
import com.bumptech.glide.i;

class GlideCacheUtil$1 implements Runnable {
    final /* synthetic */ GlideCacheUtil this$0;
    final /* synthetic */ Context val$context;

    GlideCacheUtil$1(GlideCacheUtil glideCacheUtil, Context context) {
        this.this$0 = glideCacheUtil;
        this.val$context = context;
    }

    public void run() {
        i.a(this.val$context).j();
    }
}

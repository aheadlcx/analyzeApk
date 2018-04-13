package com.liulishuo.filedownloader.services;

import android.content.Intent;
import com.liulishuo.filedownloader.d.c;

public class f {
    public static void a(c cVar) {
        if (cVar == null) {
            throw new IllegalArgumentException();
        } else if (cVar.f() != (byte) -3) {
            throw new IllegalStateException();
        } else {
            Intent intent = new Intent("filedownloader.intent.action.completed");
            intent.putExtra("model", cVar);
            com.liulishuo.filedownloader.g.c.a().sendBroadcast(intent);
        }
    }
}

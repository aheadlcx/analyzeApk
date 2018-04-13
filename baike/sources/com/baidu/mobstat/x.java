package com.baidu.mobstat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import java.io.Closeable;
import java.util.ArrayList;

abstract class x implements Closeable {
    private af a;

    public abstract long a(String str, String str2);

    public abstract ArrayList<w> a(int i, int i2);

    public abstract boolean b(long j);

    public x(String str, String str2) {
        Context aeVar = new ae();
        this.a = new af(aeVar, str);
        if (aeVar.getDatabasePath(".confd") != null) {
            a(str2);
        }
    }

    private void a(String str) {
        this.a.a(str);
    }

    public synchronized boolean a() {
        boolean a;
        try {
            a = this.a.a();
        } catch (Throwable e) {
            bd.b(e);
            a = false;
        }
        return a;
    }

    public synchronized void close() {
        try {
            this.a.close();
        } catch (Throwable e) {
            bd.b(e);
        }
    }

    protected int b() {
        return this.a.b();
    }

    protected Cursor a(String str, int i, int i2) {
        return this.a.a(null, null, null, null, null, str + " desc", i2 + ", " + i);
    }

    protected Cursor a(String str, String str2, String str3, int i) {
        String str4 = str + "=? ";
        String[] strArr = new String[]{str2};
        return this.a.a(null, str4, strArr, null, null, str3 + " desc", i + "");
    }

    protected long a(ContentValues contentValues) {
        return this.a.a(null, contentValues);
    }

    protected boolean a(long j) {
        String[] strArr = new String[]{j + ""};
        if (this.a.a("_id=? ", strArr) > 0) {
            return true;
        }
        return false;
    }
}

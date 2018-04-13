package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.List;

public class u {
    private static volatile u a = null;
    private Context b;
    private List<m> c = new ArrayList();

    private u(Context context) {
        this.b = context.getApplicationContext();
        if (this.b == null) {
            this.b = context;
        }
    }

    public static u a(Context context) {
        if (a == null) {
            synchronized (u.class) {
                if (a == null) {
                    a = new u(context);
                }
            }
        }
        return a;
    }

    public synchronized String a(aj ajVar) {
        return this.b.getSharedPreferences("mipush_extra", 0).getString(ajVar.name(), "");
    }

    public synchronized void a(aj ajVar, String str) {
        SharedPreferences sharedPreferences = this.b.getSharedPreferences("mipush_extra", 0);
        sharedPreferences.edit().putString(ajVar.name(), str).commit();
    }

    public void a(String str) {
        synchronized (this.c) {
            m mVar = new m();
            mVar.a = 0;
            mVar.b = str;
            if (this.c.contains(mVar)) {
                this.c.remove(mVar);
            }
            this.c.add(mVar);
        }
    }

    public void b(String str) {
        synchronized (this.c) {
            m mVar = new m();
            mVar.b = str;
            if (this.c.contains(mVar)) {
                for (m mVar2 : this.c) {
                    if (mVar.equals(mVar2)) {
                        break;
                    }
                }
            }
            m mVar22 = mVar;
            mVar22.a++;
            this.c.remove(mVar22);
            this.c.add(mVar22);
        }
    }

    public int c(String str) {
        int i;
        synchronized (this.c) {
            m mVar = new m();
            mVar.b = str;
            if (this.c.contains(mVar)) {
                for (m mVar2 : this.c) {
                    if (mVar2.equals(mVar)) {
                        i = mVar2.a;
                        break;
                    }
                }
            }
            i = 0;
        }
        return i;
    }

    public void d(String str) {
        synchronized (this.c) {
            m mVar = new m();
            mVar.b = str;
            if (this.c.contains(mVar)) {
                this.c.remove(mVar);
            }
        }
    }

    public boolean e(String str) {
        boolean z;
        synchronized (this.c) {
            m mVar = new m();
            mVar.b = str;
            if (this.c.contains(mVar)) {
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }
}

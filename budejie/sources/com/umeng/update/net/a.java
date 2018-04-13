package com.umeng.update.net;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Messenger;

public class a {
    private static final String b = a.class.getName();
    final Messenger a = new Messenger(new a$b(this));
    private Context c;
    private d d;
    private Messenger e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String[] k;
    private boolean l = false;
    private boolean m = false;
    private boolean n = false;
    private ServiceConnection o = new a$1(this);

    static class a {
        public String a;
        public String b;
        public String c;
        public String d;
        public String e;
        public String[] f = null;
        public boolean g = false;
        public boolean h = false;
        public boolean i = false;

        public a(String str, String str2, String str3) {
            this.a = str;
            this.b = str2;
            this.c = str3;
        }

        public Bundle a() {
            Bundle bundle = new Bundle();
            bundle.putString("mComponentName", this.a);
            bundle.putString("mTitle", this.b);
            bundle.putString("mUrl", this.c);
            bundle.putString("mMd5", this.d);
            bundle.putString("mTargetMd5", this.e);
            bundle.putStringArray("reporturls", this.f);
            bundle.putBoolean("rich_notification", this.g);
            bundle.putBoolean("mSilent", this.h);
            bundle.putBoolean("mWifiOnly", this.i);
            return bundle;
        }

        public static a a(Bundle bundle) {
            a aVar = new a(bundle.getString("mComponentName"), bundle.getString("mTitle"), bundle.getString("mUrl"));
            aVar.d = bundle.getString("mMd5");
            aVar.e = bundle.getString("mTargetMd5");
            aVar.f = bundle.getStringArray("reporturls");
            aVar.g = bundle.getBoolean("rich_notification");
            aVar.h = bundle.getBoolean("mSilent");
            aVar.i = bundle.getBoolean("mWifiOnly");
            return aVar;
        }
    }

    public void a(String str) {
        this.i = str;
    }

    public void b(String str) {
        this.j = str;
    }

    public void a(String[] strArr) {
        this.k = strArr;
    }

    public void a(boolean z) {
        this.l = z;
    }

    public void b(boolean z) {
        this.m = z;
    }

    public void c(boolean z) {
        this.n = z;
    }

    public a(Context context, String str, String str2, String str3, d dVar) {
        this.c = context.getApplicationContext();
        this.f = str;
        this.g = str2;
        this.h = str3;
        this.d = dVar;
    }

    public void a() {
        this.c.bindService(new Intent(this.c, DownloadingService.class), this.o, 1);
        this.c.startService(new Intent(this.c, DownloadingService.class));
    }
}

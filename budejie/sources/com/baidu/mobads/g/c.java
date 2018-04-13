package com.baidu.mobads.g;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.baidu.mobads.g.o.a;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class c extends Thread {
    private static volatile c f;
    a a = new d(this);
    private volatile String b;
    private String c = null;
    private double d;
    private Handler e;
    private final Context g;
    private o h = null;
    private final e i;
    private IXAdLogger j = XAdSDKFoundationFacade.getInstance().getAdLogger();

    public static c a(Context context, e eVar, String str, Handler handler) {
        if (f == null) {
            f = new c(context, eVar, str, handler);
        }
        return f;
    }

    private c(Context context, e eVar, String str, Handler handler) {
        this.g = context;
        this.i = eVar;
        a(eVar.c());
        this.e = handler;
        this.c = str;
    }

    public void a(String str) {
        this.b = str;
        interrupt();
    }

    public void run() {
        try {
            if (b()) {
                a();
                this.j.d("XAdApkDownloadThread", "download apk successfully, downloader exit");
                f = null;
                this.j.d("XAdApkDownloadThread", "no newer apk, downloader exit");
                f = null;
            }
        } catch (IOException e) {
            this.j.e(new Object[]{"XAdApkDownloadThread", "create File or HTTP Get failed, exception: " + e.getMessage()});
        } catch (Throwable th) {
        }
    }

    private void a(String str, e eVar, String str2) {
        if (str.equals("OK") || str.equals("ERROR")) {
            Message obtainMessage = this.e.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putParcelable("APK_INFO", eVar);
            bundle.putString("CODE", str);
            obtainMessage.setData(bundle);
            this.e.sendMessage(obtainMessage);
        }
    }

    private String a() {
        String str = "__xadsdk__remote__final__" + UUID.randomUUID().toString() + ".jar";
        String str2 = this.c + str;
        File file = new File(str2);
        try {
            file.createNewFile();
            this.h.a(this.c, str);
            return str2;
        } catch (IOException e) {
            file.delete();
            throw e;
        }
    }

    private boolean b() {
        double d;
        try {
            this.h = new o(this.g, new URL(this.b), this.i, this.a);
        } catch (MalformedURLException e) {
            try {
                this.h = new o(this.g, this.b, this.i, this.a);
            } catch (Exception e2) {
                String str = "parse apk failed, error:" + e2.toString();
                this.j.e(new Object[]{"XAdApkDownloadThread", str});
                throw new g$a(str);
            }
        }
        if (g.c != null) {
            d = g.c.a;
        } else if (g.b == null) {
            d = 0.0d;
        } else if (g.b.a > 0.0d) {
            d = g.b.a;
        } else {
            d = g.b.a;
        }
        this.j.d("XAdApkDownloadThread", "isNewApkAvailable: local apk version is: " + d + ", remote apk version: " + this.i.b());
        if (d > 0.0d) {
            if (this.i.b() > 0.0d) {
                this.j.e(new Object[]{"XAdApkDownloadThread", "remote not null, local apk version is null, force upgrade"});
                this.d = this.i.b();
                return true;
            }
            this.j.e(new Object[]{"XAdApkDownloadThread", "remote is null, local apk version is null, do not upgrade"});
            return false;
        } else if (this.i.b() <= 0.0d) {
            this.j.e(new Object[]{"XAdApkDownloadThread", "remote apk version is: null, local apk version is: " + d + ", do not upgrade"});
            return false;
        } else if (this.i.b() <= d) {
            return false;
        } else {
            this.d = this.i.b();
            return true;
        }
    }
}

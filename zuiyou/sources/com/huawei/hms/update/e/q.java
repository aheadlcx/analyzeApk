package com.huawei.hms.update.e;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.view.KeyEvent;
import com.huawei.hms.activity.BridgeActivity;
import com.huawei.hms.activity.a;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.c.e;
import com.huawei.hms.c.g;
import com.huawei.hms.update.a.a.b;
import com.huawei.hms.update.a.a.c;
import com.huawei.hms.update.a.a.d;
import com.huawei.hms.update.a.f;
import com.huawei.hms.update.a.i;
import com.huawei.hms.update.provider.UpdateProvider;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.io.File;
import java.lang.ref.WeakReference;

public class q extends a implements a, b {
    private WeakReference<Activity> a;
    private com.huawei.hms.update.a.a.a b;
    private b c;
    private c d;
    private int e = -1;

    public void a(Activity activity, boolean z) {
        this.a = new WeakReference(activity);
        if (z) {
            a(i.class);
        } else {
            e();
        }
    }

    public void a() {
        g();
        j();
        com.huawei.hms.update.c.a.a(null);
        this.a = null;
    }

    public int d() {
        if (this.e == 1) {
            return 2001;
        }
        if (this.e == 2) {
            return 2002;
        }
        if (this.e == 3) {
            return PushConstants.NOTIFICATIONSERVICE_SEND_MESSAGE;
        }
        return 2001;
    }

    public boolean a(int i, int i2, Intent intent) {
        Activity c = c();
        if (c == null || c.isFinishing()) {
            return false;
        }
        if (this.e == 1 && i == 2001) {
            if (a(c)) {
                a(0);
                return true;
            }
            a(8);
            return true;
        } else if (this.e == 2 && i == 2002) {
            if (a(c)) {
                a(0);
                return true;
            }
            a(8, this.e);
            b(c);
            return true;
        } else if (this.e != 3 || i != PushConstants.NOTIFICATIONSERVICE_SEND_MESSAGE) {
            return false;
        } else {
            if (a(c)) {
                a(0);
                return true;
            }
            a(8);
            return true;
        }
    }

    private boolean a(Activity activity) {
        return new e(activity).b(HuaweiApiAvailability.SERVICES_PACKAGE) >= 20502300;
    }

    public void b() {
        if (this.c != null) {
            Class cls = this.c.getClass();
            this.c.c();
            this.c = null;
            a(cls);
        }
    }

    public void a(int i, c cVar) {
        if (com.huawei.hms.support.log.a.a()) {
            com.huawei.hms.support.log.a.a("UpdateWizard", "Enter onCheckUpdate, status: " + d.a(i));
        }
        switch (i) {
            case 1000:
                this.d = cVar;
                a(h.class);
                i();
                return;
            case 1201:
            case 1202:
            case 1203:
                a(m.b.class);
                return;
            default:
                return;
        }
    }

    public void a(int i, int i2, int i3, File file) {
        if (com.huawei.hms.support.log.a.a()) {
            com.huawei.hms.support.log.a.a("UpdateWizard", "Enter onDownloadPackage, status: " + d.a(i) + ", reveived: " + i2 + ", total: " + i3);
        }
        switch (i) {
            case 2000:
                g();
                if (file == null) {
                    a(8);
                    return;
                } else {
                    a(file);
                    return;
                }
            case PushConstants.BROADCAST_MESSAGE_ARRIVE /*2100*/:
                if (this.c != null && (this.c instanceof h)) {
                    ((h) this.c).a(i2, i3);
                    return;
                }
                return;
            case PushConstants.ONTIME_NOTIFICATION /*2201*/:
                if (!(this.d == null || this.b == null)) {
                    this.d.c(this.b.a());
                }
                a(m.c.class);
                return;
            case PushConstants.DELAY_NOTIFICATION /*2202*/:
                a(e.b.class);
                return;
            case 2203:
            case 2204:
                a(m.d.class);
                return;
            default:
                return;
        }
    }

    private void a(File file) {
        Context c = c();
        if (c != null && !c.isFinishing()) {
            if (new e(c).a(file.toString(), HuaweiApiAvailability.SERVICES_PACKAGE, HuaweiApiAvailability.SERVICES_SIGNATURE)) {
                Uri a = a(c, file);
                if (a == null) {
                    com.huawei.hms.support.log.a.d("UpdateWizard", "In startInstaller, Failed to creates a Uri from a file.");
                    a(8);
                    return;
                }
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setDataAndType(a, "application/vnd.android.package-archive");
                intent.setFlags(3);
                try {
                    c.startActivityForResult(intent, d());
                    return;
                } catch (ActivityNotFoundException e) {
                    com.huawei.hms.support.log.a.d("UpdateWizard", "In startInstaller, Failed to start package installer." + e.getMessage());
                    a(8);
                    return;
                }
            }
            com.huawei.hms.support.log.a.d("UpdateWizard", "In startInstaller, Failed to verify package archive.");
            a(8);
        }
    }

    private static Uri a(Context context, File file) {
        Object obj = 1;
        e eVar = new e(context);
        String packageName = context.getPackageName();
        String str = packageName + UpdateProvider.AUTHORITIES_SUFFIX;
        if (VERSION.SDK_INT <= 23 || (context.getApplicationInfo().targetSdkVersion <= 23 && !eVar.a(packageName, str))) {
            obj = null;
        }
        if (obj != null) {
            return UpdateProvider.getUriForFile(context, str, file);
        }
        return Uri.fromFile(file);
    }

    Activity c() {
        if (this.a == null) {
            return null;
        }
        return (Activity) this.a.get();
    }

    void a(b bVar) {
        com.huawei.hms.support.log.a.b("UpdateWizard", "Enter onCancel.");
        if (bVar instanceof i) {
            if (g.a()) {
                this.e = 1;
            } else {
                this.e = 2;
            }
            a(13);
        } else if (bVar instanceof d) {
            j();
            a(13);
        } else if (bVar instanceof h) {
            j();
            a(e.c.class);
        } else if (bVar instanceof e.c) {
            a(h.class);
            i();
        } else if (bVar instanceof e.b) {
            a(13);
        }
    }

    void b(b bVar) {
        com.huawei.hms.support.log.a.b("UpdateWizard", "Enter onDoWork.");
        if (bVar instanceof i) {
            bVar.c();
            e();
        } else if (bVar instanceof e.c) {
            bVar.c();
            a(13);
        } else if (bVar instanceof e.b) {
            a(h.class);
            i();
        } else if (bVar instanceof m.b) {
            a(8);
        } else if (bVar instanceof m.c) {
            a(8);
        } else if (bVar instanceof m.d) {
            a(8);
        }
    }

    private void e() {
        if (g.a()) {
            this.e = 1;
            a(d.class);
            h();
            return;
        }
        f();
    }

    private void f() {
        this.e = 2;
        Activity c = c();
        if (c != null && !c.isFinishing()) {
            String str = HuaweiApiAvailability.SERVICES_PACKAGE;
            try {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.huawei.hwid"));
                intent.setPackage("com.android.vending");
                c.startActivityForResult(intent, d());
            } catch (ActivityNotFoundException e) {
                com.huawei.hms.support.log.a.d("UpdateWizard", "can not open google play");
            }
        }
    }

    private void b(Activity activity) {
        this.e = 3;
        String str = HuaweiApiAvailability.SERVICES_PACKAGE;
        try {
            activity.startActivityForResult(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.huawei.hwid")), d());
        } catch (ActivityNotFoundException e) {
            com.huawei.hms.support.log.a.d("UpdateWizard", "can not find web to hold update hms apk");
        }
    }

    private void a(Class<? extends b> cls) {
        Exception e;
        g();
        try {
            b bVar = (b) cls.newInstance();
            bVar.a((a) this);
            this.c = bVar;
            return;
        } catch (InstantiationException e2) {
            e = e2;
        } catch (IllegalAccessException e3) {
            e = e3;
        } catch (IllegalStateException e4) {
            e = e4;
        }
        com.huawei.hms.support.log.a.d("UpdateWizard", "In showDialog, Failed to show the dialog." + e.getMessage());
    }

    private void g() {
        if (this.c != null) {
            try {
                this.c.c();
                this.c = null;
            } catch (IllegalStateException e) {
                com.huawei.hms.support.log.a.d("UpdateWizard", "In dismissDialog, Failed to dismiss the dialog." + e.getMessage());
            }
        }
    }

    private void h() {
        this.d = null;
        Context c = c();
        if (c != null && !c.isFinishing()) {
            j();
            this.b = new i(new com.huawei.hms.update.a.e(c));
            this.b.a(this);
        }
    }

    private void i() {
        Context c = c();
        if (c != null && !c.isFinishing()) {
            j();
            this.b = new i(new f(c));
            this.b.a(this, this.d);
        }
    }

    private void j() {
        if (this.b != null) {
            this.b.b();
            this.b = null;
        }
    }

    private void a(int i) {
        Activity c = c();
        if (c != null && !c.isFinishing()) {
            a(i, this.e);
            Intent intent = new Intent();
            intent.putExtra(BridgeActivity.EXTRA_DELEGATE_CLASS_NAME, getClass().getName());
            intent.putExtra(BridgeActivity.EXTRA_RESULT, i);
            c.setResult(-1, intent);
            c.finish();
        }
    }

    public void a(int i, KeyEvent keyEvent) {
        if (4 == i) {
            com.huawei.hms.support.log.a.b("UpdateWizard", "In onKeyUp, Call finish.");
            Activity c = c();
            if (c != null && !c.isFinishing()) {
                c.setResult(0, null);
                c.finish();
            }
        }
    }
}

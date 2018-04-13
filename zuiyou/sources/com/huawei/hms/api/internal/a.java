package com.huawei.hms.api.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.view.KeyEvent;
import com.huawei.a.a.a.a.c;
import com.huawei.hms.activity.BridgeActivity;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.b.f;
import com.huawei.hms.c.g;
import com.meizu.cloud.pushsdk.constants.PushConstants;

public class a implements ServiceConnection, com.huawei.hms.activity.a {
    private Activity a;
    private boolean b = true;
    private a c;
    private Handler d = null;

    private static class a extends f {
        private a() {
        }

        protected String a(Context context) {
            String a = g.a(context, null);
            String a2 = g.a(context, HuaweiApiAvailability.SERVICES_PACKAGE);
            return context.getResources().getString(c.hms_bindfaildlg_message, new Object[]{a, a2});
        }

        protected String b(Context context) {
            return context.getResources().getString(c.hms_confirm);
        }
    }

    public void a(Activity activity, boolean z) {
        this.a = activity;
        d.a.a(this.a);
        a(activity);
    }

    private void a(Activity activity) {
        Intent intent = new Intent();
        intent.setClassName(HuaweiApiAvailability.SERVICES_PACKAGE, HuaweiApiAvailability.ACTIVITY_NAME);
        try {
            activity.startActivityForResult(intent, c());
        } catch (ActivityNotFoundException e) {
            com.huawei.hms.support.log.a.d("BindingFailedResolution", "ActivityNotFoundException：" + e.getMessage());
            e();
        }
    }

    public void a() {
        h();
        d.a.b(this.a);
        this.a = null;
    }

    public boolean a(int i, int i2, Intent intent) {
        if (i != c()) {
            return false;
        }
        e();
        return true;
    }

    private void e() {
        if (f()) {
            g();
            return;
        }
        com.huawei.hms.support.log.a.d("BindingFailedResolution", "In connect, bind core try fail");
        b(false);
    }

    public void b() {
        if (this.c != null) {
            com.huawei.hms.support.log.a.b("BindingFailedResolution", "re show prompt dialog");
            i();
        }
    }

    public int c() {
        return PushConstants.NOTIFICATIONSERVICE_SEND_MESSAGE;
    }

    public void a(int i, KeyEvent keyEvent) {
        com.huawei.hms.support.log.a.b("BindingFailedResolution", "On key up when resolve conn error");
    }

    protected Activity d() {
        return this.a;
    }

    private void b(boolean z) {
        if (this.b) {
            this.b = false;
            a(z);
        }
    }

    protected void a(boolean z) {
        if (d() != null) {
            if (z) {
                a(0);
            } else {
                i();
            }
        }
    }

    private boolean f() {
        Activity d = d();
        if (d == null) {
            return false;
        }
        Intent intent = new Intent(HuaweiApiAvailability.SERVICES_ACTION);
        intent.setPackage(HuaweiApiAvailability.SERVICES_PACKAGE);
        return d.bindService(intent, this, 1);
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        h();
        b(true);
        Context d = d();
        if (d != null) {
            g.a(d, (ServiceConnection) this);
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
    }

    private void a(int i) {
        Activity d = d();
        if (d != null && !d.isFinishing()) {
            com.huawei.hms.support.log.a.b("BindingFailedResolution", "finishBridgeActivity：" + i);
            Intent intent = new Intent();
            intent.putExtra(BridgeActivity.EXTRA_RESULT, i);
            d.setResult(-1, intent);
            d.finish();
        }
    }

    private void g() {
        if (this.d != null) {
            this.d.removeMessages(2);
        } else {
            this.d = new Handler(Looper.getMainLooper(), new b(this));
        }
        this.d.sendEmptyMessageDelayed(2, 3000);
    }

    private void h() {
        if (this.d != null) {
            this.d.removeMessages(2);
            this.d = null;
        }
    }

    private void i() {
        Activity d = d();
        if (d != null && !d.isFinishing()) {
            if (this.c == null) {
                this.c = new a();
            } else {
                this.c.b();
            }
            com.huawei.hms.support.log.a.d("BindingFailedResolution", "showPromptdlg to resolve conn error");
            this.c.a(d, new c(this));
        }
    }
}

package com.huawei.hms.update.e;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.view.KeyEvent;
import com.huawei.a.a.a.a.c;
import com.huawei.hms.activity.BridgeActivity;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.iflytek.cloud.SpeechConstant;
import java.lang.ref.WeakReference;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class o extends a implements com.huawei.hms.activity.a {
    private WeakReference<Activity> a;
    private BroadcastReceiver b;
    private Handler c = new Handler();
    private b d;
    private boolean e = false;
    private com.huawei.hms.activity.a f;
    private Handler g = new p(this);

    private class a implements Runnable {
        final /* synthetic */ o a;

        private a(o oVar) {
            this.a = oVar;
        }

        public void run() {
            this.a.b(14);
        }
    }

    public void a(Activity activity, boolean z) {
        this.a = new WeakReference(activity);
        a(activity);
    }

    private void a(Activity activity) {
        Intent intent = new Intent("com.huawei.appmarket.intent.action.ThirdUpdateAction");
        intent.setPackage("com.huawei.appmarket");
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("pkgName", HuaweiApiAvailability.SERVICES_PACKAGE);
            jSONObject.put(com.umeng.analytics.a.B, 20502300);
            jSONArray.put(jSONObject);
            intent.putExtra(SpeechConstant.PARAMS, jSONArray.toString());
            intent.putExtra("isHmsOrApkUpgrade", true);
            intent.putExtra("buttonDlgY", activity.getString(c.hms_install));
            intent.putExtra("buttonDlgN", activity.getString(c.hms_cancel));
            intent.putExtra("upgradeDlgContent", activity.getString(c.hms_update_message_new, new Object[]{"%P"}));
            try {
                activity.startActivityForResult(intent, 2000);
            } catch (ActivityNotFoundException e) {
                com.huawei.hms.support.log.a.d("SilentUpdateWizard", "ActivityNotFoundException");
            }
        } catch (JSONException e2) {
            com.huawei.hms.support.log.a.d("SilentUpdateWizard", "create hmsJsonObject fail" + e2.getMessage());
        }
    }

    public void a() {
        this.a = null;
        this.c.removeCallbacksAndMessages(null);
        e();
        if (this.d != null) {
            this.d.c();
            this.d = null;
        }
        if (!this.e || this.f == null) {
            com.huawei.hms.update.c.a.a(null);
        } else {
            this.f.a();
        }
    }

    public boolean a(int i, int i2, Intent intent) {
        if (this.e && this.f != null) {
            return this.f.a(i, i2, intent);
        }
        if (i != 2000) {
            return false;
        }
        if (i2 == 0) {
            d();
            a(20000);
            return true;
        } else if (i2 == 4) {
            c(13);
            return true;
        } else {
            a(i2, 0);
            a(true);
            return true;
        }
    }

    public void b() {
        if (this.e && this.f != null) {
            this.f.b();
        } else if (this.d != null) {
            Class cls = this.d.getClass();
            this.d.c();
            this.d = null;
            a(cls);
        }
    }

    private void a(boolean z) {
        Activity c = c();
        if (c != null) {
            if (this.f == null) {
                b(c);
            }
            if (this.f != null) {
                this.e = true;
                com.huawei.hms.update.c.a.a(this.f.getClass());
                this.f.a(c, z);
            }
        }
    }

    private void b(Activity activity) {
        Exception e;
        Intent intent = activity.getIntent();
        if (intent != null) {
            String stringExtra = intent.getStringExtra(BridgeActivity.EXTRA_DELEGATE_CLASS_EX_NAME);
            if (stringExtra != null) {
                try {
                    this.f = (com.huawei.hms.activity.a) Class.forName(stringExtra).asSubclass(com.huawei.hms.activity.a.class).newInstance();
                } catch (ClassCastException e2) {
                    e = e2;
                    com.huawei.hms.support.log.a.d("SilentUpdateWizard", "getBridgeActivityDelegate error" + e.getMessage());
                } catch (InstantiationException e3) {
                    e = e3;
                    com.huawei.hms.support.log.a.d("SilentUpdateWizard", "getBridgeActivityDelegate error" + e.getMessage());
                } catch (IllegalAccessException e4) {
                    e = e4;
                    com.huawei.hms.support.log.a.d("SilentUpdateWizard", "getBridgeActivityDelegate error" + e.getMessage());
                } catch (ClassNotFoundException e5) {
                    e = e5;
                    com.huawei.hms.support.log.a.d("SilentUpdateWizard", "getBridgeActivityDelegate error" + e.getMessage());
                }
            }
        }
    }

    private void d() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.appmarket.service.downloadservice.Receiver");
        intentFilter.addAction("com.huawei.appmarket.service.downloadservice.progress.Receiver");
        intentFilter.addAction("com.huawei.appmarket.service.installerservice.Receiver");
        this.b = new com.huawei.hms.update.d.a(this.g);
        Activity c = c();
        if (c != null) {
            c.registerReceiver(this.b, intentFilter);
        }
    }

    private void e() {
        Activity c = c();
        if (c != null && this.b != null) {
            c.unregisterReceiver(this.b);
            this.b = null;
        }
    }

    void a(b bVar) {
        com.huawei.hms.support.log.a.a("SilentUpdateWizard", "on SilentUpdate cancelled");
    }

    void b(b bVar) {
        com.huawei.hms.support.log.a.a("SilentUpdateWizard", "on SilentUpdate dowork");
    }

    Activity c() {
        if (this.a == null) {
            return null;
        }
        return (Activity) this.a.get();
    }

    private void a(int i) {
        this.c.removeCallbacksAndMessages(null);
        this.c.postDelayed(new a(), (long) i);
    }

    private void b(int i) {
        this.c.removeCallbacksAndMessages(null);
        e();
        if (this.d != null) {
            this.d.c();
            this.d = null;
        }
        a(i, 0);
        a(false);
    }

    private void a(Bundle bundle) {
        Object obj = null;
        if (bundle.containsKey("UpgradePkgName")) {
            obj = bundle.getString("UpgradePkgName");
        }
        if (obj != null && HuaweiApiAvailability.SERVICES_PACKAGE.equals(obj) && bundle.containsKey("downloadtask.status")) {
            int i = bundle.getInt("downloadtask.status");
            if (i == 3 || i == 5 || i == 6 || i == 8) {
                b(i);
            } else if (i == 4) {
                a(60000);
            } else {
                a(20000);
            }
        }
    }

    private void b(Bundle bundle) {
        Object obj = null;
        if (bundle.containsKey("UpgradePkgName")) {
            obj = bundle.getString("UpgradePkgName");
        }
        if (obj != null && HuaweiApiAvailability.SERVICES_PACKAGE.equals(obj) && bundle.containsKey("UpgradeDownloadProgress") && bundle.containsKey("UpgradeAppName")) {
            int i = bundle.getInt("UpgradeDownloadProgress");
            a(20000);
            if (i >= 99) {
                i = 99;
            }
            if (this.d == null) {
                a(l.class);
            }
            if (this.d != null) {
                ((l) this.d).a(i);
            }
        }
    }

    private void c(Bundle bundle) {
        if (bundle.containsKey("packagename") && bundle.containsKey(NotificationCompat.CATEGORY_STATUS)) {
            String string = bundle.getString("packagename");
            int i = bundle.getInt(NotificationCompat.CATEGORY_STATUS);
            if (string != null && HuaweiApiAvailability.SERVICES_PACKAGE.equals(string)) {
                if (i == 2) {
                    this.c.removeCallbacksAndMessages(null);
                    if (this.d != null) {
                        ((l) this.d).a(100);
                    }
                    c(0);
                } else if (i == -1 || i == -2) {
                    b(i);
                } else {
                    a(60000);
                }
            }
        }
    }

    private void a(Class<? extends b> cls) {
        Exception e;
        try {
            b bVar = (b) cls.newInstance();
            bVar.a((a) this);
            this.d = bVar;
            return;
        } catch (InstantiationException e2) {
            e = e2;
        } catch (IllegalAccessException e3) {
            e = e3;
        } catch (IllegalStateException e4) {
            e = e4;
        }
        com.huawei.hms.support.log.a.d("SilentUpdateWizard", "In showDialog, Failed to show the dialog." + e.getMessage());
    }

    private void c(int i) {
        Activity c = c();
        if (c != null && !c.isFinishing()) {
            a(i, 0);
            Intent intent = new Intent();
            intent.putExtra(BridgeActivity.EXTRA_DELEGATE_CLASS_NAME, getClass().getName());
            intent.putExtra(BridgeActivity.EXTRA_RESULT, i);
            c.setResult(-1, intent);
            c.finish();
        }
    }

    public void a(int i, KeyEvent keyEvent) {
        if (this.e && this.f != null) {
            this.f.a(i, keyEvent);
        }
    }
}

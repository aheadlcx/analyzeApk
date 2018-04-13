package com.loc;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Messenger;
import android.text.TextUtils;
import com.amap.api.location.APSServiceBase;
import com.loc.e.a;
import qsbk.app.core.model.CustomButton;

public class f implements APSServiceBase {
    e a = null;
    Context b = null;
    Messenger c = null;

    public f(Context context) {
        this.b = context.getApplicationContext();
        this.a = new e(this.b);
    }

    public IBinder onBind(Intent intent) {
        e eVar = this.a;
        Object stringExtra = intent.getStringExtra("a");
        if (!TextUtils.isEmpty(stringExtra)) {
            l.a(stringExtra);
        }
        eVar.a = intent.getStringExtra(CustomButton.POSITION_BOTTOM);
        k.a(eVar.a);
        String stringExtra2 = intent.getStringExtra("d");
        if (!TextUtils.isEmpty(stringExtra2)) {
            n.a(stringExtra2);
        }
        eVar = this.a;
        if ("true".equals(intent.getStringExtra("as")) && eVar.d != null) {
            eVar.d.sendEmptyMessageDelayed(9, 100);
        }
        this.c = new Messenger(this.a.d);
        return this.c.getBinder();
    }

    public void onCreate() {
        try {
            this.a.j = de.b();
            this.a.k = de.a();
            e eVar = this.a;
            eVar.i = new db();
            eVar.b = new b(eVar, "amapLocCoreThread");
            eVar.b.setPriority(5);
            eVar.b.start();
            eVar.d = new a(eVar, eVar.b.getLooper());
        } catch (Throwable th) {
            cw.a(th, "APSServiceCore", "onCreate");
        }
    }

    public void onDestroy() {
        try {
            if (this.a != null) {
                this.a.d.sendEmptyMessage(11);
            }
        } catch (Throwable th) {
            cw.a(th, "APSServiceCore", "onDestroy");
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return 0;
    }
}

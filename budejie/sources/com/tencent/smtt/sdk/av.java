package com.tencent.smtt.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.tbs.video.interfaces.IUserStateChangedListener;
import com.tencent.tbs.video.interfaces.a;

class av {
    private static av e = null;
    ax a = null;
    Context b;
    a c;
    IUserStateChangedListener d;

    private av(Context context) {
        this.b = context.getApplicationContext();
        this.a = new ax(this.b);
    }

    public static synchronized av a(Context context) {
        av avVar;
        synchronized (av.class) {
            if (e == null) {
                e = new av(context);
            }
            avVar = e;
        }
        return avVar;
    }

    public void a(int i, int i2, Intent intent) {
        if (this.c != null) {
            this.c.a(i, i2, intent);
        }
    }

    void a(Activity activity, int i) {
        this.a.a(activity, i);
    }

    public boolean a() {
        this.a.a();
        return this.a.b();
    }

    public boolean a(String str, Bundle bundle, a aVar) {
        Object obj;
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (!TextUtils.isEmpty(str)) {
            bundle.putString("videoUrl", str);
        }
        if (aVar != null) {
            this.a.a();
            if (!this.a.b()) {
                return false;
            }
            this.c = aVar;
            this.d = new aw(this);
            this.c.a(this.d);
            bundle.putInt("callMode", 3);
        } else {
            bundle.putInt("callMode", 1);
        }
        ax axVar = this.a;
        if (aVar == null) {
            obj = null;
        }
        axVar.a(bundle, obj);
        return true;
    }
}

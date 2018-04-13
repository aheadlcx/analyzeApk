package com.qq.e.ads;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import cn.xiaochuankeji.aop.permission.c;
import com.qq.e.comm.managers.GDTADManager;
import com.qq.e.comm.pi.ACTD;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.StringUtil;
import org.aspectj.a.b.b;
import org.aspectj.lang.a.a;

public class ADActivity extends Activity {
    private static final a b = null;
    private ACTD a;

    public class AjcClosure1 extends org.aspectj.a.a.a {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            ADActivity.a((ADActivity) objArr2[0], (Bundle) objArr2[1], (org.aspectj.lang.a) objArr2[2]);
            return null;
        }
    }

    static {
        a();
    }

    private static void a() {
        b bVar = new b("<Unknown>", ADActivity.class);
        b = bVar.a("method-execution", bVar.a("4", "onCreate", "com.qq.e.ads.ADActivity", "android.os.Bundle", "arg0", "", "void"), 0);
    }

    static final void a(ADActivity aDActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        Intent intent = aDActivity.getIntent();
        String string = intent.getExtras().getString(ACTD.DELEGATE_NAME_KEY);
        String string2 = intent.getExtras().getString("appid");
        if (!(StringUtil.isEmpty(string) || StringUtil.isEmpty(string2))) {
            try {
                if (GDTADManager.getInstance().initWith(aDActivity.getApplicationContext(), string2)) {
                    aDActivity.a = GDTADManager.getInstance().getPM().getPOFactory().getActivityDelegate(string, aDActivity);
                    if (aDActivity.a == null) {
                        GDTLogger.e("Init ADActivity Delegate return null,delegateName" + string);
                    }
                } else {
                    GDTLogger.e("Init GDTADManager fail in AdActivity");
                }
            } catch (Throwable th) {
                GDTLogger.e("Init ADActivity Delegate Faile,DelegateName:" + string, th);
            }
        }
        if (aDActivity.a != null) {
            aDActivity.a.onBeforeCreate(bundle);
        } else {
            aDActivity.finish();
        }
        super.onCreate(bundle);
        if (aDActivity.a != null) {
            aDActivity.a.onAfterCreate(bundle);
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        if (this.a != null) {
            this.a.onBackPressed();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.a != null) {
            this.a.onConfigurationChanged(configuration);
        }
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(b, this, this, bundle);
        c.c().a(new AjcClosure1(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.a != null) {
            this.a.onDestroy();
        }
    }

    public void onPause() {
        if (this.a != null) {
            this.a.onPause();
        }
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
        if (this.a != null) {
            this.a.onResume();
        }
    }

    protected void onStop() {
        if (this.a != null) {
            this.a.onStop();
        }
        super.onStop();
    }
}

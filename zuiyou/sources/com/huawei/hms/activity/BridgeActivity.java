package com.huawei.hms.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import cn.xiaochuankeji.aop.permission.c;
import java.lang.reflect.InvocationTargetException;
import org.aspectj.a.b.b;
import org.aspectj.lang.a.a;

public class BridgeActivity extends Activity {
    public static final String EXTRA_DELEGATE_CLASS_EX_NAME = "intent.extra.DELEGATE_CLASS_OBJECT_EX";
    public static final String EXTRA_DELEGATE_CLASS_NAME = "intent.extra.DELEGATE_CLASS_OBJECT";
    public static final String EXTRA_RESULT = "intent.extra.RESULT";
    private static final a ajc$tjp_0 = null;
    private a a;

    public class AjcClosure1 extends org.aspectj.a.a.a {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            BridgeActivity.onCreate_aroundBody0((BridgeActivity) objArr2[0], (Bundle) objArr2[1], (org.aspectj.lang.a) objArr2[2]);
            return null;
        }
    }

    static {
        ajc$preClinit();
    }

    private static void ajc$preClinit() {
        b bVar = new b("BridgeActivity.java", BridgeActivity.class);
        ajc$tjp_0 = bVar.a("method-execution", bVar.a("4", "onCreate", "com.huawei.hms.activity.BridgeActivity", "android.os.Bundle", "arg0", "", "void"), 52);
    }

    static final void onCreate_aroundBody0(BridgeActivity bridgeActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
        bridgeActivity.a();
        if (!bridgeActivity.b()) {
            bridgeActivity.setResult(1, null);
            bridgeActivity.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(ajc$tjp_0, this, this, bundle);
        c.c().a(new AjcClosure1(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.a != null) {
            this.a.a();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.a != null) {
            this.a.b();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.a != null && !this.a.a(i, i2, intent) && !isFinishing()) {
            setResult(i2, intent);
            finish();
        }
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (this.a != null) {
            this.a.a(i, keyEvent);
        }
        return super.onKeyUp(i, keyEvent);
    }

    public void finish() {
        com.huawei.hms.support.log.a.b("BridgeActivity", "Enter finish.");
        super.finish();
    }

    private void a() {
        requestWindowFeature(1);
        if (com.huawei.hms.a.a.a.a >= 9) {
            Window window = getWindow();
            window.addFlags(67108864);
            a(window, true);
        }
    }

    private boolean b() {
        Exception e;
        Intent intent = getIntent();
        if (intent == null) {
            com.huawei.hms.support.log.a.d("BridgeActivity", "In initialize, Must not pass in a null intent.");
            return false;
        }
        String stringExtra = intent.getStringExtra(EXTRA_DELEGATE_CLASS_NAME);
        if (stringExtra == null) {
            com.huawei.hms.support.log.a.d("BridgeActivity", "In initialize, Must not pass in a null or non class object.");
            return false;
        }
        try {
            this.a = (a) Class.forName(stringExtra).asSubclass(a.class).newInstance();
            this.a.a((Activity) this, true);
            return true;
        } catch (ClassCastException e2) {
            e = e2;
            com.huawei.hms.support.log.a.d("BridgeActivity", "In initialize, Failed to create 'IUpdateWizard' instance." + e.getMessage());
            return false;
        } catch (InstantiationException e3) {
            e = e3;
            com.huawei.hms.support.log.a.d("BridgeActivity", "In initialize, Failed to create 'IUpdateWizard' instance." + e.getMessage());
            return false;
        } catch (IllegalAccessException e4) {
            e = e4;
            com.huawei.hms.support.log.a.d("BridgeActivity", "In initialize, Failed to create 'IUpdateWizard' instance." + e.getMessage());
            return false;
        } catch (ClassNotFoundException e5) {
            e = e5;
            com.huawei.hms.support.log.a.d("BridgeActivity", "In initialize, Failed to create 'IUpdateWizard' instance." + e.getMessage());
            return false;
        }
    }

    private static void a(Window window, boolean z) {
        Exception e;
        try {
            window.getClass().getMethod("setHwFloating", new Class[]{Boolean.TYPE}).invoke(window, new Object[]{Boolean.valueOf(z)});
            return;
        } catch (NoSuchMethodException e2) {
            e = e2;
        } catch (IllegalAccessException e3) {
            e = e3;
        } catch (IllegalArgumentException e4) {
            e = e4;
        } catch (InvocationTargetException e5) {
            e = e5;
        }
        com.huawei.hms.support.log.a.d("BridgeActivity", "In setHwFloating, Failed to call Window.setHwFloating()." + e.getMessage());
    }
}

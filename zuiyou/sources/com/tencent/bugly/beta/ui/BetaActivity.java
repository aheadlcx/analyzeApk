package com.tencent.bugly.beta.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import cn.xiaochuankeji.aop.permission.c;
import com.tencent.bugly.beta.Beta;
import org.aspectj.a.b.b;
import org.aspectj.lang.a.a;

public class BetaActivity extends FragmentActivity {
    private static final a ajc$tjp_0 = null;
    public Runnable onDestroyRunnable = null;

    public class AjcClosure1 extends org.aspectj.a.a.a {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            BetaActivity.onCreate_aroundBody0((BetaActivity) objArr2[0], (Bundle) objArr2[1], (org.aspectj.lang.a) objArr2[2]);
            return null;
        }
    }

    static {
        ajc$preClinit();
    }

    private static void ajc$preClinit() {
        b bVar = new b("BUGLY", BetaActivity.class);
        ajc$tjp_0 = bVar.a("method-execution", bVar.a("4", "onCreate", "com.tencent.bugly.beta.ui.BetaActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 30);
    }

    static final void onCreate_aroundBody0(BetaActivity betaActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
        try {
            betaActivity.requestWindowFeature(1);
            if (Beta.dialogFullScreen) {
                betaActivity.getWindow().setFlags(1024, 1024);
            }
            View findViewById = betaActivity.getWindow().getDecorView().findViewById(16908290);
            if (findViewById != null) {
                findViewById.setOnClickListener(new com.tencent.bugly.beta.global.b(1, betaActivity, findViewById));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        int intExtra = betaActivity.getIntent().getIntExtra("frag", -1);
        b bVar = (b) g.a.get(Integer.valueOf(intExtra));
        if (bVar != null) {
            betaActivity.getSupportFragmentManager().beginTransaction().add(16908290, bVar).commit();
            g.a.remove(Integer.valueOf(intExtra));
            return;
        }
        betaActivity.finish();
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(ajc$tjp_0, this, this, bundle);
        c.c().a(new AjcClosure1(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.onDestroyRunnable != null) {
            this.onDestroyRunnable.run();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean a;
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(16908290);
        try {
            if (findFragmentById instanceof b) {
                a = ((b) findFragmentById).a(i, keyEvent);
            } else {
                a = false;
            }
        } catch (Exception e) {
            a = false;
        }
        if (a) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }
}

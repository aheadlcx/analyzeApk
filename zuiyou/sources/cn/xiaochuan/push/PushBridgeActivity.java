package cn.xiaochuan.push;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import cn.xiaochuankeji.aop.permission.c;
import org.aspectj.a.b.b;
import org.aspectj.lang.a.a;

public class PushBridgeActivity extends Activity {
    private static final a a = null;

    static {
        a();
    }

    private static void a() {
        b bVar = new b("PushBridgeActivity.java", PushBridgeActivity.class);
        a = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuan.push.PushBridgeActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 14);
    }

    static final void a(PushBridgeActivity pushBridgeActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
        String action = pushBridgeActivity.getIntent().getAction();
        e eVar = (e) pushBridgeActivity.getIntent().getParcelableExtra("p_m_extra_data");
        Log.d("MessageReceiver", "action:" + action + "   extra :" + eVar);
        if (eVar != null) {
            if ("cn.xiaochuan.push.action.clicked".equalsIgnoreCase(action)) {
                a.a().a(3, eVar.l, eVar);
            } else if ("cn.xiaochuan.push.action.delete".equalsIgnoreCase(action)) {
                a.a().a(4, eVar.l, eVar);
            }
            pushBridgeActivity.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(a, this, this, bundle);
        c.c().a(new b(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }
}

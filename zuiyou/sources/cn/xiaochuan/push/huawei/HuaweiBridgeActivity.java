package cn.xiaochuan.push.huawei;

import android.app.Activity;
import android.os.Bundle;
import cn.xiaochuankeji.aop.permission.c;
import org.aspectj.a.b.b;
import org.aspectj.lang.a.a;

public class HuaweiBridgeActivity extends Activity {
    private static final a a = null;

    static {
        a();
    }

    private static void a() {
        b bVar = new b("HuaweiBridgeActivity.java", HuaweiBridgeActivity.class);
        a = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuan.push.huawei.HuaweiBridgeActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 10);
    }

    static final void a(HuaweiBridgeActivity huaweiBridgeActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
        com.izuiyou.a.a.b.c(bundle);
        huaweiBridgeActivity.finish();
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(a, this, this, bundle);
        c.c().a(new a(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }
}

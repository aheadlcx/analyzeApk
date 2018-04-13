package cn.xiaochuan.push.oppo;

import android.app.Activity;
import android.os.Bundle;
import cn.xiaochuan.push.e;
import cn.xiaochuankeji.aop.permission.c;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.a.b.b;
import org.aspectj.lang.a.a;

public class BridgeActivity extends Activity {
    private static final a a = null;

    static {
        a();
    }

    private static void a() {
        b bVar = new b("BridgeActivity.java", BridgeActivity.class);
        a = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuan.push.oppo.BridgeActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 15);
    }

    static final void a(BridgeActivity bridgeActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
        com.izuiyou.a.a.b.b("action:" + bridgeActivity.getIntent().getAction());
        Bundle extras = bridgeActivity.getIntent().getExtras();
        if (extras != null && extras.containsKey("push_data")) {
            try {
                JSONObject parseObject = JSON.parseObject(extras.getString("push_data"));
                e eVar = new e();
                eVar.f = parseObject.getIntValue("type");
                eVar.k = parseObject;
                cn.xiaochuan.push.a.a().a(3, "op", eVar);
            } catch (Exception e) {
                com.izuiyou.a.a.b.e(e);
            }
        }
        bridgeActivity.finish();
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(a, this, this, bundle);
        c.c().a(new a(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }
}

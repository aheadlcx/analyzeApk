package cn.xiaochuankeji.tieba.wxapi;

import android.app.Activity;
import android.os.Bundle;
import cn.xiaochuankeji.tieba.background.utils.share.c;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Resp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import org.aspectj.a.b.b;
import org.aspectj.lang.a.a;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private static final a a = null;

    static {
        a();
    }

    private static void a() {
        b bVar = new b("WXEntryActivity.java", WXEntryActivity.class);
        a = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.wxapi.WXEntryActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 19);
    }

    static final void a(WXEntryActivity wXEntryActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
        c.a().a(wXEntryActivity.getIntent(), wXEntryActivity);
        wXEntryActivity.finish();
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(a, this, this, bundle);
        cn.xiaochuankeji.aop.permission.c.c().a(new a(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    public void onReq(BaseReq baseReq) {
        com.izuiyou.a.a.b.e(baseReq.openId);
    }

    public void onResp(BaseResp baseResp) {
        com.izuiyou.a.a.b.c("baseResp.errCode: " + baseResp.errCode);
        if (baseResp instanceof Resp) {
            cn.xiaochuankeji.tieba.background.a.l().a((Resp) baseResp);
        } else if (baseResp instanceof SendAuth.Resp) {
            cn.xiaochuankeji.tieba.background.a.m().a((SendAuth.Resp) baseResp);
        }
    }
}

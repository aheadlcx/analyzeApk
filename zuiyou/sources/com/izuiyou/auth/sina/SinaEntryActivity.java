package com.izuiyou.auth.sina;

import android.app.Activity;
import android.os.Bundle;
import cn.xiaochuankeji.aop.permission.c;
import com.sina.weibo.sdk.api.share.BaseResponse;
import com.sina.weibo.sdk.api.share.IWeiboHandler.Response;
import org.aspectj.a.b.b;
import org.aspectj.lang.a.a;

public class SinaEntryActivity extends Activity implements Response {
    private static final a a = null;

    static {
        a();
    }

    private static void a() {
        b bVar = new b("SinaEntryActivity.java", SinaEntryActivity.class);
        a = bVar.a("method-execution", bVar.a("4", "onCreate", "com.izuiyou.auth.sina.SinaEntryActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 14);
    }

    static final void a(SinaEntryActivity sinaEntryActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
        c.a().a(sinaEntryActivity.getIntent(), (Response) sinaEntryActivity);
        sinaEntryActivity.finish();
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(a, this, this, bundle);
        c.c().a(new b(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    public void onResponse(BaseResponse baseResponse) {
        c.a().a(getIntent(), (Response) this);
        c.a().a(baseResponse);
    }
}

package cn.xiaochuankeji.tieba.ui.my;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import cn.xiaochuankeji.aop.permission.c;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.my.diagnosis.NetworkDiagnoseActivity;
import org.aspectj.a.b.b;
import org.aspectj.lang.a.a;

public class UserFeedBackActivity extends h implements OnClickListener {
    private static final a d = null;

    static {
        j();
    }

    private static void j() {
        b bVar = new b("UserFeedBackActivity.java", UserFeedBackActivity.class);
        d = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.my.UserFeedBackActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 36);
    }

    static final void a(UserFeedBackActivity userFeedBackActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(d, this, this, bundle);
        c.c().a(new d(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected int a() {
        return R.layout.activity_feedback;
    }

    protected void i_() {
        findViewById(R.id.tvVideoError).setOnClickListener(this);
        findViewById(R.id.tvPicError).setOnClickListener(this);
        findViewById(R.id.tvOther).setOnClickListener(this);
        TextView textView = (TextView) findViewById(R.id.tvQQQunNumber);
        CharSequence f = cn.xiaochuankeji.tieba.background.utils.c.a.c().f();
        if (!TextUtils.isEmpty(f)) {
            textView.setText(f);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvPicError:
                NetworkDiagnoseActivity.a((Context) this);
                return;
            default:
                return;
        }
    }

    protected void onDestroy() {
        super.onDestroy();
    }
}

package cn.xiaochuankeji.tieba.ui.my;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.a.b;
import cn.xiaochuankeji.tieba.ui.base.a;

public class ZYGroupActivity extends a {
    public static void a(Context context) {
        context.startActivity(new Intent(context, ZYGroupActivity.class));
    }

    protected int a() {
        return R.layout.activity_zy_group;
    }

    protected void i_() {
        ButterKnife.a((Activity) this);
        a(b.b());
    }

    @OnClick
    public void back() {
        onBackPressed();
    }
}

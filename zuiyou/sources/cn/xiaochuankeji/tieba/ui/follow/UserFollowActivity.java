package cn.xiaochuankeji.tieba.ui.follow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import cn.htjyb.b.a.b.b;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView.EmptyPaddingStyle;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.background.e.d;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.base.n;
import cn.xiaochuankeji.tieba.ui.utils.e;

public class UserFollowActivity extends n implements b {
    private static String g = "key_uid";
    private static String h = "还没有关注人";
    private a i;
    private d j;
    private QueryListView k;
    private boolean l = false;

    public static void a(Context context, long j) {
        Intent intent = new Intent(context, UserFollowActivity.class);
        intent.putExtra(g, j);
        context.startActivity(intent);
    }

    protected boolean a(Bundle bundle) {
        this.l = a.g().c() == getIntent().getLongExtra(g, 0);
        return true;
    }

    protected void j() {
        this.j.registerOnQueryFinishListener(this);
        this.j.refresh();
    }

    protected QueryListView k() {
        this.k = new QueryListView(this);
        this.k.m().setPadding(0, e.a(8.0f), 0, e.a(8.0f));
        this.k.m().setClipToPadding(false);
        return this.k;
    }

    protected String v() {
        return this.l ? "我关注的人" : "TA关注的人";
    }

    protected void c() {
        super.c();
        this.k.a(h, c.a.d.a.a.a().d(R.drawable.ic_empty_follow), EmptyPaddingStyle.GoldenSection);
    }

    protected void w() {
        long longExtra = getIntent().getLongExtra(g, 0);
        if (0 != longExtra) {
            this.j = new d(longExtra);
            this.i = new a(this, this.j, a.g().c() == longExtra, true);
            this.k.a(this.j, this.i);
        }
    }

    public void a(boolean z, boolean z2, String str) {
        if (!z) {
            g.a(str);
        }
    }
}

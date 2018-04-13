package cn.xiaochuankeji.tieba.ui.follow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import cn.htjyb.b.a.b.b;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView.EmptyPaddingStyle;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.background.e.c;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.base.n;
import cn.xiaochuankeji.tieba.ui.utils.e;

public class UserBeFollowedActivity extends n implements b {
    private static String g = "key_uid";
    private a h;
    private c i;
    private QueryListView j;
    private boolean k = false;

    public static void a(Context context, long j) {
        Intent intent = new Intent(context, UserBeFollowedActivity.class);
        intent.putExtra(g, j);
        context.startActivity(intent);
    }

    protected boolean a(Bundle bundle) {
        this.k = a.g().c() == getIntent().getLongExtra(g, 0);
        return true;
    }

    protected void j() {
        this.i.registerOnQueryFinishListener(this);
        this.i.refresh();
    }

    protected QueryListView k() {
        this.j = new QueryListView(this);
        this.j.m().setPadding(0, e.a(8.0f), 0, e.a(8.0f));
        this.j.m().setClipToPadding(false);
        return this.j;
    }

    protected String v() {
        return this.k ? "粉丝" : "关注TA的人";
    }

    protected void c() {
        super.c();
        this.j.a("还没有人关注你", c.a.d.a.a.a().d(R.drawable.ic_empty_followed), EmptyPaddingStyle.GoldenSection);
    }

    protected void w() {
        long longExtra = getIntent().getLongExtra(g, 0);
        if (0 != longExtra) {
            boolean z;
            this.i = new c(longExtra);
            if (a.g().c() == longExtra) {
                z = true;
            } else {
                z = false;
            }
            this.h = new a(this, this.i, z, false);
            this.j.a(this.i, this.h);
        }
    }

    public void a(boolean z, boolean z2, String str) {
        if (!z) {
            g.a(str);
        }
    }
}

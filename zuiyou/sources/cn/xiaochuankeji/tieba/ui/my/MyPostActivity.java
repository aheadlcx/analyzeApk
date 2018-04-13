package cn.xiaochuankeji.tieba.ui.my;

import android.content.Intent;
import android.os.Bundle;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView.EmptyPaddingStyle;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.member.e;
import cn.xiaochuankeji.tieba.ui.base.n;
import cn.xiaochuankeji.tieba.ui.post.PostQueryListView;

public class MyPostActivity extends n {
    private e g;
    private PostQueryListView h;

    protected void j() {
        this.h.h();
    }

    protected QueryListView k() {
        this.h = new PostQueryListView(this);
        this.h.f();
        return this.h;
    }

    protected void c() {
        super.c();
    }

    protected String v() {
        return null;
    }

    protected void w() {
        this.f.setTitle("我的帖子");
        this.h.f();
        this.h.a(this.g);
        this.h.a("空空如也，请勤劳发帖", (int) R.drawable.empty_tip_reported_post, EmptyPaddingStyle.GoldenSection);
    }

    public void onDestroy() {
        super.onDestroy();
        this.h.c();
    }

    protected boolean a(Bundle bundle) {
        this.g = new e();
        return true;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (-1 == i2) {
        }
    }
}

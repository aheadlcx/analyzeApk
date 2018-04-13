package cn.xiaochuankeji.tieba.ui.my;

import android.os.Bundle;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView.EmptyPaddingStyle;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.member.d;
import cn.xiaochuankeji.tieba.background.member.d.b;
import cn.xiaochuankeji.tieba.ui.base.n;
import cn.xiaochuankeji.tieba.ui.post.PostQueryListView;
import cn.xiaochuankeji.tieba.ui.widget.f;
import cn.xiaochuankeji.tieba.ui.widget.f.a;

public class MyHistoryPostActivity extends n implements b {
    private d g;
    private PostQueryListView h;

    protected void j() {
        this.g.d();
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
        this.f.setTitle("浏览历史");
        this.f.setOptionImg(R.drawable.nav_delete);
        this.f.setOptionText("");
        this.h.f();
        this.h.a(this.g);
        this.h.a("空空如也~", (int) R.drawable.ic_post_empty, EmptyPaddingStyle.GoldenSection, true);
    }

    public void s() {
        f.a("提示", "确定清空历史记录吗？", this, new a(this) {
            final /* synthetic */ MyHistoryPostActivity a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
                if (z) {
                    this.a.g.c();
                }
            }
        });
    }

    protected boolean a(Bundle bundle) {
        this.g = d.a();
        this.g.a((b) this);
        return true;
    }

    public void h_() {
        this.g.notifyListUpdate();
        this.f.setOptionTxtVisibility(this.g.itemCount() > 0 ? 0 : 8);
    }

    public void b() {
        this.g.notifyListUpdate();
        finish();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.g.e();
        this.g.clear();
    }
}

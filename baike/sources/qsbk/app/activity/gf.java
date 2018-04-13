package qsbk.app.activity;

import qsbk.app.adapter.CircleCommentAdapter.OnTabSelectListener;
import qsbk.app.utils.UIHelper;

class gf implements OnTabSelectListener {
    final /* synthetic */ CircleArticleActivity a;

    gf(CircleArticleActivity circleArticleActivity) {
        this.a = circleArticleActivity;
    }

    public void onCommentTabSelect() {
        this.a.s();
        if (this.a.c.isNormalPage()) {
            this.a.m.setImgAndTextViewGone();
            if (this.a.e.size() != 0 || this.a.o == 1) {
                this.a.g.removeFooterView(this.a.m);
            } else {
                this.a.Z.set(UIHelper.getCommentEmptyImg(), "暂无评论，快来抢地主吧~");
                this.a.Z.show();
                this.a.g.addFooterView(this.a.m);
            }
            this.a.g.setLoadMoreEnable(this.a.R);
            return;
        }
        this.a.m.setImgAndTextViewGone();
        if (this.a.f.size() != 0 || this.a.p == 1) {
            this.a.g.removeFooterView(this.a.m);
        } else {
            this.a.Z.set(UIHelper.getCommentEmptyImg(), "暂无楼主评论~");
            this.a.Z.show();
            this.a.g.addFooterView(this.a.m);
        }
        this.a.g.setLoadMoreEnable(this.a.S);
    }
}

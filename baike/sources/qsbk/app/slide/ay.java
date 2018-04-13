package qsbk.app.slide;

import qsbk.app.adapter.SingleArticleAdapter.OnTabSelectListener;
import qsbk.app.utils.UIHelper;

class ay implements OnTabSelectListener {
    final /* synthetic */ SingleArticleFragment a;

    ay(SingleArticleFragment singleArticleFragment) {
        this.a = singleArticleFragment;
    }

    public void onTabChange(boolean z) {
        this.a.W = false;
        this.a.f();
        CommentsDataProvider commentsDataProvider = z ? this.a.n : this.a.p;
        if (commentsDataProvider.c == 3 && commentsDataProvider.a.size() == 0) {
            this.a.M.setImgAndTextViewGone();
            this.a.ay.set(UIHelper.getCommentEmptyImg(), z ? "暂无评论，快来抢地主吧~" : "暂无楼主评论~");
            this.a.ay.show();
        } else {
            this.a.ay.hide();
        }
        this.a.b(commentsDataProvider.c);
    }
}

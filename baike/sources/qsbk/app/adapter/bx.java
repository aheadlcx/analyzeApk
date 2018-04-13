package qsbk.app.adapter;

import android.view.View;
import com.baidu.mobstat.Config;
import qsbk.app.adapter.ManageMyContentsAdapter.ViewHolder;
import qsbk.app.model.Article;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.SupportOrNotView.OnSupportListener;

class bx implements OnSupportListener {
    final /* synthetic */ int a;
    final /* synthetic */ ViewHolder b;
    final /* synthetic */ ManageMyContentsAdapter c;

    bx(ManageMyContentsAdapter manageMyContentsAdapter, int i, ViewHolder viewHolder) {
        this.c = manageMyContentsAdapter;
        this.a = i;
        this.b = viewHolder;
    }

    public void onSupportSelect(View view, boolean z) {
        Article article = (Article) this.c.m.get(this.a);
        article.vote_up++;
        if (z) {
            article.vote_down++;
        }
        UIHelper.setSupportAndCommentTextHighlight(this.b.supportCount, this.b.commentCount, article.getDisplayLaugth(), article.comment_count, article.shareCount, true);
        this.c.a(this.a, "up", article.id, "active");
    }

    public void onUnSupportSelect(View view, boolean z) {
        Article article = (Article) this.c.m.get(this.a);
        article.vote_down--;
        if (z) {
            article.vote_up--;
            article.vote_up = Math.max(article.vote_up, 0);
        }
        article.vote_up = Math.max(0, article.vote_up);
        UIHelper.setSupportAndCommentTextHighlight(this.b.supportCount, this.b.commentCount, article.getDisplayLaugth(), article.comment_count, article.shareCount, true);
        this.c.a(this.a, Config.DEVICE_NAME, article.id, "active");
    }
}

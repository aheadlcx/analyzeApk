package qsbk.app.slide;

import android.view.View;
import com.baidu.mobstat.Config;
import qsbk.app.model.Article;
import qsbk.app.utils.QiushiArticleBus;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.SupportOrNotView.OnSupportListener;

class m implements OnSupportListener {
    final /* synthetic */ SingleArticleFragment a;

    m(SingleArticleFragment singleArticleFragment) {
        this.a = singleArticleFragment;
    }

    public void onSupportSelect(View view, boolean z) {
        Article article = this.a.l;
        article.vote_up++;
        if (z) {
            article = this.a.l;
            article.vote_down++;
            this.a.l.vote_down = Math.min(this.a.l.vote_down, 0);
            QiushiArticleBus.updateArticleVoteState(this.a.l, null, -1, 1);
        } else {
            QiushiArticleBus.updateArticleVoteState(this.a.l, null, 0, 1);
        }
        this.a.a("up", this.a.S, "active");
        UIHelper.setSupportAndCommentTextHighlight(this.a.C, this.a.D, this.a.l.getDisplayLaugth(), this.a.l.comment_count, this.a.l.shareCount, true);
        QiushiArticleBus.updateArticle(this.a.l, this.a);
    }

    public void onUnSupportSelect(View view, boolean z) {
        Article article = this.a.l;
        article.vote_down--;
        if (z) {
            article = this.a.l;
            article.vote_up--;
            this.a.l.vote_up = Math.max(this.a.l.vote_up, 0);
            QiushiArticleBus.updateArticleVoteState(this.a.l, null, 1, -1);
        } else {
            QiushiArticleBus.updateArticleVoteState(this.a.l, null, 0, -1);
        }
        this.a.a(Config.DEVICE_NAME, this.a.S, "active");
        UIHelper.setSupportAndCommentTextHighlight(this.a.C, this.a.D, this.a.l.getDisplayLaugth(), this.a.l.comment_count, this.a.l.shareCount, true);
        QiushiArticleBus.updateArticle(this.a.l, this.a);
    }
}

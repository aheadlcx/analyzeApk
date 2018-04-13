package qsbk.app.adapter;

import android.view.View;
import com.baidu.mobstat.Config;
import qsbk.app.adapter.VideoImmersionAdapter.VideoImmersionCell;
import qsbk.app.model.Article;
import qsbk.app.utils.QiushiArticleBus;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.SupportOrNotView.OnSupportListener;

class dz implements OnSupportListener {
    final /* synthetic */ VideoImmersionCell a;

    dz(VideoImmersionCell videoImmersionCell) {
        this.a = videoImmersionCell;
    }

    public void onSupportSelect(View view, boolean z) {
        Article article = (Article) this.a.a.m.get(this.a.q);
        article.vote_up++;
        if (z) {
            article.vote_down++;
            QiushiArticleBus.updateArticleVoteState(article, null, -1, 1);
        } else {
            QiushiArticleBus.updateArticleVoteState(article, null, 0, 1);
        }
        this.a.a(this.a.q, "up", article.id, "active");
        UIHelper.setSupportAndCommentTextHighlight(this.a.supportCount, this.a.commentsCount, article.getDisplayLaugth(), article.comment_count, article.shareCount, true);
    }

    public void onUnSupportSelect(View view, boolean z) {
        Article article = (Article) this.a.a.m.get(this.a.q);
        article.vote_down--;
        if (z) {
            article.vote_up--;
            article.vote_up = Math.max(article.vote_up, 0);
            QiushiArticleBus.updateArticleVoteState(article, null, 1, -1);
        } else {
            QiushiArticleBus.updateArticleVoteState(article, null, 0, -1);
        }
        article.vote_up = Math.max(0, article.vote_up);
        this.a.a(this.a.q, Config.DEVICE_NAME, article.id, "active");
        UIHelper.setSupportAndCommentText(this.a.supportCount, this.a.commentsCount, article.getDisplayLaugth(), article.comment_count, article.shareCount, true);
    }
}

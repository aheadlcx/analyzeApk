package qsbk.app.adapter;

import android.view.View;
import android.widget.TextView;
import com.baidu.mobstat.Config;
import qsbk.app.abtest.ArticleActionStater;
import qsbk.app.activity.QiushiTopicActivity;
import qsbk.app.model.Article;
import qsbk.app.ticker.TickerView;
import qsbk.app.utils.QiushiArticleBus;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.SupportOrNotView.OnSupportListener;

class u implements OnSupportListener {
    final /* synthetic */ Article a;
    final /* synthetic */ int b;
    final /* synthetic */ TickerView c;
    final /* synthetic */ TextView d;
    final /* synthetic */ ArticleAdapter e;

    u(ArticleAdapter articleAdapter, Article article, int i, TickerView tickerView, TextView textView) {
        this.e = articleAdapter;
        this.a = article;
        this.b = i;
        this.c = tickerView;
        this.d = textView;
    }

    public void onSupportSelect(View view, boolean z) {
        ArticleActionStater.getInstance().statAction(ArticleActionStater.ACTION_VOTE_UP, this.a.getStatType());
        Article article = this.a;
        article.vote_up++;
        if (z) {
            article = this.a;
            article.vote_down++;
            QiushiArticleBus.updateArticleVoteState(this.a, null, -1, 1);
        } else {
            QiushiArticleBus.updateArticleVoteState(this.a, null, 0, 1);
        }
        if (this.e.k instanceof QiushiTopicActivity) {
            QiushiArticleBus.updateArticle(this.a, "qiushitopicup");
        }
        this.e.a(this.b, "up", this.a.id, "active");
        UIHelper.setSupportAndCommentTextHighlight(this.c, this.d, this.a.getDisplayLaugth(), this.a.comment_count, this.a.shareCount, true);
    }

    public void onUnSupportSelect(View view, boolean z) {
        Article article = this.a;
        article.vote_down--;
        if (z) {
            article = this.a;
            article.vote_up--;
            this.a.vote_up = Math.max(this.a.vote_up, 0);
            QiushiArticleBus.updateArticleVoteState(this.a, null, 1, -1);
        } else {
            QiushiArticleBus.updateArticleVoteState(this.a, null, 0, -1);
        }
        if (this.e.k instanceof QiushiTopicActivity) {
            QiushiArticleBus.updateArticle(this.a, "qiushitopicdown");
        }
        this.a.vote_up = Math.max(0, this.a.vote_up);
        this.e.a(this.b, Config.DEVICE_NAME, this.a.id, "active");
        UIHelper.setSupportAndCommentText(this.c, this.d, this.a.getDisplayLaugth(), this.a.comment_count, this.a.shareCount, true);
    }
}

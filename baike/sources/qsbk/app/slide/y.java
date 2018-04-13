package qsbk.app.slide;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.EventWindowActivity;
import qsbk.app.model.Article;
import qsbk.app.utils.Util;

class y implements OnClickListener {
    final /* synthetic */ Article a;
    final /* synthetic */ SingleArticleFragment b;

    y(SingleArticleFragment singleArticleFragment, Article article) {
        this.b = singleArticleFragment;
        this.a = article;
    }

    public void onClick(View view) {
        EventWindowActivity.launch(Util.getActivityOrContext(view), this.a.qiushiTopic.eventWindow);
    }
}

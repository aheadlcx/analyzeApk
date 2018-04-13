package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.EventWindowActivity;
import qsbk.app.model.Article;
import qsbk.app.utils.Util;

class ab implements OnClickListener {
    final /* synthetic */ Article a;
    final /* synthetic */ ArticleAdapter b;

    ab(ArticleAdapter articleAdapter, Article article) {
        this.b = articleAdapter;
        this.a = article;
    }

    public void onClick(View view) {
        EventWindowActivity.launch(Util.getActivityOrContext(view), this.a.qiushiTopic.eventWindow);
    }
}

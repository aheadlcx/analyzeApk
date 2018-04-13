package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.abtest.ArticleActionStater;
import qsbk.app.model.Article;

class v implements OnClickListener {
    final /* synthetic */ Article a;
    final /* synthetic */ b b;
    final /* synthetic */ ArticleAdapter c;

    v(ArticleAdapter articleAdapter, Article article, b bVar) {
        this.c = articleAdapter;
        this.a = article;
        this.b = bVar;
    }

    public void onClick(View view) {
        ArticleActionStater.getInstance().statAction(ArticleActionStater.ACTION_COMMENT, this.a.getStatType());
        this.b.onClick(view);
    }
}

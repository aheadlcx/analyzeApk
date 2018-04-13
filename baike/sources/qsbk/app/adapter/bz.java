package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.abtest.ArticleActionStater;
import qsbk.app.model.Article;

class bz implements OnClickListener {
    final /* synthetic */ Article a;
    final /* synthetic */ b b;
    final /* synthetic */ ManageMyContentsAdapter c;

    bz(ManageMyContentsAdapter manageMyContentsAdapter, Article article, b bVar) {
        this.c = manageMyContentsAdapter;
        this.a = article;
        this.b = bVar;
    }

    public void onClick(View view) {
        ArticleActionStater.getInstance().statAction(ArticleActionStater.ACTION_COMMENT, this.a.getStatType());
        this.b.onClick(view);
    }
}

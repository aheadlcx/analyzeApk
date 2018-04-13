package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.abtest.ArticleActionStater;
import qsbk.app.adapter.VideoImmersionAdapter.VideoImmersionCell;

class eb implements OnClickListener {
    final /* synthetic */ b a;
    final /* synthetic */ VideoImmersionCell b;

    eb(VideoImmersionCell videoImmersionCell, b bVar) {
        this.b = videoImmersionCell;
        this.a = bVar;
    }

    public void onClick(View view) {
        ArticleActionStater.getInstance().statAction(ArticleActionStater.ACTION_COMMENT, this.b.article.getStatType());
        this.a.onClick(view);
    }
}

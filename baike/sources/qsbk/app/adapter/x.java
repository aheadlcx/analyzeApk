package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.ImageViewer;
import qsbk.app.adapter.ArticleAdapter.ViewHolder;
import qsbk.app.model.Article;
import qsbk.app.model.ImageInfo;
import qsbk.app.utils.UIHelper;

class x implements OnClickListener {
    final /* synthetic */ Article a;
    final /* synthetic */ ViewHolder b;
    final /* synthetic */ ArticleAdapter c;

    x(ArticleAdapter articleAdapter, Article article, ViewHolder viewHolder) {
        this.c = articleAdapter;
        this.a = article;
        this.b = viewHolder;
    }

    public void onClick(View view) {
        ImageViewer.launch(this.c.k, new ImageInfo(this.a.hotComment.smallImage.getImageUrl(), this.a.hotComment.bigImage == null ? "" : this.a.hotComment.bigImage.getImageUrl()), UIHelper.getRectOnScreen(this.b.hotCommentImage));
    }
}

package qsbk.app.adapter;

import android.graphics.Rect;
import android.view.View;
import qsbk.app.activity.NewImageViewer;
import qsbk.app.adapter.ArticleAdapter.ViewHolder;
import qsbk.app.model.Article;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.QiushiImageLayout.OnChildClickListener;

class r implements OnChildClickListener {
    final /* synthetic */ ViewHolder a;
    final /* synthetic */ Article b;
    final /* synthetic */ ArticleAdapter c;

    r(ArticleAdapter articleAdapter, ViewHolder viewHolder, Article article) {
        this.c = articleAdapter;
        this.a = viewHolder;
        this.b = article;
    }

    public void onViewClicked(View view, int i) {
        NewImageViewer.launch(view.getContext(), this.a.qiushiImageLayout.getImageLocations(), new Rect[]{UIHelper.getViewVisibleRect(this.a.qiushiImageLayout)}, this.b, i);
    }
}

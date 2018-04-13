package qsbk.app.adapter;

import android.graphics.Rect;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.NewImageViewer;
import qsbk.app.activity.VideoImmersionActivity;
import qsbk.app.adapter.ArticleAdapter.ViewHolder;
import qsbk.app.model.Article;
import qsbk.app.utils.UIHelper;

class af implements OnClickListener {
    final /* synthetic */ Article a;
    final /* synthetic */ ViewHolder b;
    final /* synthetic */ BaseVideoAdapter c;

    af(BaseVideoAdapter baseVideoAdapter, Article article, ViewHolder viewHolder) {
        this.c = baseVideoAdapter;
        this.a = article;
        this.b = viewHolder;
    }

    public void onClick(View view) {
        if (this.a.isGIFArticle()) {
            NewImageViewer.launch(this.c.k, new Rect[]{UIHelper.getRectOnScreen(view)}, new Rect[]{UIHelper.getViewVisibleRect(view)}, this.a, 0);
            return;
        }
        VideoImmersionActivity.launch(this.c.k, this.a, this.b.videoPlayer.getCurrentTime());
    }
}

package qsbk.app.adapter;

import android.graphics.Rect;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.NewImageViewer;
import qsbk.app.activity.VideoImmersionActivity;
import qsbk.app.model.Article;
import qsbk.app.utils.UIHelper;
import qsbk.app.video.VideoPlayerView;

class n implements OnClickListener {
    final /* synthetic */ Article a;
    final /* synthetic */ VideoPlayerView b;
    final /* synthetic */ ArticleAdapter c;

    n(ArticleAdapter articleAdapter, Article article, VideoPlayerView videoPlayerView) {
        this.c = articleAdapter;
        this.a = article;
        this.b = videoPlayerView;
    }

    public void onClick(View view) {
        if (this.a.isGIFArticle()) {
            NewImageViewer.launch(this.c.k, new Rect[]{UIHelper.getRectOnScreen(view)}, new Rect[]{UIHelper.getViewVisibleRect(view)}, this.a, 0);
            return;
        }
        VideoImmersionActivity.launch(this.c.k, this.a, this.b.getCurrentTime());
    }
}

package qsbk.app.adapter;

import android.graphics.Rect;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.NewImageViewer;
import qsbk.app.activity.VideoImmersionActivity;
import qsbk.app.adapter.ManageMyContentsAdapter.ViewHolder;
import qsbk.app.model.Article;
import qsbk.app.utils.UIHelper;
import qsbk.app.video.VideoPlayerView;

class cc implements OnClickListener {
    final /* synthetic */ Article a;
    final /* synthetic */ VideoPlayerView b;
    final /* synthetic */ ViewHolder c;
    final /* synthetic */ ManageMyContentsAdapter d;

    cc(ManageMyContentsAdapter manageMyContentsAdapter, Article article, VideoPlayerView videoPlayerView, ViewHolder viewHolder) {
        this.d = manageMyContentsAdapter;
        this.a = article;
        this.b = videoPlayerView;
        this.c = viewHolder;
    }

    public void onClick(View view) {
        if (!"publish".equals(this.a.state)) {
            this.c.player.startOrPause();
        } else if (this.a.isGIFArticle()) {
            NewImageViewer.launch(this.d.k, new Rect[]{UIHelper.getRectOnScreen(view)}, new Rect[]{UIHelper.getViewVisibleRect(view)}, this.a, 0);
        } else {
            VideoImmersionActivity.launch(this.d.k, this.a, this.b.getCurrentTime(), true);
        }
    }
}

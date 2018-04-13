package qsbk.app.adapter;

import android.graphics.Rect;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.NewImageViewer;
import qsbk.app.activity.VideoImmersionActivity;
import qsbk.app.adapter.ManageMyContentsAdapter.ViewHolder;
import qsbk.app.model.Article;
import qsbk.app.utils.UIHelper;

class cg implements OnClickListener {
    final /* synthetic */ Article a;
    final /* synthetic */ ViewHolder b;
    final /* synthetic */ ManageMyContentsAdapter c;

    cg(ManageMyContentsAdapter manageMyContentsAdapter, Article article, ViewHolder viewHolder) {
        this.c = manageMyContentsAdapter;
        this.a = article;
        this.b = viewHolder;
    }

    public void onClick(View view) {
        if (!"publish".equals(this.a.state)) {
            this.b.player.startOrPause();
        } else if (this.a.isGIFArticle()) {
            NewImageViewer.launch(this.c.k, new Rect[]{UIHelper.getRectOnScreen(view)}, new Rect[]{UIHelper.getViewVisibleRect(view)}, this.a, 0);
        } else {
            VideoImmersionActivity.launch(this.c.k, this.a, this.b.player.getCurrentTime(), true);
        }
    }
}

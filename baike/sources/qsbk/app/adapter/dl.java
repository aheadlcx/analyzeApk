package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.ImageViewer;
import qsbk.app.model.Comment;
import qsbk.app.model.ImageInfo;
import qsbk.app.utils.UIHelper;

class dl implements OnClickListener {
    final /* synthetic */ Comment a;
    final /* synthetic */ b b;
    final /* synthetic */ SingleArticleAdapter c;

    dl(SingleArticleAdapter singleArticleAdapter, Comment comment, b bVar) {
        this.c = singleArticleAdapter;
        this.a = comment;
        this.b = bVar;
    }

    public void onClick(View view) {
        ImageViewer.launch(this.c.k, new ImageInfo(this.a.smallImage.getImageUrl(), this.a.bigImage == null ? "" : this.a.bigImage.getImageUrl()), UIHelper.getRectOnScreen(this.b.q));
    }
}

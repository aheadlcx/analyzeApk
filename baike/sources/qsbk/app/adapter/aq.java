package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.ImageViewer;
import qsbk.app.model.CircleComment;
import qsbk.app.model.ImageInfo;
import qsbk.app.utils.UIHelper;

class aq implements OnClickListener {
    final /* synthetic */ CircleComment a;
    final /* synthetic */ b b;
    final /* synthetic */ CircleCommentAdapter c;

    aq(CircleCommentAdapter circleCommentAdapter, CircleComment circleComment, b bVar) {
        this.c = circleCommentAdapter;
        this.a = circleComment;
        this.b = bVar;
    }

    public void onClick(View view) {
        ImageViewer.launch(this.c.k, new ImageInfo(this.a.smallImage.getImageUrl(), this.a.bigImage == null ? "" : this.a.bigImage.getImageUrl()), UIHelper.getRectOnScreen(this.b.p));
    }
}

package qsbk.app.adapter;

import android.content.Intent;
import android.view.View;
import qsbk.app.QsbkApp;
import qsbk.app.activity.ActionBarLoginActivity;
import qsbk.app.activity.ImageViewer;
import qsbk.app.model.CircleComment;
import qsbk.app.model.ImageInfo;
import qsbk.app.widget.NoUnderlineClickableSpan;

class an extends NoUnderlineClickableSpan {
    final /* synthetic */ CircleComment a;
    final /* synthetic */ CircleCommentAdapter b;

    an(CircleCommentAdapter circleCommentAdapter, CircleComment circleComment) {
        this.b = circleCommentAdapter;
        this.a = circleComment;
    }

    public void onClick(View view) {
        if (QsbkApp.currentUser == null) {
            this.b.k.startActivity(new Intent(this.b.k, ActionBarLoginActivity.class));
        } else {
            ImageViewer.launch(this.b.k, new ImageInfo(this.a.reply.smallImage.getImageUrl(), this.a.reply.bigImage == null ? "" : this.a.reply.bigImage.getImageUrl()), null);
        }
    }
}

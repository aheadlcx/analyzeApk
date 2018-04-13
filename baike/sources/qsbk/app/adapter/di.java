package qsbk.app.adapter;

import android.content.Intent;
import android.view.View;
import qsbk.app.QsbkApp;
import qsbk.app.activity.ActionBarLoginActivity;
import qsbk.app.activity.ImageViewer;
import qsbk.app.model.Comment;
import qsbk.app.model.ImageInfo;
import qsbk.app.widget.NoUnderlineClickableSpan;

class di extends NoUnderlineClickableSpan {
    final /* synthetic */ Comment a;
    final /* synthetic */ SingleArticleAdapter b;

    di(SingleArticleAdapter singleArticleAdapter, Comment comment) {
        this.b = singleArticleAdapter;
        this.a = comment;
    }

    public void onClick(View view) {
        if (QsbkApp.currentUser == null) {
            this.b.k.startActivity(new Intent(this.b.k, ActionBarLoginActivity.class));
        } else {
            ImageViewer.launch(this.b.k, new ImageInfo(this.a.reply.smallImage.getImageUrl(), this.a.reply.bigImage == null ? "" : this.a.reply.bigImage.getImageUrl()), null);
        }
    }
}

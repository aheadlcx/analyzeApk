package qsbk.app.adapter;

import android.view.View;
import qsbk.app.activity.SingleArticle;
import qsbk.app.model.PostedArticle;
import qsbk.app.widget.NoUnderlineClickableSpan;

class ch extends NoUnderlineClickableSpan {
    final /* synthetic */ PostedArticle a;
    final /* synthetic */ ManageMyContentsAdapter b;

    ch(ManageMyContentsAdapter manageMyContentsAdapter, PostedArticle postedArticle) {
        this.b = manageMyContentsAdapter;
        this.a = postedArticle;
    }

    public void onClick(View view) {
        SingleArticle.launch(this.b.k, this.a.ban.repeatId);
    }
}

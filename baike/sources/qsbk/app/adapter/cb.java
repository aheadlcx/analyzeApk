package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.Article;

class cb implements OnClickListener {
    final /* synthetic */ Article a;
    final /* synthetic */ ManageMyContentsAdapter b;

    cb(ManageMyContentsAdapter manageMyContentsAdapter, Article article) {
        this.b = manageMyContentsAdapter;
        this.a = article;
    }

    public void onClick(View view) {
        if (this.b.f != null) {
            this.b.f.delete(this.a);
        }
    }
}

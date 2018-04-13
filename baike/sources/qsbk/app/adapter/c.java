package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.EditorMsg;

class c implements OnClickListener {
    final /* synthetic */ EditorMsg a;
    final /* synthetic */ ArticleAdapter b;

    c(ArticleAdapter articleAdapter, EditorMsg editorMsg) {
        this.b = articleAdapter;
        this.a = editorMsg;
    }

    public void onClick(View view) {
        this.a.vote(true, new d(this));
    }
}

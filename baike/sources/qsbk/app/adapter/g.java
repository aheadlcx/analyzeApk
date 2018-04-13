package qsbk.app.adapter;

import android.view.View;
import qsbk.app.activity.GroupInfoActivity;
import qsbk.app.model.EditorMsg;
import qsbk.app.widget.NoUnderlineClickableSpan;

class g extends NoUnderlineClickableSpan {
    final /* synthetic */ EditorMsg a;
    final /* synthetic */ ArticleAdapter b;

    g(ArticleAdapter articleAdapter, EditorMsg editorMsg) {
        this.b = articleAdapter;
        this.a = editorMsg;
    }

    public void onClick(View view) {
        try {
            GroupInfoActivity.launch(this.b.k, Integer.parseInt(this.a.tribeId), null, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

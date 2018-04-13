package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;

class h implements OnClickListener {
    final /* synthetic */ ArticleAdapter a;

    h(ArticleAdapter articleAdapter) {
        this.a = articleAdapter;
    }

    public void onClick(View view) {
        if (this.a.b != null) {
            this.a.b.onChangeDate();
        }
    }
}

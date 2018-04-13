package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;

class m implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ ArticleAdapter b;

    m(ArticleAdapter articleAdapter, int i) {
        this.b = articleAdapter;
        this.a = i;
    }

    public void onClick(View view) {
        this.b.l.performItemClick(this.b.l, this.a + this.b.l.getHeaderViewsCount(), (long) (this.a + this.b.l.getHeaderViewsCount()));
    }
}

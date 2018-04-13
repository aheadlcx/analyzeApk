package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;

class dg implements OnClickListener {
    final /* synthetic */ SingleArticleAdapter a;

    dg(SingleArticleAdapter singleArticleAdapter) {
        this.a = singleArticleAdapter;
    }

    public void onClick(View view) {
        if (this.a.c == this.a.b) {
            this.a.c = this.a.a;
            if (this.a.f != null) {
                this.a.f.onTabChange(true);
            }
            this.a.notifyDataSetChanged();
        }
    }
}

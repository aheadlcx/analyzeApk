package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;

class dh implements OnClickListener {
    final /* synthetic */ SingleArticleAdapter a;

    dh(SingleArticleAdapter singleArticleAdapter) {
        this.a = singleArticleAdapter;
    }

    public void onClick(View view) {
        if (this.a.c == this.a.a) {
            this.a.c = this.a.b;
            if (this.a.f != null) {
                this.a.f.onTabChange(false);
            }
            this.a.notifyDataSetChanged();
        }
    }
}

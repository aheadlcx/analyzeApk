package qsbk.app.slide;

import android.view.View;
import android.view.View.OnLongClickListener;

class l implements OnLongClickListener {
    final /* synthetic */ SingleArticleFragment a;

    l(SingleArticleFragment singleArticleFragment) {
        this.a = singleArticleFragment;
    }

    public boolean onLongClick(View view) {
        this.a.m();
        return true;
    }
}

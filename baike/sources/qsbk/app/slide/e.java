package qsbk.app.slide;

import android.view.View;
import android.view.View.OnClickListener;

class e implements OnClickListener {
    final /* synthetic */ SingleArticleFragment a;

    e(SingleArticleFragment singleArticleFragment) {
        this.a = singleArticleFragment;
    }

    public void onClick(View view) {
        view.setVisibility(8);
        this.a.h();
    }
}

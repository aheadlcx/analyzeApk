package qsbk.app.slide;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.R;

class q implements OnClickListener {
    final /* synthetic */ SingleArticleFragment a;

    q(SingleArticleFragment singleArticleFragment) {
        this.a = singleArticleFragment;
    }

    public void onClick(View view) {
        this.a.Q = null;
        this.a.a.setHint(R.string.comment_input_hint);
        this.a.aq.setVisibility(8);
    }
}

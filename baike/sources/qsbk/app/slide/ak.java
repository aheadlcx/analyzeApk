package qsbk.app.slide;

import android.view.View;
import android.view.View.OnClickListener;

class ak implements OnClickListener {
    final /* synthetic */ SingleArticleFragment a;

    ak(SingleArticleFragment singleArticleFragment) {
        this.a = singleArticleFragment;
    }

    public void onClick(View view) {
        this.a.Z.cancel();
    }
}

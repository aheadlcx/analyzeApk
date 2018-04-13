package qsbk.app.slide;

import android.view.View;
import android.view.View.OnClickListener;

class bb implements OnClickListener {
    final /* synthetic */ SingleArticleFragment a;
    final /* synthetic */ a b;

    bb(a aVar, SingleArticleFragment singleArticleFragment) {
        this.b = aVar;
        this.a = singleArticleFragment;
    }

    public void onClick(View view) {
        this.b.a();
        if (this.b.b != null) {
            this.b.a.a(this.b.b, this.b.h);
        }
    }
}

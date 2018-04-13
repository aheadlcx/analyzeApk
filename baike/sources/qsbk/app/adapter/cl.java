package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.widget.BaseCell;

class cl implements OnClickListener {
    final /* synthetic */ BaseCell a;
    final /* synthetic */ NewsAdapter b;

    cl(NewsAdapter newsAdapter, BaseCell baseCell) {
        this.b = newsAdapter;
        this.a = baseCell;
    }

    public void onClick(View view) {
        this.a.onClick();
    }
}

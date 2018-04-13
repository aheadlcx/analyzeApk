package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.widget.BaseCell;

class ba implements OnClickListener {
    final /* synthetic */ BaseCell a;
    final /* synthetic */ CircleHotCommentAdapter b;

    ba(CircleHotCommentAdapter circleHotCommentAdapter, BaseCell baseCell) {
        this.b = circleHotCommentAdapter;
        this.a = baseCell;
    }

    public void onClick(View view) {
        this.a.onClick();
    }
}

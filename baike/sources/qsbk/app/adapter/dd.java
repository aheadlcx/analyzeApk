package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.widget.BaseCell;

class dd implements OnClickListener {
    final /* synthetic */ BaseCell a;
    final /* synthetic */ QiuYouCircleAdapter b;

    dd(QiuYouCircleAdapter qiuYouCircleAdapter, BaseCell baseCell) {
        this.b = qiuYouCircleAdapter;
        this.a = baseCell;
    }

    public void onClick(View view) {
        this.a.onClick();
    }
}

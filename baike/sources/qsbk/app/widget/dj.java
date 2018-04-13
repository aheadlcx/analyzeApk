package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.ImageInfo;

class dj implements OnClickListener {
    final /* synthetic */ View a;
    final /* synthetic */ ImageInfo b;
    final /* synthetic */ int c;
    final /* synthetic */ QiushiDeleteViewFactory d;

    dj(QiushiDeleteViewFactory qiushiDeleteViewFactory, View view, ImageInfo imageInfo, int i) {
        this.d = qiushiDeleteViewFactory;
        this.a = view;
        this.b = imageInfo;
        this.c = i;
    }

    public void onClick(View view) {
        if (this.d.a != null) {
            this.d.a.onViewClick(this.a, this.b, this.c);
        }
    }
}

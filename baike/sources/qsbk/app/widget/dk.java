package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;

class dk implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ QiushiDeleteViewFactory b;

    dk(QiushiDeleteViewFactory qiushiDeleteViewFactory, int i) {
        this.b = qiushiDeleteViewFactory;
        this.a = i;
    }

    public void onClick(View view) {
        if (this.b.a != null) {
            this.b.a.onDelete(view, this.a);
        }
    }
}

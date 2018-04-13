package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;

class do implements OnClickListener {
    final /* synthetic */ QBImageView a;
    final /* synthetic */ int b;
    final /* synthetic */ dn c;

    do(dn dnVar, QBImageView qBImageView, int i) {
        this.c = dnVar;
        this.a = qBImageView;
        this.b = i;
    }

    public void onClick(View view) {
        if (QiushiImageLayout.a(this.c.a) != null) {
            QiushiImageLayout.a(this.c.a).onViewClicked(this.a, this.b);
        }
    }
}

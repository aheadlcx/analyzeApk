package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class pt implements OnClickListener {
    final /* synthetic */ c a;

    pt(c cVar) {
        this.a = cVar;
    }

    public void onClick(View view) {
        this.a.a.startCamera();
    }
}

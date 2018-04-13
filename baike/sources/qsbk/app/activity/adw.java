package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.BaseUserInfo;

class adw implements OnClickListener {
    final /* synthetic */ b a;
    final /* synthetic */ a b;

    adw(a aVar, b bVar) {
        this.b = aVar;
        this.a = bVar;
    }

    public void onClick(View view) {
        this.b.a.a((BaseUserInfo) this.b.getItem(this.a.pos));
    }
}

package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.LaiseeDetailActivity;
import qsbk.app.model.Laisee;

class aa implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ n c;

    aa(n nVar, String str, String str2) {
        this.c = nVar;
        this.a = str;
        this.b = str2;
    }

    public void onClick(View view) {
        LaiseeDetailActivity.launch(this.c.a.h, new Laisee(this.a, this.b));
    }
}

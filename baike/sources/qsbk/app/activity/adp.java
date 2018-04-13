package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class adp implements OnClickListener {
    final /* synthetic */ ado a;

    adp(ado ado) {
        this.a = ado;
    }

    public void onClick(View view) {
        this.a.cancel();
    }
}

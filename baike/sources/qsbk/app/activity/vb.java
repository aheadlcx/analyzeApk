package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.utils.UIHelper;

class vb implements OnClickListener {
    final /* synthetic */ uz a;

    vb(uz uzVar) {
        this.a = uzVar;
    }

    public void onClick(View view) {
        this.a.cancel();
        UIHelper.hideKeyboard(this.a.c);
    }
}

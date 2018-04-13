package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

class mf implements OnClickListener {
    final /* synthetic */ CheckBox a;
    final /* synthetic */ me b;

    mf(me meVar, CheckBox checkBox) {
        this.b = meVar;
        this.a = checkBox;
    }

    public void onClick(View view) {
        this.a.toggle();
    }
}

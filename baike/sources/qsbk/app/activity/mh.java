package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

class mh implements OnClickListener {
    final /* synthetic */ CheckBox a;
    final /* synthetic */ me b;

    mh(me meVar, CheckBox checkBox) {
        this.b = meVar;
        this.a = checkBox;
    }

    public void onClick(View view) {
        this.b.dismiss();
        this.b.a.d(this.a.isChecked());
    }
}

package qsbk.app.pay.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

class r implements OnClickListener {
    final /* synthetic */ EditText a;
    final /* synthetic */ int b;
    final /* synthetic */ WithdrawActivity c;

    r(WithdrawActivity withdrawActivity, EditText editText, int i) {
        this.c = withdrawActivity;
        this.a = editText;
        this.b = i;
    }

    public void onClick(View view) {
        this.a.setText(String.valueOf(this.b));
    }
}

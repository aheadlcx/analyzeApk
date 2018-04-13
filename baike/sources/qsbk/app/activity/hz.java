package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import qsbk.app.utils.ToastAndDialog;

class hz implements OnClickListener {
    final /* synthetic */ EditText a;
    final /* synthetic */ hw b;

    hz(hw hwVar, EditText editText) {
        this.b = hwVar;
        this.a = editText;
    }

    public void onClick(View view) {
        String trim = this.a.getText().toString().trim();
        if (trim.length() > 140) {
            ToastAndDialog.makeNegativeToast(this.b.b, "最多只能140个字").show();
            return;
        }
        this.b.dismiss();
        this.b.b.b(trim);
    }
}

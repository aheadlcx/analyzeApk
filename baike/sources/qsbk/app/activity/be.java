package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.utils.ToastAndDialog;

class be implements OnClickListener {
    final /* synthetic */ ApplyForGroupActivity a;

    be(ApplyForGroupActivity applyForGroupActivity) {
        this.a = applyForGroupActivity;
    }

    public void onClick(View view) {
        String obj = this.a.d.getText().toString();
        if (obj.length() > 30) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "字数超出限制").show();
        } else {
            this.a.a(obj);
        }
    }
}

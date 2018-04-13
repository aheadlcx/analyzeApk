package qsbk.app.activity;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.utils.ToastAndDialog;

class zh implements OnClickListener {
    final /* synthetic */ PayPasswordModifyActivity a;

    zh(PayPasswordModifyActivity payPasswordModifyActivity) {
        this.a = payPasswordModifyActivity;
    }

    public void onClick(View view) {
        if (TextUtils.equals(this.a.h, this.a.i)) {
            this.a.g();
        } else {
            ToastAndDialog.makeNegativeToast(this.a, "两次输入的新密码不一致，请重新输入").show();
        }
    }
}

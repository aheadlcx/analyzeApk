package qsbk.app.pay.ui;

import android.app.Dialog;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.pay.R;

class ab implements OnClickListener {
    final /* synthetic */ EditText a;
    final /* synthetic */ Dialog b;
    final /* synthetic */ WithdrawActivity c;

    ab(WithdrawActivity withdrawActivity, EditText editText, Dialog dialog) {
        this.c = withdrawActivity;
        this.a = editText;
        this.b = dialog;
    }

    public void onClick(View view) {
        Object obj = this.a.getText().toString();
        if (TextUtils.isEmpty(obj)) {
            ToastUtil.Short(this.c.getString(R.string.pay_withdraw_diamond_tips_empty));
            return;
        }
        try {
            int parseInt = Integer.parseInt(obj);
            if (parseInt < 1) {
                ToastUtil.Short(this.c.getString(R.string.pay_withdraw_diamond_tips_less));
                return;
            }
            if (((double) parseInt) > this.c.y) {
                this.c.h();
                return;
            }
            this.c.a(parseInt);
            this.b.dismiss();
        } catch (NumberFormatException e) {
            ToastUtil.Short(this.c.getString(R.string.pay_withdraw_diamond_tips_illegal));
        }
    }
}

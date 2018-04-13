package qsbk.app.fragments;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.activity.PayPasswordSetActivity;
import qsbk.app.utils.ToastUtil;

class du implements OnClickListener {
    final /* synthetic */ LaiseeSendFragment a;

    du(LaiseeSendFragment laiseeSendFragment) {
        this.a = laiseeSendFragment;
    }

    public void onClick(View view) {
        if (!QsbkApp.currentUser.hasPhone()) {
            AlertDialog create = new Builder(this.a.getActivity()).setTitle("账号安全系数低，请先绑定手机，并设置支付密码").setPositiveButton("绑定手机", new dv(this)).create();
            create.setCanceledOnTouchOutside(true);
            create.show();
        } else if (QsbkApp.currentUser.hasPaypass()) {
            this.a.B = this.a.k.getText().toString();
            this.a.B = TextUtils.isEmpty(this.a.B) ? "恭喜发财，大吉大利" : this.a.B;
            this.a.d();
        } else {
            ToastUtil.Short("为了您的资金安全，请先设置支付密码");
            PayPasswordSetActivity.launch(this.a.getActivity());
        }
    }
}

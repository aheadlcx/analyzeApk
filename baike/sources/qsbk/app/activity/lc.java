package qsbk.app.activity;

import android.app.ProgressDialog;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.utils.NameLengthFilter;
import qsbk.app.utils.ToastAndDialog;

class lc implements OnClickListener {
    final /* synthetic */ FillUserDataActivity a;

    lc(FillUserDataActivity fillUserDataActivity) {
        this.a = fillUserDataActivity;
    }

    public void onClick(View view) {
        String trim = this.a.c.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "请输入您的糗百昵称").show();
            this.a.c.setCursorVisible(true);
            this.a.c.setSelected(true);
        } else if (trim.length() + NameLengthFilter.getChineseCount(trim) < 4) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "昵称最少为2个汉字").show();
            this.a.c.setCursorVisible(true);
            this.a.c.setSelected(true);
        } else if (trim.contains("@")) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "昵称中不能包含@等特殊符号").show();
            this.a.c.setCursorVisible(true);
            this.a.c.setSelected(true);
        } else {
            this.a.l = ProgressDialog.show(this.a, null, "请稍候...", true, false);
            new Thread(new ld(this, trim)).start();
        }
    }
}

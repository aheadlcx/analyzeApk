package qsbk.app.activity.publish;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import qsbk.app.QsbkApp;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.ToastAndDialog;

class ai implements OnClickListener {
    final /* synthetic */ CirclePublishActivity a;

    ai(CirclePublishActivity circlePublishActivity) {
        this.a = circlePublishActivity;
    }

    public void onClick(View view) {
        String obj = this.a.b.getText().toString();
        CharSequence trim = this.a.n.getText().toString().trim();
        CharSequence trim2 = this.a.o.getText().toString().trim();
        boolean isEmpty = TextUtils.isEmpty(trim);
        boolean isEmpty2 = TextUtils.isEmpty(trim2);
        if ((isEmpty || !isEmpty2) && (!isEmpty || isEmpty2)) {
            int length;
            if (this.a.b.getFirstText() != null) {
                a aVar = (a) this.a.b.getFirstText();
                length = aVar.a != null ? aVar.a.isClocked() ? this.a.b.getFirstText().text.length() - 1 : this.a.b.getFirstText().text.length() + 2 : 2;
            } else {
                length = 2;
            }
            if (obj.length() <= length && !this.a.I) {
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "多说点呗~", Integer.valueOf(0)).show();
                return;
            } else if (obj.length() > 3000) {
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, String.format("内容不能超过%s个字", new Object[]{Integer.valueOf(3000)}), Integer.valueOf(0)).show();
                return;
            } else if (QsbkApp.currentUser != null) {
                if (HttpUtils.netIsAvailable()) {
                    this.a.startSubmit();
                } else {
                    this.a.q();
                    ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "未发现可用网络，内容已保存为草稿", Integer.valueOf(0)).show();
                    this.a.finish();
                }
                if (this.a.getCurrentFocus() != null) {
                    ((InputMethodManager) this.a.getSystemService("input_method")).hideSoftInputFromWindow(this.a.getCurrentFocus().getWindowToken(), 2);
                    return;
                }
                return;
            } else {
                return;
            }
        }
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "请补充完投票选项", Integer.valueOf(0)).show();
    }
}

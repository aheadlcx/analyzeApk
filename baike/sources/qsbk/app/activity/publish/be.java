package qsbk.app.activity.publish;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import qsbk.app.QsbkApp;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.ToastAndDialog;

class be implements OnClickListener {
    final /* synthetic */ PublishActivity a;

    be(PublishActivity publishActivity) {
        this.a = publishActivity;
    }

    public void onClick(View view) {
        Object str = this.a.b.getQiushiContent().toString();
        if ((TextUtils.isEmpty(str) ? 0 : str.trim().length()) < 5) {
            Toast.makeText(QsbkApp.mContext, "至少输入5个字!", 1).show();
        } else if (QsbkApp.currentUser != null) {
            if (HttpUtils.netIsAvailable()) {
                this.a.A();
            } else {
                this.a.r();
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "未发现可用网络，内容已保存为草稿", Integer.valueOf(0)).show();
                this.a.finish();
            }
            if (this.a.getCurrentFocus() != null) {
                this.a.Y.hideSoftInputFromWindow(this.a.getCurrentFocus().getWindowToken(), 2);
            }
        }
    }
}

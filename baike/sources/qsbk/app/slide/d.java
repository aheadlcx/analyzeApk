package qsbk.app.slide;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.TextView;
import qsbk.app.utils.RemarkManager;

class d extends BroadcastReceiver {
    final /* synthetic */ SingleArticleFragment a;

    d(SingleArticleFragment singleArticleFragment) {
        this.a = singleArticleFragment;
    }

    public void onReceive(Context context, Intent intent) {
        if (this.a.x != null && this.a.l != null) {
            CharSequence remark = RemarkManager.getRemark(this.a.l.user_id);
            TextView textView = this.a.x;
            if (TextUtils.isEmpty(remark)) {
                remark = this.a.l.login;
            }
            textView.setText(remark);
        }
    }
}

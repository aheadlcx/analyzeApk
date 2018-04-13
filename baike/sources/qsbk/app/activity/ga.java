package qsbk.app.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.TextView;
import qsbk.app.utils.RemarkManager;

class ga extends BroadcastReceiver {
    final /* synthetic */ CircleArticleActivity a;

    ga(CircleArticleActivity circleArticleActivity) {
        this.a = circleArticleActivity;
    }

    public void onReceive(Context context, Intent intent) {
        if (this.a.l != null && this.a.l.user != null) {
            CharSequence remark = RemarkManager.getRemark(this.a.l.user.userId);
            if (this.a.z != null) {
                TextView textView = this.a.z.nameView;
                if (TextUtils.isEmpty(remark)) {
                    remark = this.a.l.user.userName;
                }
                textView.setText(remark);
            }
        }
    }
}

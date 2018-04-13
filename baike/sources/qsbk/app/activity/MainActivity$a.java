package qsbk.app.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import qsbk.app.utils.ToastAndDialog;

class MainActivity$a extends BroadcastReceiver {
    final /* synthetic */ MainActivity a;

    private MainActivity$a(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void onReceive(Context context, Intent intent) {
        String str;
        Object stringExtra = intent.getStringExtra("err_msg");
        if (TextUtils.isEmpty(stringExtra)) {
            str = "网络错误，投稿失败。可以到「管理我的糗事」重新投稿哦";
        } else {
            str = stringExtra + ",可以到「管理我的糗事」重新投稿哦";
        }
        if (this.a.b) {
            ToastAndDialog.makeNegativeToast(this.a, str, Integer.valueOf(1)).show();
        }
    }
}

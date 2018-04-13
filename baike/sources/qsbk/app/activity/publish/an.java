package qsbk.app.activity.publish;

import android.content.Intent;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.ResultActivityListener;

class an implements ResultActivityListener {
    final /* synthetic */ PublishActivity a;

    an(PublishActivity publishActivity) {
        this.a = publishActivity;
    }

    public void onResult(int i, int i2, Intent intent) {
        LogUtil.d("on edit result:");
        if (i2 == -1) {
            this.a.I = intent.getStringExtra("uuid");
        } else if (i2 == 0) {
        }
        this.a.t();
    }
}

package qsbk.app.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import qsbk.app.utils.ToastAndDialog;

class MainActivity$b extends BroadcastReceiver {
    final /* synthetic */ MainActivity a;

    private MainActivity$b(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void onReceive(Context context, Intent intent) {
        if (this.a.b) {
            ToastAndDialog.makePositiveToast(this.a, "投稿成功，人品+1，请耐心等待审核！", Integer.valueOf(1)).show();
        }
    }
}

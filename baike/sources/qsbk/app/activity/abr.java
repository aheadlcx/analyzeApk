package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.utils.ToastAndDialog;

class abr implements OnClickListener {
    final /* synthetic */ BaseUserInfo a;
    final /* synthetic */ SearchQiuYouActivity b;

    abr(SearchQiuYouActivity searchQiuYouActivity, BaseUserInfo baseUserInfo) {
        this.b = searchQiuYouActivity;
        this.a = baseUserInfo;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.b.b(this.a);
        String str = "已分享给" + this.a.userName;
        LocalBroadcastManager.getInstance(this.b).sendBroadcast(new Intent(SearchQiuYouActivity.INTENT_FILTER_SHARED_OK));
        ToastAndDialog.makePositiveToast(this.b, str, Integer.valueOf(0)).show();
        this.b.finish();
    }
}

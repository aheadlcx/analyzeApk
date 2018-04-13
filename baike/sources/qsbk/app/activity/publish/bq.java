package qsbk.app.activity.publish;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.utils.ToastAndDialog;

class bq implements OnClickListener {
    final /* synthetic */ PublishActivity a;

    bq(PublishActivity publishActivity) {
        this.a = publishActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == 0) {
            if (this.a.r()) {
                ToastAndDialog.makePositiveToast(QsbkApp.mContext, "内容已保存为草稿", Integer.valueOf(1));
            }
            this.a.finish();
        } else if (i == 1) {
            PublishActivity.clearDraft();
            this.a.finish();
        } else {
            dialogInterface.dismiss();
        }
    }
}

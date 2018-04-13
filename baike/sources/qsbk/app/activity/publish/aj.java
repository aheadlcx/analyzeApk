package qsbk.app.activity.publish;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.utils.ToastAndDialog;

class aj implements OnClickListener {
    final /* synthetic */ CirclePublishActivity a;

    aj(CirclePublishActivity circlePublishActivity) {
        this.a = circlePublishActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == 0) {
            if (this.a.q()) {
                ToastAndDialog.makePositiveToast(QsbkApp.mContext, "内容已保存为草稿", Integer.valueOf(0)).show();
            }
            this.a.finish();
        } else if (i == 1) {
            this.a.b.setText("");
            this.a.y.clear();
            this.a.I = false;
            this.a.E = null;
            this.a.P.clear();
            this.a.z = null;
            this.a.C();
            this.a.finish();
        } else {
            dialogInterface.dismiss();
        }
    }
}

package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.model.CircleComment;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.ToastAndDialog;

class gm implements OnClickListener {
    final /* synthetic */ CircleComment a;
    final /* synthetic */ OnItemLongClick b;

    gm(OnItemLongClick onItemLongClick, CircleComment circleComment) {
        this.b = onItemLongClick;
        this.a = circleComment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case 0:
                this.b.a.a(this.a);
                dialogInterface.dismiss();
                return;
            case 1:
                if (HttpUtils.netIsAvailable()) {
                    dialogInterface.dismiss();
                    this.b.a.deleteComment(this.a);
                } else {
                    ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "未发现可用网络，请稍候再试").show();
                    dialogInterface.dismiss();
                }
                dialogInterface.dismiss();
                return;
            default:
                return;
        }
    }
}

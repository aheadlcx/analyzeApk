package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.model.CircleComment;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.ToastAndDialog;

class gk implements OnClickListener {
    final /* synthetic */ CircleComment a;
    final /* synthetic */ OnItemLongClick b;

    gk(OnItemLongClick onItemLongClick, CircleComment circleComment) {
        this.b = onItemLongClick;
        this.a = circleComment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (HttpUtils.netIsAvailable()) {
            dialogInterface.dismiss();
            this.b.a.deleteComment(this.a);
            return;
        }
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "未发现可用网络，请稍候再试").show();
        dialogInterface.dismiss();
    }
}

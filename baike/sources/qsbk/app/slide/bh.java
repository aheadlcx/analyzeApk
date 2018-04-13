package qsbk.app.slide;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.model.Comment;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.ToastAndDialog;

class bh implements OnClickListener {
    final /* synthetic */ Comment a;
    final /* synthetic */ int b;
    final /* synthetic */ OnItemLongClick c;

    bh(OnItemLongClick onItemLongClick, Comment comment, int i) {
        this.c = onItemLongClick;
        this.a = comment;
        this.b = i;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case 0:
                this.c.a.a(this.a);
                dialogInterface.dismiss();
                return;
            case 1:
                if (HttpUtils.netIsAvailable()) {
                    dialogInterface.dismiss();
                    this.c.a.deleteComment(this.a, this.b);
                } else {
                    ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "未发现可用网络，请稍候再试", Integer.valueOf(0)).show();
                    dialogInterface.dismiss();
                }
                dialogInterface.dismiss();
                return;
            default:
                return;
        }
    }
}

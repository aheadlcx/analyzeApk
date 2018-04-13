package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.TextUtils;
import qsbk.app.QsbkApp;
import qsbk.app.model.CircleComment;
import qsbk.app.utils.StringUtils;
import qsbk.app.utils.ToastAndDialog;

class go implements OnClickListener {
    final /* synthetic */ CircleComment a;
    final /* synthetic */ OnItemLongClick b;

    go(OnItemLongClick onItemLongClick, CircleComment circleComment) {
        this.b = onItemLongClick;
        this.a = circleComment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case 0:
                if (!TextUtils.isEmpty(this.a.content)) {
                    StringUtils.copyToClipboard(this.a.content, this.b.a);
                    ToastAndDialog.makePositiveToast(QsbkApp.mContext, "评论内容已复制到粘贴板").show();
                    return;
                }
                return;
            case 1:
                this.b.a.a(this.a);
                dialogInterface.dismiss();
                return;
            default:
                return;
        }
    }
}

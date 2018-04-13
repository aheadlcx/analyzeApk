package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.TextUtils;
import qsbk.app.QsbkApp;
import qsbk.app.model.CircleComment;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.StringUtils;
import qsbk.app.utils.ToastAndDialog;

class gi implements OnClickListener {
    final /* synthetic */ CircleComment a;
    final /* synthetic */ OnItemLongClick b;

    gi(OnItemLongClick onItemLongClick, CircleComment circleComment) {
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
                if (HttpUtils.netIsAvailable()) {
                    dialogInterface.dismiss();
                    this.b.a.deleteComment(this.a);
                    return;
                }
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "未发现可用网络，请稍候再试", Integer.valueOf(0)).show();
                dialogInterface.dismiss();
                return;
            case 2:
                if (HttpUtils.netIsAvailable()) {
                    dialogInterface.dismiss();
                    this.b.a.forbidComment(this.a);
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

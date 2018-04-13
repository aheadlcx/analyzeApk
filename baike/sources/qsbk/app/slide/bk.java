package qsbk.app.slide;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.TextUtils;
import qsbk.app.QsbkApp;
import qsbk.app.model.Comment;
import qsbk.app.utils.StringUtils;
import qsbk.app.utils.ToastAndDialog;

class bk implements OnClickListener {
    final /* synthetic */ Comment a;
    final /* synthetic */ OnItemLongClick b;

    bk(OnItemLongClick onItemLongClick, Comment comment) {
        this.b = onItemLongClick;
        this.a = comment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case 0:
                if (!TextUtils.isEmpty(this.a.content)) {
                    StringUtils.copyToClipboard(this.a.content, this.b.a.getActivity());
                    ToastAndDialog.makePositiveToast(QsbkApp.mContext, "评论内容已复制到粘贴板").show();
                    return;
                }
                return;
            case 1:
                this.b.a.a(this.a);
                return;
            default:
                return;
        }
    }
}

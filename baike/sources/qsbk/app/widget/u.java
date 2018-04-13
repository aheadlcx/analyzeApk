package qsbk.app.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.TextUtils;
import qsbk.app.model.CircleArticle;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.StringUtils;
import qsbk.app.utils.ToastAndDialog;

class u implements OnClickListener {
    final /* synthetic */ String[] a;
    final /* synthetic */ CircleArticle b;
    final /* synthetic */ Context c;
    final /* synthetic */ ArticleMoreOperationbar d;

    u(ArticleMoreOperationbar articleMoreOperationbar, String[] strArr, CircleArticle circleArticle, Context context) {
        this.d = articleMoreOperationbar;
        this.a = strArr;
        this.b = circleArticle;
        this.c = context;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        Object obj = this.a[i];
        if ("unlike".equals(obj)) {
            ArticleMoreOperationbar.unlikeArticle(this.b);
        } else if ("report".equals(obj)) {
            ArticleMoreOperationbar.reportDialog(this.c, this.b);
        } else if ("delete".equals(obj)) {
            ArticleMoreOperationbar.deleteDialog(this.c, this.b);
        } else if ("copy".equals(obj)) {
            if (!TextUtils.isEmpty(this.b.content)) {
                StringUtils.copyToClipboard(this.b.content, this.c);
                ToastAndDialog.makePositiveToast(this.c, "对话内容已复制到粘贴板", Integer.valueOf(0)).show();
            }
        } else if ("top".equals(obj)) {
            ArticleMoreOperationbar.topDialog(this.c, this.b);
        } else if ("untop".equals(obj)) {
            ArticleMoreOperationbar.untopDialog(this.c, this.b);
        } else if ("remove".equals(obj)) {
            ArticleMoreOperationbar.removeDialog(this.c, this.b);
        } else if ("block".equals(obj)) {
            ArticleMoreOperationbar.blockDialog(this.c, this.b);
        } else if ("adminDelete".equals(obj)) {
            ArticleMoreOperationbar.deleteDialog(this.c, this.b);
        } else if ("forbid".equals(obj)) {
            ArticleMoreOperationbar.forbidDialog(this.c, this.b);
        } else if ("share".equals(obj)) {
            ShareUtils.openShareDialog(this.c, this.b);
        }
    }
}

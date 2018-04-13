package qsbk.app.widget.qiuyoucircle;

import android.view.View;
import android.view.View.OnLongClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.model.CircleArticle;
import qsbk.app.share.ShareUtils$OnCircleShareStartListener;
import qsbk.app.utils.LoginPermissionClickDelegate;

class ar implements OnLongClickListener {
    final /* synthetic */ CircleArticle a;
    final /* synthetic */ NormalCell b;

    ar(NormalCell normalCell, CircleArticle circleArticle) {
        this.b = normalCell;
        this.a = circleArticle;
    }

    public boolean onLongClick(View view) {
        if (QsbkApp.currentUser == null || this.a.auditStatus != 1) {
            LoginPermissionClickDelegate.startLoginActivity(view.getContext());
        } else if (this.b.shareListener != null) {
            this.b.shareListener.onCircleShareStart((CircleArticle) this.b.getItem(), ShareUtils$OnCircleShareStartListener.TYPE_REPORT_OR_COPY, this.b.shareCount);
        }
        return true;
    }
}

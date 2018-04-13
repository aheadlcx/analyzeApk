package qsbk.app.widget.qiuyoucircle;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.CircleArticle;
import qsbk.app.share.ShareUtils$OnCircleShareStartListener;

class aq implements OnClickListener {
    final /* synthetic */ CircleArticle a;
    final /* synthetic */ NormalCell b;

    aq(NormalCell normalCell, CircleArticle circleArticle) {
        this.b = normalCell;
        this.a = circleArticle;
    }

    public void onClick(View view) {
        if (this.b.shareListener != null && this.a.auditStatus == 1) {
            this.b.shareListener.onCircleShareStart((CircleArticle) this.b.getItem(), ShareUtils$OnCircleShareStartListener.TYPE_SHARE, this.b.shareCount);
        }
    }
}

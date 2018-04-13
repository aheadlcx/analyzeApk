package qsbk.app.widget.qiuyoucircle;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.CircleArticle;
import qsbk.app.share.ShareUtils$OnCircleShareStartListener;

class bi implements OnClickListener {
    final /* synthetic */ ShareCell a;

    bi(ShareCell shareCell) {
        this.a = shareCell;
    }

    public void onClick(View view) {
        if (this.a.shareListener != null) {
            this.a.shareListener.onCircleShareStart((CircleArticle) this.a.getItem(), ShareUtils$OnCircleShareStartListener.TYPE_SHARE, this.a.shareCount);
        }
    }
}

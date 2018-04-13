package qsbk.app.widget.qiuyoucircle;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.CircleArticle;
import qsbk.app.share.ShareUtils$OnCircleShareStartListener;

class bz implements OnClickListener {
    final /* synthetic */ WebAdCell a;

    bz(WebAdCell webAdCell) {
        this.a = webAdCell;
    }

    public void onClick(View view) {
        if (this.a.shareListener != null) {
            this.a.shareListener.onCircleShareStart((CircleArticle) this.a.getItem(), ShareUtils$OnCircleShareStartListener.TYPE_SHARE, this.a.actionView);
        }
    }
}

package qsbk.app.widget.qiuyoucircle;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.CircleArticle;
import qsbk.app.share.ShareUtils$OnCircleShareStartListener;

class am implements OnClickListener {
    final /* synthetic */ ForwardCell a;

    am(ForwardCell forwardCell) {
        this.a = forwardCell;
    }

    public void onClick(View view) {
        if (this.a.shareListener != null) {
            this.a.shareListener.onCircleShareStart((CircleArticle) this.a.getItem(), ShareUtils$OnCircleShareStartListener.TYPE_SHARE, this.a.shareCount);
        }
    }
}

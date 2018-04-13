package qsbk.app.widget.qiuyoucircle;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.VideoImmersionCircleActivity;
import qsbk.app.model.CircleArticle;
import qsbk.app.utils.Util;

class ai implements OnClickListener {
    final /* synthetic */ CircleArticle a;
    final /* synthetic */ ForwardCell b;

    ai(ForwardCell forwardCell, CircleArticle circleArticle) {
        this.b = forwardCell;
        this.a = circleArticle;
    }

    public void onClick(View view) {
        if (!this.b.fromCircleTopic || this.a == null) {
            VideoImmersionCircleActivity.launch(Util.getActivityOrContext(view), this.a, this.b.originalVideoPlayer.getCurrentTime());
        } else {
            VideoImmersionCircleActivity.launch(Util.getActivityOrContext(view), this.a, this.b.originalVideoPlayer.getCurrentTime(), this.a.id, false);
        }
    }
}

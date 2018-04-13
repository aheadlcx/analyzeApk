package qsbk.app.widget.qiuyoucircle;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.VideoImmersionCircleActivity;
import qsbk.app.model.CircleArticle;

class av implements OnClickListener {
    final /* synthetic */ CircleArticle a;
    final /* synthetic */ NormalCell b;

    av(NormalCell normalCell, CircleArticle circleArticle) {
        this.b = normalCell;
        this.a = circleArticle;
    }

    public void onClick(View view) {
        if (!this.b.fromCircleTopic || this.a == null) {
            VideoImmersionCircleActivity.launch(this.b.getContext(), (CircleArticle) this.b.getItem(), this.b.playerView.getCurrentTime());
        } else {
            VideoImmersionCircleActivity.launch(this.b.getContext(), (CircleArticle) this.b.getItem(), this.b.playerView.getCurrentTime(), this.a.id, false);
        }
    }
}

package qsbk.app.widget.qiuyoucircle;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.CircleArticleActivity;
import qsbk.app.model.CircleArticle;

class ao implements OnClickListener {
    final /* synthetic */ CircleArticle a;
    final /* synthetic */ ForwardCell b;

    ao(ForwardCell forwardCell, CircleArticle circleArticle) {
        this.b = forwardCell;
        this.a = circleArticle;
    }

    public void onClick(View view) {
        CircleArticleActivity.launch(this.b.getContext(), this.a, true, true, this.b.fromCircleTopic);
    }
}

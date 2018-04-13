package qsbk.app.widget.qiuyoucircle;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.CircleArticleActivity;
import qsbk.app.model.CircleArticle;

class ae implements OnClickListener {
    final /* synthetic */ ForwardCell a;

    ae(ForwardCell forwardCell) {
        this.a = forwardCell;
    }

    public void onClick(View view) {
        CircleArticle circleArticle = (CircleArticle) this.a.getItem();
        if (circleArticle.isForward()) {
            CircleArticleActivity.launch(this.a.originalLayout.getContext(), circleArticle.originalCircleArticle, false, false, this.a.fromCircleTopic);
        }
    }
}

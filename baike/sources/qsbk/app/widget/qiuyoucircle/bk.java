package qsbk.app.widget.qiuyoucircle;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.CircleArticleActivity;
import qsbk.app.model.CircleArticle;

class bk implements OnClickListener {
    final /* synthetic */ CircleArticle a;
    final /* synthetic */ ShareCell b;

    bk(ShareCell shareCell, CircleArticle circleArticle) {
        this.b = shareCell;
        this.a = circleArticle;
    }

    public void onClick(View view) {
        CircleArticleActivity.launch(this.b.getContext(), this.a, true, true, this.b.fromCircleTopic);
    }
}

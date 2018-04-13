package qsbk.app.widget.qiuyoucircle;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.CircleArticleActivity;
import qsbk.app.model.CircleArticle;
import qsbk.app.utils.Util;

class bf implements OnClickListener {
    final /* synthetic */ ShareCell a;

    bf(ShareCell shareCell) {
        this.a = shareCell;
    }

    public void onClick(View view) {
        if (view.getTag() != null) {
            view.setTag(null);
            return;
        }
        Context activityOrContext = Util.getActivityOrContext(view);
        if (!(activityOrContext instanceof CircleArticleActivity)) {
            CircleArticleActivity.launch(activityOrContext, (CircleArticle) this.a.getItem(), false, false, this.a.fromCircleTopic);
        }
    }
}

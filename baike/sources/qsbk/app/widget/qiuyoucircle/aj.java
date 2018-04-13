package qsbk.app.widget.qiuyoucircle;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.CircleArticleActivity;
import qsbk.app.model.CircleArticle;
import qsbk.app.utils.Util;

class aj implements OnClickListener {
    final /* synthetic */ ForwardCell a;

    aj(ForwardCell forwardCell) {
        this.a = forwardCell;
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

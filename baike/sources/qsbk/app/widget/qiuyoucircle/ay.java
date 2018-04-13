package qsbk.app.widget.qiuyoucircle;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.CircleArticleActivity;
import qsbk.app.model.CircleArticle;
import qsbk.app.utils.Util;

class ay implements OnClickListener {
    final /* synthetic */ NormalCell a;

    ay(NormalCell normalCell) {
        this.a = normalCell;
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

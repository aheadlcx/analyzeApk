package qsbk.app.widget.qiuyoucircle;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.CircleArticleActivity;
import qsbk.app.model.CircleArticle;
import qsbk.app.utils.Util;

class ba implements OnClickListener {
    final /* synthetic */ NormalCell a;

    ba(NormalCell normalCell) {
        this.a = normalCell;
    }

    public void onClick(View view) {
        if (view.getTag() != null) {
            view.setTag(null);
            return;
        }
        CircleArticleActivity.launch(Util.getActivityOrContext(view), (CircleArticle) this.a.getItem(), false, false, this.a.fromCircleTopic);
    }
}

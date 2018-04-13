package qsbk.app.widget.qiuyoucircle;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.CircleArticleActivity;
import qsbk.app.model.CircleArticle;
import qsbk.app.utils.Util;

class aa implements OnClickListener {
    final /* synthetic */ BaseUserCell a;

    aa(BaseUserCell baseUserCell) {
        this.a = baseUserCell;
    }

    public void onClick(View view) {
        Context activityOrContext = Util.getActivityOrContext(view);
        if (activityOrContext instanceof CircleArticleActivity) {
            ((CircleArticleActivity) activityOrContext).showKeyboard();
            return;
        }
        CircleArticleActivity.launch(view.getContext(), (CircleArticle) this.a.getItem(), true, false, this.a.fromCircleTopic);
    }
}

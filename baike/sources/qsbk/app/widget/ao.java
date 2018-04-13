package qsbk.app.widget;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.CircleTopicCategoriesActivity;
import qsbk.app.utils.Util;

class ao implements OnClickListener {
    final /* synthetic */ CircleCategoryFlowCell a;

    ao(CircleCategoryFlowCell circleCategoryFlowCell) {
        this.a = circleCategoryFlowCell;
    }

    public void onClick(View view) {
        Context activityOrContext = Util.getActivityOrContext(view);
        if (activityOrContext instanceof Activity) {
            CircleTopicCategoriesActivity.launch((Activity) activityOrContext);
        }
    }
}

package qsbk.app.widget;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.CircleTopicCategoriesActivity;
import qsbk.app.model.CircleTopicCategory;
import qsbk.app.utils.Util;

class ap implements OnClickListener {
    final /* synthetic */ CircleTopicCategory a;
    final /* synthetic */ CircleCategoryFlowCell b;

    ap(CircleCategoryFlowCell circleCategoryFlowCell, CircleTopicCategory circleTopicCategory) {
        this.b = circleCategoryFlowCell;
        this.a = circleTopicCategory;
    }

    public void onClick(View view) {
        Context activityOrContext = Util.getActivityOrContext(view);
        if (activityOrContext instanceof Activity) {
            CircleTopicCategoriesActivity.launch((Activity) activityOrContext, this.a);
        }
    }
}

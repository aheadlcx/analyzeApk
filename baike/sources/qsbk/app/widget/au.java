package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.activity.CircleTopicActivity;
import qsbk.app.model.CircleTopic;
import qsbk.app.utils.SubscribeReportHelper;

class au implements OnClickListener {
    final /* synthetic */ CircleTopic a;
    final /* synthetic */ String b;
    final /* synthetic */ CircleTopicMoreCell c;

    au(CircleTopicMoreCell circleTopicMoreCell, CircleTopic circleTopic, String str) {
        this.c = circleTopicMoreCell;
        this.a = circleTopic;
        this.b = str;
    }

    public void onClick(View view) {
        CircleTopicActivity.launch(this.c.getContext(), this.a, -1, true, this.b);
        SubscribeReportHelper.report(QsbkApp.mContext, this.a.hashCode());
    }
}

package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.activity.CircleTopicActivity;
import qsbk.app.model.CircleTopic;
import qsbk.app.utils.SubscribeReportHelper;

class bb implements OnClickListener {
    final /* synthetic */ CircleTopic a;
    final /* synthetic */ CircleTopicThreeImageCell b;

    bb(CircleTopicThreeImageCell circleTopicThreeImageCell, CircleTopic circleTopic) {
        this.b = circleTopicThreeImageCell;
        this.a = circleTopic;
    }

    public void onClick(View view) {
        CircleTopicActivity.launch(this.b.getContext(), this.a, -1, true);
        SubscribeReportHelper.report(QsbkApp.mContext, this.a.hashCode());
    }
}

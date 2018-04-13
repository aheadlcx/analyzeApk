package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.activity.CircleTopicActivity;
import qsbk.app.model.CircleTopic;
import qsbk.app.utils.SubscribeReportHelper;

class aw implements OnClickListener {
    final /* synthetic */ CircleTopic a;
    final /* synthetic */ CircleTopicTextBgCell b;

    aw(CircleTopicTextBgCell circleTopicTextBgCell, CircleTopic circleTopic) {
        this.b = circleTopicTextBgCell;
        this.a = circleTopic;
    }

    public void onClick(View view) {
        CircleTopicActivity.launch(this.b.getContext(), this.a, -1);
        SubscribeReportHelper.report(QsbkApp.mContext, this.a.hashCode());
    }
}

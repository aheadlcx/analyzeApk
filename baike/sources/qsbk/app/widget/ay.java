package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.activity.CircleTopicActivity;
import qsbk.app.model.CircleTopic;
import qsbk.app.utils.SubscribeReportHelper;

class ay implements OnClickListener {
    final /* synthetic */ CircleTopic a;
    final /* synthetic */ String[] b;
    final /* synthetic */ CircleTopicThreeImageCell c;

    ay(CircleTopicThreeImageCell circleTopicThreeImageCell, CircleTopic circleTopic, String[] strArr) {
        this.c = circleTopicThreeImageCell;
        this.a = circleTopic;
        this.b = strArr;
    }

    public void onClick(View view) {
        CircleTopicActivity.launch(this.c.getContext(), this.a, -1, true, this.b[1]);
        SubscribeReportHelper.report(QsbkApp.mContext, this.a.hashCode());
    }
}

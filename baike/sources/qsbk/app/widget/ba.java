package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.activity.publish.CirclePublishActivity;
import qsbk.app.model.CircleTopic;
import qsbk.app.utils.LoginPermissionClickDelegate;
import qsbk.app.utils.SubscribeReportHelper;

class ba implements OnClickListener {
    final /* synthetic */ CircleTopic a;
    final /* synthetic */ CircleTopicThreeImageCell b;

    ba(CircleTopicThreeImageCell circleTopicThreeImageCell, CircleTopic circleTopic) {
        this.b = circleTopicThreeImageCell;
        this.a = circleTopic;
    }

    public void onClick(View view) {
        if (QsbkApp.currentUser != null) {
            CirclePublishActivity.launch(view.getContext(), this.a);
            SubscribeReportHelper.report(QsbkApp.mContext, this.a.hashCode());
            return;
        }
        LoginPermissionClickDelegate.startLoginActivity(view.getContext());
    }
}

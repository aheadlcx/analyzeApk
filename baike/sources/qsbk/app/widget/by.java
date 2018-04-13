package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.activity.MyInfoActivity;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.utils.SubscribeReportHelper;
import qsbk.app.widget.GroupRecommendQiushiCell.a;

class by implements OnClickListener {
    final /* synthetic */ BaseUserInfo a;
    final /* synthetic */ a b;

    by(a aVar, BaseUserInfo baseUserInfo) {
        this.b = aVar;
        this.a = baseUserInfo;
    }

    public void onClick(View view) {
        if (this.a != null) {
            MyInfoActivity.launch(view.getContext(), this.a.userId);
            SubscribeReportHelper.report(QsbkApp.mContext, this.b.a.getItem().hashCode());
        }
    }
}

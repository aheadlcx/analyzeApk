package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.activity.GroupInfoActivity;
import qsbk.app.model.GroupRecommend.GroupItem;
import qsbk.app.utils.SubscribeReportHelper;
import qsbk.app.widget.GroupRecommendQiushiCell.a;

class bx implements OnClickListener {
    final /* synthetic */ GroupItem a;
    final /* synthetic */ a b;

    bx(a aVar, GroupItem groupItem) {
        this.b = aVar;
        this.a = groupItem;
    }

    public void onClick(View view) {
        if (this.a != null) {
            GroupInfoActivity.launch(view.getContext(), this.a.toGroupInfo());
            SubscribeReportHelper.report(QsbkApp.mContext, this.b.a.getItem().hashCode());
        }
    }
}

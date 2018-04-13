package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.activity.ApplyForGroupActivity;
import qsbk.app.model.GroupRecommend.GroupItem;
import qsbk.app.utils.SubscribeReportHelper;
import qsbk.app.utils.Util;
import qsbk.app.widget.GroupRecommendQiushiCell.a;

class bz implements OnClickListener {
    final /* synthetic */ GroupItem a;
    final /* synthetic */ a b;

    bz(a aVar, GroupItem groupItem) {
        this.b = aVar;
        this.a = groupItem;
    }

    public void onClick(View view) {
        if (this.a != null) {
            ApplyForGroupActivity.launchForResult(Util.getActivityOrContext(view), this.a.toGroupInfo(), -1);
            SubscribeReportHelper.report(QsbkApp.mContext, this.b.a.getItem().hashCode());
        }
    }
}

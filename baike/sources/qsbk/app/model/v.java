package qsbk.app.model;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.activity.WeiboLoginActivity;
import qsbk.app.model.UserLoginGuideCard.ViewHolder;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.SubscribeReportHelper;

class v implements OnClickListener {
    final /* synthetic */ ViewHolder a;

    v(ViewHolder viewHolder) {
        this.a = viewHolder;
    }

    public void onClick(View view) {
        DebugUtil.debug(UserLoginGuideCard.c, "weiboLogin ");
        UserLoginGuideCard.b("login_guide_card", "login_guide_card_weibo");
        UserLoginGuideCard.d.startActivity(new Intent(UserLoginGuideCard.d, WeiboLoginActivity.class));
        SubscribeReportHelper.report(QsbkApp.mContext, -1);
    }
}

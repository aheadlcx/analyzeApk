package qsbk.app.model;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.activity.ActionBarLoginActivity;
import qsbk.app.model.UserLoginGuideCard.ViewHolder;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.SubscribeReportHelper;

class w implements OnClickListener {
    final /* synthetic */ ViewHolder a;

    w(ViewHolder viewHolder) {
        this.a = viewHolder;
    }

    public void onClick(View view) {
        DebugUtil.debug(UserLoginGuideCard.c, "qiubaiLogin ");
        UserLoginGuideCard.b("login_guide_card", "login_guide_card_qb");
        if (QsbkApp.currentUser == null) {
            UserLoginGuideCard.d.startActivity(new Intent(UserLoginGuideCard.d, ActionBarLoginActivity.class));
            return;
        }
        SubscribeReportHelper.report(QsbkApp.mContext, -1);
    }
}

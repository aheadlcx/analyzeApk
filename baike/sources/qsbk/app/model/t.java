package qsbk.app.model;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.model.UserLoginGuideCard.ViewHolder;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.SubscribeReportHelper;

class t implements OnClickListener {
    final /* synthetic */ ViewHolder a;

    t(ViewHolder viewHolder) {
        this.a = viewHolder;
    }

    public void onClick(View view) {
        DebugUtil.debug(UserLoginGuideCard.c, "qqLogin ");
        UserLoginGuideCard.b("login_guide_card", "login_guide_card_qq");
        UserLoginGuideCard.j = ThirdPartyConstants.THIRDPARTY_TYLE_QQ;
        UserLoginGuideCard.q = new a();
        UserLoginGuideCard.p = ThirdParty.getTencentInstance(ThirdPartyConstants.QQ_CONSUMER_KEY, UserLoginGuideCard.d.getApplicationContext());
        UserLoginGuideCard.p.login((Activity) UserLoginGuideCard.d, "all", UserLoginGuideCard.q);
        SubscribeReportHelper.report(QsbkApp.mContext, -1);
    }
}

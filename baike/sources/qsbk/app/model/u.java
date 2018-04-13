package qsbk.app.model;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.model.UserLoginGuideCard.ViewHolder;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.SubscribeReportHelper;
import qsbk.app.wxapi.WXAuthHelper;

class u implements OnClickListener {
    final /* synthetic */ ViewHolder a;

    u(ViewHolder viewHolder) {
        this.a = viewHolder;
    }

    public void onClick(View view) {
        DebugUtil.debug(UserLoginGuideCard.c, "weixinLogin ");
        UserLoginGuideCard.b("login_guide_card", "login_guide_card_wx");
        UserLoginGuideCard.j = ThirdPartyConstants.THIRDPARTY_TYLE_WX;
        UserLoginGuideCard.m = WXAuthHelper.getInstance(view.getContext());
        UserLoginGuideCard.m.startAuth(new b());
        SubscribeReportHelper.report(QsbkApp.mContext, -1);
    }
}

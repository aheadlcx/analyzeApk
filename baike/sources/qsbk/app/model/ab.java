package qsbk.app.model;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.WelcomeCard.ViewHolder;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.DebugUtil;
import qsbk.app.wxapi.WXAuthHelper;

class ab implements OnClickListener {
    final /* synthetic */ ViewHolder a;

    ab(ViewHolder viewHolder) {
        this.a = viewHolder;
    }

    public void onClick(View view) {
        DebugUtil.debug(WelcomeCard.c, "weixinLogin ");
        WelcomeCard.b("welcome_card", "welcome_card_wx");
        WelcomeCard.f = ThirdPartyConstants.THIRDPARTY_TYLE_WX;
        WelcomeCard.l = WXAuthHelper.getInstance(view.getContext());
        WelcomeCard.l.startAuth(new b());
    }
}

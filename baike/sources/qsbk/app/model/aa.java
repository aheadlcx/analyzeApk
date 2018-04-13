package qsbk.app.model;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.WelcomeCard.ViewHolder;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.DebugUtil;

class aa implements OnClickListener {
    final /* synthetic */ ViewHolder a;

    aa(ViewHolder viewHolder) {
        this.a = viewHolder;
    }

    public void onClick(View view) {
        DebugUtil.debug(WelcomeCard.c, "qqLogin ");
        WelcomeCard.b("welcome_card", "welcome_card_qq");
        WelcomeCard.f = ThirdPartyConstants.THIRDPARTY_TYLE_QQ;
        WelcomeCard.k = new a();
        WelcomeCard.j = ThirdParty.getTencentInstance(ThirdPartyConstants.QQ_CONSUMER_KEY, WelcomeCard.e.getApplicationContext());
        WelcomeCard.j.login((Activity) WelcomeCard.e, "all", WelcomeCard.k);
    }
}

package qsbk.app.model;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.WeiboLoginActivity;
import qsbk.app.model.WelcomeCard.ViewHolder;
import qsbk.app.utils.DebugUtil;

class ac implements OnClickListener {
    final /* synthetic */ ViewHolder a;

    ac(ViewHolder viewHolder) {
        this.a = viewHolder;
    }

    public void onClick(View view) {
        DebugUtil.debug(WelcomeCard.c, "weiboLogin ");
        WelcomeCard.b("welcome_card", "login_guide_card_weibo");
        WelcomeCard.e.startActivity(new Intent(WelcomeCard.e, WeiboLoginActivity.class));
    }
}

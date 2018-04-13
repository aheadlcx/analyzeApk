package qsbk.app.model;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.activity.ActionBarLoginActivity;
import qsbk.app.model.WelcomeCard.ViewHolder;
import qsbk.app.utils.DebugUtil;

class ad implements OnClickListener {
    final /* synthetic */ ViewHolder a;

    ad(ViewHolder viewHolder) {
        this.a = viewHolder;
    }

    public void onClick(View view) {
        DebugUtil.debug(WelcomeCard.c, "qiubaiLogin ");
        WelcomeCard.b("welcome_card", "login_guide_card_qb");
        if (QsbkApp.currentUser == null) {
            WelcomeCard.e.startActivity(new Intent(WelcomeCard.e, ActionBarLoginActivity.class));
        }
    }
}

package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.utils.UIHelper;

class fp implements OnClickListener {
    final /* synthetic */ IMChatBaseActivityEx a;

    fp(IMChatBaseActivityEx iMChatBaseActivityEx) {
        this.a = iMChatBaseActivityEx;
    }

    public void onClick(View view) {
        this.a.q.setBackgroundColor(UIHelper.isNightTheme() ? -15724269 : -1973791);
        this.a.r.setBackgroundColor(UIHelper.isNightTheme() ? -15263461 : -328966);
        this.a.A();
    }
}
